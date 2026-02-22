package com.assetguard.core.dto.location;

import com.assetguard.core.model.shared.LocationType;
import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record LocationRequest(
        @NotBlank String name, LocationType type, UUID parentLocationId) {}
