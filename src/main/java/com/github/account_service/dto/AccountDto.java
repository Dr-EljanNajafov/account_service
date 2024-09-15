package com.github.account_service.dto;

import com.github.account_service.model.AccountStatus;
import com.github.account_service.model.AccountType;
import com.github.account_service.model.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {
    private Long id;
    private String number;
    private Long userId;
    private Long projectId;
    private AccountType type;
    private Currency currency;
    private AccountStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant closedAt;
    private long version;
}