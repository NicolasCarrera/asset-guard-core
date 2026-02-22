package com.assetguard.core.dto.asset;

import java.time.Instant;
import java.util.UUID;

public record AssetLocationLogResponse(
        UUID id,
        UUID assetId,
        UUID locationId,
        UUID transferredByUserId,
        Instant arrivalDate,
        Instant departureDate) {}
