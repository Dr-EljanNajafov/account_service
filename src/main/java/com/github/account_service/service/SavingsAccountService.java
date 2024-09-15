package com.github.account_service.service;


import com.github.account_service.dto.SavingsAccountDto;
import com.github.account_service.dto.TariffDto;
import com.github.account_service.mapper.AccountMapper;
import com.github.account_service.repository.SavingAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavingsAccountService {
    private final AccountMapper accountMapper;
    private final AccountService accountService;
    private final SavingAccountRepository savingAccountRepository;
    private final TariffService tariffService;

    public SavingsAccountDto openAccount(SavingsAccountDto accountDTO){
        var savingAccount  = accountMapper.toEntity(accountDTO);
        var account = accountService.getAccountById(accountDTO.getAccountId());
        var tariff = tariffService.getTariffById(accountDTO.getCurrent_tariff());
        savingAccount.setAccount(account);
        savingAccount.setCurrent_tariff(tariff);
        var savedAccount = savingAccountRepository.save(savingAccount);
        return accountMapper.toDto(savedAccount);
    }

    public TariffDto getTariffByAccountId(long accountId){
        var tariff = tariffService.getTariffById(accountId);
        return accountMapper.toDto(tariff);
    }
}