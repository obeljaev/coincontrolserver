package ru.iambelyaev.coincontrolserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.iambelyaev.coincontrolserver.model.Verification;
import ru.iambelyaev.coincontrolserver.model.Wallet;

import java.util.List;

@Repository
public interface VerificationRepository extends JpaRepository<Verification, Long> {

    List<Verification> findByUserId(Long userId);

    List<Verification> findByWalletId(Long walletId);

    @Query(value = "SELECT count(*) > 0 wallet_id " +
            "FROM verifications LEFT JOIN wallets " +
            "ON verifications.wallet_id = wallets.id " +
            "WHERE verifications.id = :verificationId AND wallets.id = :walletId", nativeQuery = true)
    Boolean isWalletVerification(@Param("verificationId") Long verificationId,
                         @Param("walletId") Long userId);
}
