package gd.tp.recuperacion;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity

public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String code;
    private LocalDateTime expiration;

    @Column(nullable = false)
    private boolean verified = false;

    private boolean isUsed;

    public PasswordResetToken() {
    }

    public PasswordResetToken(String email, String code, LocalDateTime expiration) {
        this.email = email;
        this.code = code;
        this.expiration = expiration;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expiration);
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
	    "id=" + id +
	    ", email='" + email + '\'' +
	    ", code='[PROTECTED]'" + // Don't expose the code in logs
	    ", expiration=" + expiration +
	    ", verified=" + verified +
	    ", isUsed=" + isUsed +
	    '}';
    }
}
