package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.Donation;

import java.util.List;
import java.util.Optional;

public interface DonationService {
    List<Donation> findAll();
    Optional<Integer> bagsSum();
    void save(Donation donation);
    List<Donation> getUserDonations(Long userId);
    Donation findById(Long id);
}
