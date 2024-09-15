package com.github.account_service.dto;


import com.github.account_service.enums.RequestStatus;
import com.github.account_service.enums.RequestType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class RequestDto {
    @NotNull
    private Long requestId;

    @NotNull
    private Long userId;

    private RequestStatus requestStatus;

    @NotNull
    private Long lockValue;

    private boolean active;

    @NotNull
    private Long version;

    private RequestType requestType;
}