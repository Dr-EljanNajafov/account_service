package com.github.account_service.dto;

import com.github.account_service.model.saving.TariffType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TariffDto {
    private Long id;
    private TariffType tariffType;
    private float currentRate;
}