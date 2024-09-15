package com.github.account_service.service.balance;

import com.github.account_service.dto.balance.BalanceUpdateDto;
import com.github.account_service.dto.balance.BalanceUpdateType;
import com.github.account_service.model.Balance;
import org.springframework.stereotype.Component;

@Component
public class BalanceReplenishment implements BalanceUpdater {
    @Override
    public boolean isApplicable(BalanceUpdateDto dto) {
        return dto.type() == BalanceUpdateType.REPLENISHMENT;
    }

    @Override
    public Balance update(Balance balance, BalanceUpdateDto dto) {
        return updateAuthAndActualBalance(balance, dto);
    }

    private Balance updateAuthAndActualBalance(Balance balance, BalanceUpdateDto dto) {
        balance.setAuthBalance(balance.getAuthBalance().add(dto.amount()));
        balance.setActualBalance(balance.getActualBalance().add(dto.amount()));
        return balance;
    }
}