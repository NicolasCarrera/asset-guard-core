package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.LifecycleStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record AssetUpdateRequest(
        @NotNull LifecycleStatus lifecycleStatus,
        @DecimalMin(value = "0.0") BigDecimal residualValue) {}
