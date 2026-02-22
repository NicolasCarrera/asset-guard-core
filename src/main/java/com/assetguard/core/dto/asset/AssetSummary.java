package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.AssetCategory;
import com.assetguard.core.model.shared.LifecycleStatus;
import java.util.UUID;

public record AssetSummary(
        UUID id,
        String assetTag,
        String serialNumber,
        String manufacturer,
        String modelName,
        AssetCategory category,
        LifecycleStatus lifecycleStatus) {}
