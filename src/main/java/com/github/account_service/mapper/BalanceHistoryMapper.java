package com.github.account_service.mapper;

import com.github.account_service.dto.balance.BalanceHistoryDto;
import com.github.account_service.model.BalanceHistory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BalanceHistoryMapper {

    @Mapping(target = "balance", ignore = true)
    BalanceHistory toEntity(BalanceHistoryDto balanceHistoryDto);

    @Mapping(target = "balanceId", source = "balance.id")
    BalanceHistoryDto toDto(BalanceHistory balanceHistory);
}