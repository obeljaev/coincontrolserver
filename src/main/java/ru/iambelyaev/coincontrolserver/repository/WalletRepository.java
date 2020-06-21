package ru.iambelyaev.coincontrolserver.repository;

import ru.iambelyaev.coincontrolserver.model.Category;
import ru.iambelyaev.coincontrolserver.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository

public interface WalletRepository extends JpaRepository<Wallet, Long> {

    @Query(value = "select count(*) > 0 from wallets join users on wallets.user_id = users.id " +
            "where users.id = :userId and wallets.name = :name", nativeQuery = true)
    Boolean existByName(Long userId, String name);

    List<Wallet> findByUserId(Long userId);

    @Query(value = "SELECT count(*) > 0 user_id " +
            "FROM wallets LEFT JOIN users " +
            "ON wallets.user_id = users.id " +
            "WHERE wallets.id = :walletId AND users.id = :userId", nativeQuery = true)
    Boolean isUserWallet(@Param("walletId") Long walletId,
                           @Param("userId") Long userId);
}