package com.assetguard.core.dto.user;

import com.assetguard.core.model.shared.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequest(
        @NotBlank String employeeId,
        @NotBlank String fullName,
        @NotBlank @Email String email,
        String department,
        UserRole role) {}
