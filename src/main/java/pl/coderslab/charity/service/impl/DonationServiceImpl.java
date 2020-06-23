package pl.coderslab.charity.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.service.DonationService;
import pl.coderslab.charity.repository.DonationRepository;

import java.util.List;
import java.util.Optional;

@Service
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

    @Override
    public void save(Donation donation) {
        donationRepository.save(donation);
    }

    @Override
    public List<Donation> getUserDonations(Long userId) {
        return donationRepository.getUserDonations(userId);
    }

    @Override
    public Donation findById(Long id) {
        return donationRepository.getOne(id);
    }
}
