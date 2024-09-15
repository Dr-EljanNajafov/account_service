package com.github.account_service.service;

import com.github.account_service.dto.RequestDto;
import com.github.account_service.entity.Request;
import com.github.account_service.mapper.RequestMapper;
import com.github.account_service.repository.RequestRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService {
    private final RequestRepository repository;
    private final RequestMapper requestMapper;

    public RequestDto getRequest(RequestDto requestDto) {
        Optional<Request> requestById = repository.findById(requestDto.getRequestId());
        validateRequest(requestById);
        return requestMapper.toDto(requestById.get());
    }

    public RequestDto getOrSaveRequest(RequestDto requestDto, Long requestId) {
        Optional<Request> requestById = repository.findById(requestId);
        if (requestById.isEmpty()) {
            Request request = requestMapper.toEntity(requestDto);
            repository.save(request);
            return requestMapper.toDto(request);
        }
        return requestMapper.toDto(requestById.get());
    }

    public RequestDto changeStatus(RequestDto requestDto) {
        Optional<Request> requestById = repository.findById(requestDto.getRequestId());
        validateRequest(requestById);
        Request request = requestById.get();
        request.setRequestStatus(requestDto.getRequestStatus());
        repository.save(request);
        return requestMapper.toDto(request);
    }

    private void validateRequest(Optional<Request> requestById) {
        if (requestById.isEmpty()) {
            log.error("request doesn't exist");
            throw new IllegalArgumentException("request doesn't exist");
        }
    }
}