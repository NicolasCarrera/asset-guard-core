package com.assetguard.core.dto.custody;

import com.assetguard.core.model.shared.AssetCondition;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record CustodyCheckoutRequest(
        @NotNull UUID assetId,
        @NotNull UUID custodianId,
        @NotNull UUID assignedByUserId,
        @NotNull AssetCondition checkoutCondition) {}
