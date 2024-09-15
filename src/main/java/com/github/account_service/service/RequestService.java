package com.github.account_service.service;

import com.github.account_service.dto.RequestDto;
import com.github.account_service.entity.Request;
import com.github.account_service.enums.RequestStatus;
import com.github.account_service.mapper.RequestMapper;
import com.github.account_service.repository.RequestRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class RequestService {
    private final RequestRepository repository;
    private final RequestMapper requestMapper;

    public RequestDto getRequest(long id) {
        Optional<Request> requestById = repository.findById(id);
        validateRequest(requestById);
        return requestMapper.toDto(requestById.get());
    }

    @Transactional
    public RequestDto getOrSaveRequest(Request request) {
        Request requestBuild = getRequest(request);
        Optional<Request> requestById = repository.findById(requestBuild.getId());
        if (requestById.isPresent()) {
            validateInputData(requestBuild, requestById);
            return requestMapper.toDto(requestById.get());
        }
        repository.save(requestBuild);
        return requestMapper.toDto(requestBuild);
    }

    private void validateInputData(Request requestBuild, Optional<Request> requestById) {
        Map<String, Object> inputData = requestBuild.getInputData();
        Map<String, Object> inputDataDB = requestById.get().getInputData();
        if(!inputData.equals(inputDataDB)) {
            throw new Error("409");
        }
    }


    @Transactional
    public RequestDto changeStatus(Request request) {
        Request requestBuild = getRequest(request);
        Optional<Request> requestById = repository.findById(requestBuild.getId());
        validateRequest(requestById);
        if (request.getRequestStatus() == RequestStatus.FAILED) {
            request.setActive(false);
        }
        request.setRequestStatus(request.getRequestStatus());
        request.setDetails(request.getDetails());
        repository.save(request);
        return requestMapper.toDto(request);
    }

    protected Request build(RequestDto requestDto) {
        return Request.builder()
                .id(requestDto.getRequestId())
                .userId(requestDto.getUserId())
                .lockValue(requestDto.getLockValue())
                .active(requestDto.isActive())
                .details(requestDto.getDetails())
                .version(requestDto.getVersion())
                .requestType(requestDto.getRequestType())
                .requestStatus(requestDto.getRequestStatus())
                .inputData(requestDto.getInputData())
                .build();
    }

    private Request getRequest(Request request) {
        RequestDto requestDto = requestMapper.toDto(request);
        return build(requestDto);
    }

    private void validateRequest(Optional<Request> requestById) {
        if (requestById.isEmpty()) {
            log.error("request doesn't exist");
            throw new IllegalArgumentException("request doesn't exist");
        }
    }
}