package com.github.account_service.service;

import com.github.account_service.client.ProjectServiceClient;
import com.github.account_service.client.UserServiceClient;
import com.github.account_service.dto.account.AccountDto;
import com.github.account_service.model.Account;
import com.github.account_service.model.AccountStatus;
import com.github.account_service.model.AccountType;
import com.github.account_service.model.Currency;
import com.github.account_service.repository.AccountRepository;
import com.github.account_service.util.exception.DataValidationException;
import com.github.account_service.util.exceptionhandler.EntityNotFoundException;
import com.github.account_service.util.validator.AccountOwnerChecker;
import com.github.account_service.util.validator.AccountServiceValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @Spy
    private AccountOwnerChecker accountOwnerChecker = new AccountOwnerChecker(userServiceClient, projectServiceClient);
    @Mock
    private UserServiceClient userServiceClient;

    @Mock
    private ProjectServiceClient projectServiceClient;

    @Spy
    private AccountServiceValidator accountServiceValidator = new AccountServiceValidator(userServiceClient, projectServiceClient);

    @InjectMocks
    private AccountService accountService;

    @Test
    void get_AccountNotFound_ShouldThrowException() {
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException e = Assertions.assertThrows(EntityNotFoundException.class, () -> {
            accountService.get(1L);
        });
        Assertions.assertEquals("Account with id 1 not found", e.getMessage());
    }

    @Test
    void get_AccountFound_ShouldReturnCorrectDto() {
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(mockAccount()));

        Assertions.assertEquals(mockAccountDto(), accountService.get(1L));
    }

    @Test
    void create_RequestHasUserAndProject_ShouldThrowException() {
        AccountDto accountDto = mockAccountDto();
        accountDto.setProjectId(1L);

        DataValidationException e = Assertions.assertThrows(DataValidationException.class, () -> {
            accountService.create(accountDto);
        });
        Assertions.assertEquals("Post's author can be only author or project and can't be both", e.getMessage());
    }

    @Test
    void create_RequestHasNoOwners_ShouldThrowException() {
        AccountDto accountDto = mockAccountDto();
        accountDto.setUserId(null);

        DataValidationException e = Assertions.assertThrows(DataValidationException.class, () -> {
            accountService.create(accountDto);
        });
        Assertions.assertEquals("Post's author can be only author or project and can't be both", e.getMessage());
    }

    @Test
    void create_RequestHasOnlyOneOwner_ShouldMapCorrectlyAndSave() {
        Mockito.doNothing().when(accountOwnerChecker).validateToCreate(mockAccountDto());
        accountService.create(mockAccountDto());

        Mockito.verify(accountRepository).save(mockAccount());
    }

    private Account mockAccount() {
        return Account.builder()
                .id(1L)
                .userId(1L)
                .number("123456789012345")
                .status(AccountStatus.ACTIVE)
                .type(AccountType.CURRENT_ACCOUNT)
                .currency(Currency.USD)
                .version(1L)
                .build();
    }

    private AccountDto mockAccountDto() {
        return AccountDto.builder()
                .id(1L)
                .userId(1L)
                .number("123456789012345")
                .status(AccountStatus.ACTIVE)
                .type(AccountType.CURRENT_ACCOUNT)
                .currency(Currency.USD)
                .version(1L)
                .build();
    }
}
