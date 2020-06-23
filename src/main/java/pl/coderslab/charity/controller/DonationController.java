package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.service.CategoryService;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.service.InstitutionService;
import pl.coderslab.charity.model.CurrentUser;

import java.util.List;

@Controller
@RequestMapping("/donation")
public class DonationController {

    private InstitutionService institutionService;
    private DonationService donationService;
    private CategoryService categoryService;

    public DonationController(InstitutionService institutionService, DonationService donationService, CategoryService categoryService) {
        this.institutionService = institutionService;
        this.donationService = donationService;
        this.categoryService = categoryService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("category", new Category());
        model.addAttribute("institution", new Institution());
        model.addAttribute("institutions", institutionService.findAll());
        model.addAttribute("donation", new Donation());
        return "user/form";
    }

    @ModelAttribute("categories")
    public List<Category> getCategoryList() {
        return categoryService.findAll();
    }

    @ModelAttribute("institutions")
    public List<Institution> getInstitutionList() {
        return institutionService.findAll();
    }

    @PostMapping("/form")
    public String formAction(@ModelAttribute Donation donation, @AuthenticationPrincipal CurrentUser currentUser) {
        donation.setUser(currentUser.getUser());
        donationService.save(donation);
        return "/form-confirmation";
    }

}
