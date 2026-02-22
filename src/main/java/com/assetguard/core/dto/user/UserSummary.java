package com.assetguard.core.dto.user;

import com.assetguard.core.model.shared.UserRole;
import java.util.UUID;

public record UserSummary(UUID id, String employeeId, String fullName, String email, UserRole role) {}
