package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.interfaces.DonationService;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;
import java.util.Optional;

public class DonationServiceImpl implements DonationService {

    private DonationRepository donationRepository;

    public DonationServiceImpl(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    @Override
    public List<Donation> findAll() {
        return donationRepository.findAll();
    }

    @Override
    public Optional<Integer> bagsSum() {
        return donationRepository.bagsSum();
    }
}
