package com.assetguard.core.dto.user;

import com.assetguard.core.model.shared.RecordStatus;
import com.assetguard.core.model.shared.UserRole;
import java.time.Instant;
import java.util.UUID;

public record UserResponse(
        UUID id,
        String employeeId,
        String fullName,
        String email,
        String department,
        UserRole role,
        RecordStatus recordStatus,
        Instant createdAt,
        Instant updatedAt) {}
