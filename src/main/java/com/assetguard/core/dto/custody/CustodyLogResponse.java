package com.assetguard.core.dto.custody;

import com.assetguard.core.model.shared.AssetCondition;
import com.assetguard.core.model.shared.CustodyStatus;
import java.time.Instant;
import java.util.UUID;

public record CustodyLogResponse(
        UUID id,
        UUID assetId,
        UUID custodianId,
        UUID assignedByUserId,
        Instant checkoutDate,
        AssetCondition checkoutCondition,
        Instant acceptanceDate,
        String signatureHash,
        Instant checkinDate,
        AssetCondition checkinCondition,
        CustodyStatus status) {}
