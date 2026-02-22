package com.assetguard.core.mapper;

import com.assetguard.core.dto.asset.CapitalizationRequest;
import com.assetguard.core.dto.asset.CapitalizationResponse;
import com.assetguard.core.model.asset.AssetCapitalization;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssetCapitalizationMapper {

    @Mapping(target = "asset", ignore = true)
    @Mapping(target = "workOrder", ignore = true)
    AssetCapitalization toEntity(CapitalizationRequest request);

    @Mapping(source = "asset.id", target = "assetId")
    @Mapping(source = "workOrder.id", target = "workOrderId")
    CapitalizationResponse toResponse(AssetCapitalization capitalization);

    List<CapitalizationResponse> toResponseList(List<AssetCapitalization> capitalizations);

    void updateFromRequest(CapitalizationRequest request, @MappingTarget AssetCapitalization capitalization);
}
