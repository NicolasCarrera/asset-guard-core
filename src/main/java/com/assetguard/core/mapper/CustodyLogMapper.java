package com.assetguard.core.mapper;

import com.assetguard.core.dto.custody.CustodyCheckinRequest;
import com.assetguard.core.dto.custody.CustodyCheckoutRequest;
import com.assetguard.core.dto.custody.CustodyLogResponse;
import com.assetguard.core.model.custody.CustodyLog;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CustodyLogMapper {

    @Mapping(target = "asset", ignore = true)
    @Mapping(target = "assignedBy", ignore = true)
    @Mapping(target = "custodian", ignore = true)
    @Mapping(target = "status", ignore = true)
    CustodyLog toEntity(CustodyCheckoutRequest request);

    @Mapping(source = "asset.id", target = "assetId")
    @Mapping(source = "custodian.id", target = "custodianId")
    @Mapping(source = "assignedBy.id", target = "assignedByUserId")
    CustodyLogResponse toResponse(CustodyLog log);

    List<CustodyLogResponse> toResponseList(List<CustodyLog> logs);

    @Mapping(target = "asset", ignore = true)
    @Mapping(target = "checkinCondition", source = "checkinCondition")
    void updateFromCheckin(CustodyCheckinRequest request, @MappingTarget CustodyLog log);
}
