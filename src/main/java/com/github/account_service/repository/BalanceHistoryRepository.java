package com.github.account_service.repository;

import com.github.account_service.model.BalanceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceHistoryRepository extends JpaRepository<BalanceHistory, Long> {
}
