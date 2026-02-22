package com.assetguard.core.dto.asset;

import com.assetguard.core.model.shared.AssetCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssetModelRequest(
        @NotNull AssetCategory category,
        @NotBlank String manufacturer,
        @NotBlank String modelName,
        @NotNull @Min(1) Integer depreciationYears) {}
