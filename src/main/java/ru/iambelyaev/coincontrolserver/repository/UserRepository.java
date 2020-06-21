package ru.iambelyaev.coincontrolserver.repository;

import ru.iambelyaev.coincontrolserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT COUNT(c) > 0 FROM User c WHERE c.name = ?1")
    Boolean existByName(String name);
}
