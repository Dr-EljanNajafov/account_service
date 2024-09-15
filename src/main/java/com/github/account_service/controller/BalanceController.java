package com.github.account_service.controller;

import com.github.account_service.dto.balance.BalanceDto;
import com.github.account_service.dto.balance.BalanceUpdateDto;
import com.github.account_service.service.balance.BalanceService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/balance")
public class BalanceController {

    private final BalanceService service;

    @PostMapping("/{accountId}/create")
    public ResponseEntity<BalanceDto> create(@NotNull @PathVariable Long accountId) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.create(accountId));
    }

    @GetMapping("/{balanceId}/get")
    public BalanceDto get(@NotNull @PathVariable Long balanceId) {
        return service.get(balanceId);
    }

    @PostMapping("/update")
    public BalanceDto update(@Valid @RequestBody BalanceUpdateDto balanceUpdateDto) {
        return service.update(balanceUpdateDto);
    }

}