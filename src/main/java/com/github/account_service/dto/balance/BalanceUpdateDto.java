package com.github.account_service.dto.balance;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record BalanceUpdateDto(@NotNull Long balanceId,
                               @NotNull BalanceUpdateType type,
                               @NotNull @Min(1) BigDecimal amount) {

}
