package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.service.UserService;
import pl.coderslab.charity.model.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private InstitutionService institutionService;
    private UserService userService;

    public AdminController(InstitutionService institutionService, UserService userService) {
        this.institutionService = institutionService;
        this.userService = userService;
    }

    @GetMapping("/panel")
    public String getAdminPanel(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("admin", currentUser.getUser());
        return "/admin/panel";
    }

    @GetMapping("/institutions")
    public String getInstitutions(Model model){
        model.addAttribute("institutions", institutionService.findAll());
        return "/admin/institutions";
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutions(@AuthenticationPrincipal CurrentUser currentUser, Model model){
        model.addAttribute("admin", currentUser.getUser());
        return institutionService.findAll();
    }

    @GetMapping("/instAdd")
    public String institutionForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("institution", new Institution());
        model.addAttribute("admin", currentUser.getUser());
        return "/admin/institutionForm";
    }

    @PostMapping("/instAdd")
    public String addInstitution(@ModelAttribute Institution institution) {
        institutionService.save(institution);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/instUpdate/{id}")
    public String editInstitution(@PathVariable Long id, Model model){
        model.addAttribute("institutionToUpdate", institutionService.findById(id));
        return "/admin/editInstitution";
    }

    @PostMapping("/instUpdate")
    public String editInstitution(@ModelAttribute Institution institution){
        institutionService.save(institution);
        return "redirect:/admin/panel";
    }

    @GetMapping("/instDelete/{id}")
    public String deleteInstitution(@PathVariable Long id){
        institutionService.deleteInstitution(id);
        return "redirect:/admin/institutions";
    }

    @GetMapping("/administrators")
    public String getAdministrators(Model model){
        model.addAttribute("administrators", userService.findAllAdministrators());
        return "/admin/administrators";
    }

    @ModelAttribute("administrators")
    public List<User> administrators(Model model){
        return userService.findAllAdministrators();
    }

    @GetMapping("/adminAdd")
    public String administratorForm(@AuthenticationPrincipal CurrentUser currentUser, Model model) {
        model.addAttribute("administrator", new User());
        model.addAttribute("admin", currentUser.getUser());
        return "/admin/administratorForm";
    }

    @PostMapping("/adminAdd")
    public String addAdministrator(@ModelAttribute User user) {
        userService.saveAdmin(user);
        return "redirect:/admin/administrators";
    }

    @GetMapping("/adminUpdate/{id}")
    public String editAdministrator(@PathVariable Long id, Model model){
        model.addAttribute("adminToUpdate", userService.findById(id));
        return "/admin/editAdmin";
    }

    @PostMapping("/adminUpdate")
    public String editAdministrator(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/admin/administrators";
    }

    @GetMapping("/adminDelete/{id}")
    public String deleteAdmin(@PathVariable Long id, @AuthenticationPrincipal CurrentUser currentUser, Model model){
        if (currentUser.getUser().getId().equals(id)){
            model.addAttribute("admin", currentUser.getUser());
            return "/admin/selfDeleteTry";
        } else {
            userService.deleteUser(id);
            return "redirect:/admin/administrators";
        }
    }

    @GetMapping("/users")
    public String getUserList(Model model){
        model.addAttribute("users", userService.findAllUsers());
        return "/admin/users";
    }

    @GetMapping("/userAdd")
    public String addUser(Model model){
        model.addAttribute("user", new User());
        return "/admin/userForm";
    }

    @PostMapping("/userAdd")
    public String userAdd(@ModelAttribute User user){
        userService.saveUser(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/userUpdate/{id}")
    public String userUpdate(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.findById(id));
        return "/admin/editUser";
    }

    @PostMapping("/userUpdate")
    public String userUpdate(@ModelAttribute User user){
        userService.save(user);
        return "redirect:/admin/users";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/deactivateUser/{id}")
    public String deactivateUser (@PathVariable Long id) {
        User userById = userService.findById(id);
        userById.setEnabled(0);
        userService.save(userById);
        return "redirect:/admin/users";
    }

    @GetMapping("/activateUser/{id}")
    public String activateUser (@PathVariable Long id){
        User userById = userService.findById(id);
        userById.setEnabled(1);
        userService.save(userById);
        return "redirect:/admin/users";
    }
}
