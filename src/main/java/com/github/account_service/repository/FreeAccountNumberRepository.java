package com.github.account_service.repository;

import com.github.account_service.model.AccountType;
import com.github.account_service.model.FreeAccountNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FreeAccountNumberRepository extends JpaRepository<FreeAccountNumber, Long> {

    Optional<FreeAccountNumber> findByAccountType(AccountType accountType);
}