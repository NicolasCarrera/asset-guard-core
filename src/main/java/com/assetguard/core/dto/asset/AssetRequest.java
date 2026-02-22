package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.LifecycleStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record AssetRequest(
        @NotNull UUID modelId,
        UUID parentAssetId,
        @NotBlank String assetTag,
        String serialNumber,
        @NotNull LocalDate purchaseDate,
        @NotNull @DecimalMin(value = "0.0", inclusive = false) BigDecimal purchaseCost,
        @DecimalMin(value = "0.0") BigDecimal residualValue) {}
