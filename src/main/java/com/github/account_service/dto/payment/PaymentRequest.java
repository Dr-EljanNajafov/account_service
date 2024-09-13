package com.github.account_service.dto.payment;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.Currency;

public record PaymentRequest(
        @NotNull
        long paymentNumber,

        @Min(1)
        @NotNull
        BigDecimal amount,

        @NotNull
        Currency currency
) {
}