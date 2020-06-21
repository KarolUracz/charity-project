package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.interfaces.InstitutionService;
import pl.coderslab.charity.model.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private InstitutionService institutionService;

    public AdminController(InstitutionService institutionService) {
        this.institutionService = institutionService;
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
}
