package com.assetguard.core.dto.asset;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record LocationTransferRequest(
        @NotNull UUID assetId, @NotNull UUID locationId, @NotNull UUID transferredByUserId) {}
