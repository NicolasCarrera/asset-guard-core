package com.assetguard.core.dto.custody;

import com.assetguard.core.model.shared.AssetCondition;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CustodyCheckinRequest(
        @NotNull UUID custodyLogId,
        @NotNull UUID assetId,
        @NotNull AssetCondition checkinCondition) {}
