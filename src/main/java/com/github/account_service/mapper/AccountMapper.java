package com.github.account_service.mapper;



import com.github.account_service.dto.SavingsAccountDto;
import com.github.account_service.dto.TariffDto;
import com.github.account_service.model.saving.SavingAccount;
import com.github.account_service.model.saving.Tariff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {
    @Mapping(source = "current_tariff", target = "current_tariff", ignore = true)
    SavingAccount toEntity(SavingsAccountDto dto);

    @Mapping(target = "accountId", source = "account.id")
    @Mapping(target = "current_tariff", source = "current_tariff.id")
    SavingsAccountDto toDto(SavingAccount entity);

    TariffDto toDto(Tariff entity);
}
