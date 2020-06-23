package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.fixture.InitDataFixture;
import pl.coderslab.charity.service.EmailService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.model.CurrentUser;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private InitDataFixture initDataFixture;
    private UserService userService;
    private EmailService emailService;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, InitDataFixture initDataFixture, UserService userService, EmailService emailService) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.initDataFixture = initDataFixture;
        this.userService = userService;
        this.emailService = emailService;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bags", donationRepository.bagsSum().orElse(0));
        model.addAttribute("donations", donationRepository.findAll().size());
        return "index";
    }

    @GetMapping("/panel")
    public String loginAction(@AuthenticationPrincipal CurrentUser currentUser) {
        if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "redirect:/admin/panel";
        } else if (currentUser.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
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
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String userRegister(@ModelAttribute User user) {
        userService.saveUser(user);
        return "index";
    }


    @GetMapping("/mail")
    @ResponseBody
    public String sendMail(){
        emailService.simpleMessage("karol_ur@interia.pl", "test", "test");
        return "email sent";
    }
}
