package com.github.account_service.service.balance;

import com.github.account_service.dto.balance.BalanceUpdateDto;
import com.github.account_service.dto.balance.BalanceUpdateType;
import com.github.account_service.model.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceClearing implements BalanceUpdater {
    @Override
    public boolean isApplicable(BalanceUpdateDto dto) {
        return dto.type() == BalanceUpdateType.CLEARING;
    }

    @Override
    public Balance update(Balance balance, BalanceUpdateDto dto) {
        balance.setActualBalance(balance.getActualBalance().subtract(dto.amount()));
        return balance;
    }
}