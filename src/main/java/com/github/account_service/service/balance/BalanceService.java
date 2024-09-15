package com.github.account_service.service.balance;

import com.github.account_service.dto.balance.BalanceDto;
import com.github.account_service.dto.balance.BalanceUpdateDto;
import com.github.account_service.mapper.BalanceMapper;
import com.github.account_service.model.Account;
import com.github.account_service.model.Balance;
import com.github.account_service.repository.AccountRepository;
import com.github.account_service.repository.BalanceRepository;
import com.github.account_service.util.exception.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {

    private final AccountRepository accountRepository;
    private final BalanceRepository balanceRepository;
    private final BalanceHistoryService balanceHistoryService;

    private final BalanceMapper balanceMapper;

    private final List<BalanceUpdater> updaters;

    @Transactional
    public BalanceDto create(Long accountId) {
        Balance balance = createBalance(accountId);

        balance = balanceRepository.save(balance);

        log.info("Created balance: {}", balance);
        return balanceMapper.toDto(balance);
    }

    @Transactional
    public BalanceDto get(Long balanceId) {
        Balance balance = getBalance(balanceId);
        log.info("Got balance: {}", balance);

        return balanceMapper.toDto(balance);
    }

    @Transactional
    public BalanceDto update(BalanceUpdateDto balanceUpdateDto) {
        Balance balance = updateBalance(balanceUpdateDto);

        balance = balanceRepository.save(balance);
        balanceHistoryService.save(balance, balanceUpdateDto.type(), balanceUpdateDto.amount());
        log.info("Updated balance: {}", balance);

        return balanceMapper.toDto(balance);
    }

    private Balance updateBalance(BalanceUpdateDto balanceUpdateDto) {
        Balance balance = getBalance(balanceUpdateDto.balanceId());

        return updaters.stream()
                .filter(updater -> updater.isApplicable(balanceUpdateDto))
                .findFirst()
                .map(updater -> updater.update(balance, balanceUpdateDto))
                .orElseThrow(() -> new IllegalArgumentException("Unknown balance update type: " + balanceUpdateDto.type()));
    }

    private Balance getBalance(Long balanceId) {
        return balanceRepository.findById(balanceId)
                .orElseThrow(() -> new EntityNotFoundException("Balance with id " + balanceId + " not found"));
    }

    private Balance createBalance(Long accountId) {
        Account account = accountRepository.getReferenceById(accountId);
        return Balance.builder()
                .account(account)
                .authBalance(BigDecimal.ZERO)
                .actualBalance(BigDecimal.ZERO)
                .build();
    }
}