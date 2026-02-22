package com.assetguard.core.dto.asset;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CapitalizationResponse(
        UUID id,
        UUID assetId,
        UUID workOrderId,
        BigDecimal amount,
        String description,
        LocalDate appliedDate) {}
