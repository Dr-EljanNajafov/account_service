package com.github.account_service.service;

import com.github.account_service.model.Account;
import com.github.account_service.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No account with id: " + id)
        );
    }
}
