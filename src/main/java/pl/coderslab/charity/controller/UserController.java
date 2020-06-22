package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.interfaces.CategoryService;
import pl.coderslab.charity.interfaces.DonationService;
import pl.coderslab.charity.interfaces.InstitutionService;
import pl.coderslab.charity.interfaces.UserService;
import pl.coderslab.charity.model.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private CategoryService categoryService;
    private InstitutionService institutionService;
    private DonationService donationService;

    public UserController(UserService userService, CategoryService categoryService, InstitutionService institutionService, DonationService donationService) {
        this.userService = userService;
        this.categoryService = categoryService;
        this.institutionService = institutionService;
        this.donationService = donationService;
    }

    @GetMapping("/panel")
    public String getUserPanel(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user", currentUser.getUser());
        return "/user/panel";
    }

    @GetMapping("/editProfile/{id}")
    public String editProfile(@PathVariable Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "/user/userForm";
    }

    @PostMapping("/editProfile")
    public String editProfile(@ModelAttribute User user){
        userService.editUser(user);
        return "redirect:/user/panel";
    }

    @GetMapping("/passwordChange/{id}")
    public String changePassword(@PathVariable Long id,Model model){
        model.addAttribute("user", userService.findById(id));
        return "/user/changePassword";
    }

    @PostMapping("/passwordChange")
    public String changePassword(@ModelAttribute User user){
        userService.changePassword(user);
        return "redirect:/user/panel";
    }

    @GetMapping("/donation")
    public String donationForm(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user", currentUser.getUser());
        model.addAttribute("donation", new Donation());
        return "/user/form";
    }

    @ModelAttribute("categories")
    public List<Category> categories(){
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> institutions(){
        return institutionService.findAll();
    }

    @GetMapping("/myDonations/{id}")
    public String myDonations(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("user", currentUser.getUser());
        return "/user/userDonations";
    }

    @ModelAttribute("userDonations")
    public List<Donation> userDonations(@AuthenticationPrincipal CurrentUser currentUser){
        return donationService.getUserDonations(currentUser.getUser().getId());
    }
}
