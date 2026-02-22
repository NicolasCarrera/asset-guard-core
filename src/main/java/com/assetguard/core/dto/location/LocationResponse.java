package com.assetguard.core.dto.location;

import com.assetguard.core.model.shared.LocationType;
import com.assetguard.core.model.shared.RecordStatus;
import java.util.UUID;

public record LocationResponse(
        UUID id,
        String name,
        LocationType type,
        UUID parentLocationId,
        RecordStatus recordStatus) {}
