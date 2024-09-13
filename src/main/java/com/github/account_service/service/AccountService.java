package com.github.account_service.service;

import com.github.account_service.dto.AccountDto;
import com.github.account_service.mapper.AccountMapper;
import com.github.account_service.model.Account;
import com.github.account_service.repository.AccountRepository;
import com.github.account_service.util.exceptionhandler.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountDto get(Long id) {
        Account foundAccount =getAccountById(id);
        return accountMapper.toDto(foundAccount);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with id %s not found", id)));
    }
}
