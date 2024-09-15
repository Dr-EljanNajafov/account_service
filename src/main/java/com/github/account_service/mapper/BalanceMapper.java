package com.github.account_service.mapper;


import com.github.account_service.dto.balance.BalanceDto;
import com.github.account_service.model.Balance;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface BalanceMapper {

    Balance toEntity(BalanceDto balanceDto);

    @Mapping(target = "accountId", source = "account.id")
    BalanceDto toDto(Balance balance);
}
