package com.github.account_service.service.balance;

import com.github.account_service.dto.balance.BalanceUpdateType;
import com.github.account_service.model.Balance;
import com.github.account_service.model.BalanceHistory;
import com.github.account_service.repository.BalanceHistoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceHistoryService {

    private final BalanceHistoryRepository balanceHistoryRepository;

    public void save(Balance balance, BalanceUpdateType type, BigDecimal amount) {
        BalanceHistory balanceHistory = createBalanceHistory(balance, type, amount);

        balanceHistoryRepository.save(balanceHistory);
        log.info("Saved balance history: {}", balanceHistory);
    }

    private BalanceHistory createBalanceHistory(Balance balance, BalanceUpdateType type, BigDecimal amount) {
        return BalanceHistory.builder()
                .balance(balance)
                .type(type)
                .amount(amount)
                .build();
    }
}