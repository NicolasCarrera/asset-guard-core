package com.assetguard.core.mapper;

import com.assetguard.core.dto.maintenance.WorkOrderRequest;
import com.assetguard.core.dto.maintenance.WorkOrderResponse;
import com.assetguard.core.model.maintenance.MaintenanceWorkOrder;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface MaintenanceWorkOrderMapper {

    @Mapping(target = "asset", ignore = true)
    @Mapping(target = "reportedBy", ignore = true)
    @Mapping(target = "technician", ignore = true)
    @Mapping(target = "status", ignore = true)
    MaintenanceWorkOrder toEntity(WorkOrderRequest request);

    @Mapping(source = "asset.id", target = "assetId")
    @Mapping(source = "reportedBy.id", target = "reportedByUserId")
    @Mapping(source = "technician.id", target = "technicianId")
    WorkOrderResponse toResponse(MaintenanceWorkOrder workOrder);

    List<WorkOrderResponse> toResponseList(List<MaintenanceWorkOrder> workOrders);

    void updateFromRequest(WorkOrderRequest request, @MappingTarget MaintenanceWorkOrder workOrder);
}
