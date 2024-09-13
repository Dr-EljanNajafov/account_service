package com.github.account_service.mapper;

import com.github.account_service.dto.account.AccountDto;
import com.github.account_service.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountDto toDto(Account account);

    @Mapping(target = "status", expression = "java(github.account_service.model.AccountStatus.ACTIVE)")
    Account toEntity(AccountDto accountDto);
}
