package com.assetguard.core.dto.maintenance;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record WorkOrderRequest(
        @NotNull UUID assetId,
        @NotNull UUID reportedByUserId,
        @NotNull UUID technicianId,
        @NotBlank String issueDescription,
        @DecimalMin(value = "0.0") BigDecimal interventionCost) {}
