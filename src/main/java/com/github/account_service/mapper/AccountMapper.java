package com.github.account_service.mapper;

import com.github.account_service.dto.account.AccountDto;
import com.github.account_service.model.Account;
import com.github.account_service.dto.SavingsAccountDto;
import com.github.account_service.dto.TariffDto;
import com.github.account_service.model.saving.SavingAccount;
import com.github.account_service.model.saving.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDto toDto(Account account);

    @Mapping(target = "status", expression = "java(com.github.account_service.model.AccountStatus.ACTIVE)")
    Account toEntity(AccountDto accountDto);
    @Mapping(source = "currentTariff", target = "currentTariff", ignore = true)
    SavingAccount toEntity(SavingsAccountDto dto);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "currentTariff", source = "currentTariff.id")
    SavingsAccountDto toDto(SavingAccount entity);

    TariffDto toDto(Tariff entity);

    Tariff toEntity(TariffDto dto);
}
