package com.github.account_service.controller;

import com.github.account_service.dto.SavingsAccountDto;
import com.github.account_service.dto.TariffDto;
import com.github.account_service.service.SavingsAccountService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/savings-account")
public class SavingsAccountController {
    private final SavingsAccountService savingsAccountService;

    @PostMapping
    public ResponseEntity<SavingsAccountDto> openAccount(@RequestBody @Valid SavingsAccountDto savingsAccountDTO) {
        var accountDto = savingsAccountService.openAccount(savingsAccountDTO);
        return ResponseEntity.ok().body(accountDto);
    }
    @GetMapping("/{id}")
    public ResponseEntity<TariffDto> getTariffByAccountId(@PathVariable long id) {
        var tariffDto = savingsAccountService.getTariffByAccountId(id);
        return ResponseEntity.ok().body(tariffDto);
    }
}