package com.github.account_service.mapper;


import com.github.account_service.dto.RequestDto;
import com.github.account_service.entity.Request;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RequestMapper {
    @Mapping(target = "requestId", source = "id")
    RequestDto toDto(Request request);

    @Mapping(target = "id", source = "requestId")
    Request toEntity(RequestDto requestDto);
}
