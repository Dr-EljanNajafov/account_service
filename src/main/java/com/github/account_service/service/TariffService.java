package com.github.account_service.service;

import com.github.account_service.model.saving.Tariff;
import com.github.account_service.repository.TariffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class TariffService {
    private final TariffRepository tariffRepository;

    public Tariff getTariffById(long id) {
        return tariffRepository.findById(id).orElseThrow(
                () -> new RuntimeException("No tariff with id: " + id)
        );
    }
}