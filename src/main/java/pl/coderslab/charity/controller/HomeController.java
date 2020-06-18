package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.charity.fixture.InitDataFixture;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


@Controller
public class HomeController {

    private InstitutionRepository institutionRepository;
    private DonationRepository donationRepository;
    private InitDataFixture initDataFixture;


    public HomeController(InstitutionRepository institutionRepository, DonationRepository donationRepository, InitDataFixture initDataFixture) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.initDataFixture = initDataFixture;
    }


    @RequestMapping("/")
    public String homeAction(Model model){
        model.addAttribute("institutions", institutionRepository.findAll());
        model.addAttribute("bags", donationRepository.bagsSum().orElse(0));
        model.addAttribute("donations", donationRepository.findAll().size());
        return "index";
    }

    @GetMapping("/initData")
    @ResponseBody
    public String initData() {
        this.initDataFixture.initRoles();
        this.initDataFixture.initUsers();
        return "initialized";
    }
}
