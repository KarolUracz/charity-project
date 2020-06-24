package pl.coderslab.charity.service;

import pl.coderslab.charity.entity.VerificationToken;

public interface VerificationTokenService {
    void saveToken(VerificationToken verificationToken);
    VerificationToken findByToken(String token);
}
