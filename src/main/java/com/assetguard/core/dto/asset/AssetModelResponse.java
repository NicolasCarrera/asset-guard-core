package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.AssetCategory;
import com.assetguard.core.model.shared.RecordStatus;
import java.util.UUID;

public record AssetModelResponse(
        UUID id,
        AssetCategory category,
        String manufacturer,
        String modelName,
        Integer depreciationYears,
        RecordStatus recordStatus) {}
