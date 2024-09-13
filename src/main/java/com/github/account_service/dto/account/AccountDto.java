package com.github.account_service.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.account_service.model.AccountStatus;
import com.github.account_service.model.AccountType;
import com.github.account_service.model.Currency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AccountDto {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String number;

    private Long userId;

    private Long projectId;

    @NotNull(message = "Select account type")
    private AccountType type;

    @NotNull(message = "Select currency")
    private Currency currency;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private AccountStatus status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime updatedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime closedAt;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private long version;
}
