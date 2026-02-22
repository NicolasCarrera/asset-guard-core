package com.assetguard.core.dto.asset;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CapitalizationRequest(
        @NotNull UUID assetId,
        UUID workOrderId,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal amount,
        @NotBlank String description,
        @NotNull LocalDate appliedDate) {}
