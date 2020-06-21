package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.interfaces.UserService;
import pl.coderslab.charity.model.CurrentUser;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
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
}
