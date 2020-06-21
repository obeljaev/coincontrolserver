package ru.iambelyaev.coincontrolserver.repository;

import ru.iambelyaev.coincontrolserver.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository

public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findByUserId(Long userId);

    @Query(value = "SELECT count(*) > 0 user_id " +
            "FROM operations LEFT JOIN users " +
            "ON operations.user_id = users.id " +
            "WHERE operations.id = :operationId AND users.id = :userId", nativeQuery = true)
    Boolean isUserOperation(@Param("operationId") Long operationId,
                         @Param("userId") Long userId);
}