package com.assetguard.core.mapper;

import com.assetguard.core.dto.asset.AssetLocationLogResponse;
import com.assetguard.core.model.asset.AssetLocationLog;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssetLocationLogMapper {

    @Mapping(source = "asset.id", target = "assetId")
    @Mapping(source = "location.id", target = "locationId")
    @Mapping(source = "transferredBy.id", target = "transferredByUserId")
    AssetLocationLogResponse toResponse(AssetLocationLog log);

    List<AssetLocationLogResponse> toResponseList(List<AssetLocationLog> logs);
}
