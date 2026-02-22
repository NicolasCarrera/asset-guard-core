package com.assetguard.core.dto.maintenance;

import com.assetguard.core.model.shared.WorkOrderStatus;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public record WorkOrderResponse(
        UUID id,
        UUID assetId,
        UUID reportedByUserId,
        UUID technicianId,
        String issueDescription,
        BigDecimal interventionCost,
        Instant startDate,
        Instant completionDate,
        WorkOrderStatus status,
        Instant createdAt) {}
