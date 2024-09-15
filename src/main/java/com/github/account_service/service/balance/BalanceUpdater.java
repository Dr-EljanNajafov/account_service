package com.github.account_service.service.balance;


import com.github.account_service.dto.balance.BalanceUpdateDto;
import com.github.account_service.model.Balance;

public interface BalanceUpdater {

    boolean isApplicable(BalanceUpdateDto dto);

    Balance update(Balance balance, BalanceUpdateDto dto);
}