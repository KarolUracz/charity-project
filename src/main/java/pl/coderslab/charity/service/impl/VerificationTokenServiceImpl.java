package pl.coderslab.charity.service.impl;

import org.springframework.stereotype.Service;
import pl.coderslab.charity.entity.VerificationToken;
import pl.coderslab.charity.repository.VerificationTokenRepository;
import pl.coderslab.charity.service.VerificationTokenService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

    private VerificationTokenRepository repository;

    public VerificationTokenServiceImpl(VerificationTokenRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveToken(VerificationToken verificationToken) {
        repository.save(verificationToken);
    }

    @Override
    public VerificationToken findByToken(String token) {
        return repository.findByTokenIsLike(token);
    }

    @Override
    public boolean verifyTokenExpiryDate(VerificationToken token) {
        return token.getExpiryDate().isBefore(LocalDateTime.now().plusDays(1));
    }
}
