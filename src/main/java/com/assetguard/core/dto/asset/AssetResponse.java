package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.AssetCategory;
import com.assetguard.core.model.shared.LifecycleStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record AssetResponse(
        UUID id,
        UUID parentAssetId,
        UUID modelId,
        String assetTag,
        String serialNumber,
        LocalDate purchaseDate,
        BigDecimal purchaseCost,
        BigDecimal residualValue,
        LifecycleStatus lifecycleStatus,
        String manufacturer,
        String modelName,
        AssetCategory category,
        Instant createdAt,
        Instant updatedAt) {}
