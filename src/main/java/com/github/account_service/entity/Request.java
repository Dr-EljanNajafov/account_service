package com.github.account_service.entity;

import com.github.account_service.enums.RequestStatus;
import com.github.account_service.enums.RequestType;
import com.github.account_service.util.MapToJsonConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "request")
public class Request {
    @Id
    private Long id;

    @Column(name = "idempotent_token")
    private UUID idempotentToken;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "idempotency_key", nullable = false)
    private String idempotencyKey;

    @Column(name = "username", nullable = false)
    private String userName;

    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @Column(name = "lock_value", nullable = false)
    private Long lockValue;

    @Column(name = "active", nullable = false)
    private boolean active;

    @Column(name = "text", nullable = false)
    private String data;

    @Column(name = "input_data")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, Object> input_data;

    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "details")
    private String details;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "version", nullable = false)
    private Long version;

}
