package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.fixture.InitDataFixture;
import pl.coderslab.charity.model.UserForm;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.model.CurrentUser;
import pl.coderslab.charity.service.VerificationTokenService;

import javax.validation.Valid;

@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private InitDataFixture initDataFixture;
    private UserService userService;
    private EmailService emailService;
    private VerificationTokenService verificationTokenService;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository,
                          InitDataFixture initDataFixture, UserService userService, EmailService emailService,
                          VerificationTokenService verificationTokenService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.initDataFixture = initDataFixture;
        this.userService = userService;
        this.emailService = emailService;
        this.verificationTokenService = verificationTokenService;
        ;
    }


    @RequestMapping("/")
    public String homeAction(Model model) {
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bags", donationRepository.bagsSum().orElse(0));
        model.addAttribute("donations", donationRepository.findAll().size());
        return "index";
    }

    @GetMapping("/panel")
    public String loginAction(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/panel";
        } else if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))) {
            return "redirect:/user/panel";
        }
        return null;
    }

    @GetMapping("/initData")
    @ResponseBody
    public String initData() {
        this.initDataFixture.initRoles();
        this.initDataFixture.initUsers();
        return "initialized";
    }

    @GetMapping("/register")
    public String registration(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "/register";
        }
        User existingUser = userService.findByUserName(userForm.getUsername());
        if (existingUser != null) {
            return "/registerError";
        } else {
            User userToSave = new User();
            userToSave.setUsername(userForm.getUsername());
            userToSave.setPassword(userForm.getPasswordConfirm());
            userService.saveUser(userToSave);
            VerificationToken verificationToken = new VerificationToken(userToSave);
            verificationTokenService.saveToken(verificationToken);
            emailService.sendConfirmationMail(userToSave.getUsername(), verificationToken.getToken());
            return "/registerSuccess";
        }
    }

    @RequestMapping(value = "/confirm-account", method = {RequestMethod.GET, RequestMethod.POST})
    public String confirmUserAccount(@RequestParam("token") String verificationToken) {
        VerificationToken token = verificationTokenService.findByToken(verificationToken);
        if (token != null && verificationTokenService.verifyTokenExpiryDate(token)) {
            User user = userService.findByUserName(token.getUser().getUsername());
            user.setEnabled(1);
            userService.save(user);
            return "/accountVerified";
        } else {
            return "/confirmationError";
        }
    }

    @GetMapping("/resetPassword")
    public String resetPassword() {
        return "/resetPassword";
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestParam String resetMail) {
        User byUserName = userService.findByUserName(resetMail);
        if (byUserName != null) {
            VerificationToken token = new VerificationToken(byUserName);
            verificationTokenService.saveToken(token);
            emailService.sendResetPasswordMail(resetMail, token.getToken());
            return "/successForgotPassword";
        } else {
            return "/mailNotPresent";
        }
    }

    @RequestMapping(value = "/confirm-reset", method = {RequestMethod.GET, RequestMethod.POST})
    public String validateResetToken(@RequestParam String token, Model model) {
        VerificationToken byToken = verificationTokenService.findByToken(token);
        if (byToken != null && verificationTokenService.verifyTokenExpiryDate(byToken)) {
            User fromToken = userService.findByUserName(byToken.getUser().getUsername());
            UserForm userForm = new UserForm();
            userForm.setUsername(fromToken.getUsername());
            model.addAttribute("userForm", userForm);
            return "/resetPasswordForm";
        } else {
            return "/registerError";
        }
    }

    @PostMapping("/reset-password")
    public String passReset(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/resetPasswordForm";
        }
        if (userForm != null && userForm.getPassword().equals(userForm.getPasswordConfirm())) {
                userService.resetPassword(userForm.getUsername(), userForm.getPasswordConfirm());
                return "redirect:/login";
            } else {
                return "/resetPasswordForm";
            }
    }
}
