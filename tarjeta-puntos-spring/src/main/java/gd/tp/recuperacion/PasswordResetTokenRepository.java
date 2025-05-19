package gd.tp.recuperacion;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    Optional<PasswordResetToken> findByEmailAndCode(String email, String code);

    Optional<PasswordResetToken> findFirstByEmailAndIsUsedFalse (String email);

    Optional<PasswordResetToken> findFirstByEmailAndVerifiedTrueAndIsUsedFalse(String email);


}
