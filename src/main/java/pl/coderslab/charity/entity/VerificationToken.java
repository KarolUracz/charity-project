package pl.coderslab.charity.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class VerificationToken {
    private static final int EXPIRATION = 60*24;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    private LocalDateTime expiryDate;

    private LocalDateTime calculateExpiryDate(int expiryTimeInMinutes) {
        LocalDateTime time = LocalDateTime.now();
        time.plus(EXPIRATION, ChronoUnit.HOURS);
        return time;
    }

    public VerificationToken(){}

    public VerificationToken(User user) {
        this.user = user;
        expiryDate = calculateExpiryDate(EXPIRATION);
        token = UUID.randomUUID().toString();
    }
}
