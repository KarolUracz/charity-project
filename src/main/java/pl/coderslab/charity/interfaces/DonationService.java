package pl.coderslab.charity.interfaces;

import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;
import pl.coderslab.charity.entity.User;

import java.util.List;
import java.util.Optional;

public interface DonationService {
    List<Donation> findAll();
    Optional<Integer> bagsSum();
    void save(Donation donation);
    List<Donation> getUserDonations(Long userId);
}
