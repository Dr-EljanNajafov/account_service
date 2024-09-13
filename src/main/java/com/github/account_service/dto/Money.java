package com.github.account_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.account_service.model.Currency;

import java.math.BigDecimal;

public record Money (
        @JsonProperty(value = "amount", required = true)
        BigDecimal amount,
        @JsonProperty(value = "currency", required = true)
        Currency currency
) {
}
