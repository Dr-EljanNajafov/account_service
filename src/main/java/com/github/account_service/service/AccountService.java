package com.github.account_service.service;

import com.github.account_service.dto.account.AccountDto;
import com.github.account_service.mapper.AccountMapper;
import com.github.account_service.model.Account;
import com.github.account_service.repository.AccountRepository;
import com.github.account_service.util.exceptionhandler.EntityNotFoundException;
import com.github.account_service.util.validator.AccountServiceValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final AccountServiceValidator accountServiceValidator;

    public AccountDto get(Long id) {
        Account foundAccount =getAccountById(id);
        return accountMapper.toDto(foundAccount);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("Account with id %s not found", id)));
    }

    @Transactional
    public AccountDto create(AccountDto dto) {
        accountServiceValidator.validateToCreate(dto);

        Account account = accountMapper.toEntity(dto);

        Account saved = accountRepository.save(account);

        return accountMapper.toDto(saved);
    }
}
