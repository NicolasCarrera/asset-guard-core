package com.assetguard.core.mapper;

import com.assetguard.core.dto.asset.AssetRequest;
import com.assetguard.core.dto.asset.AssetResponse;
import com.assetguard.core.dto.asset.AssetSummary;
import com.assetguard.core.dto.asset.AssetUpdateRequest;
import com.assetguard.core.model.asset.Asset;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssetMapper {

    @Mapping(target = "parentAsset", ignore = true)
    @Mapping(target = "model", ignore = true)
    Asset toEntity(AssetRequest request);

    @Mapping(source = "parentAsset.id", target = "parentAssetId")
    @Mapping(source = "model.id", target = "modelId")
    @Mapping(source = "model.manufacturer", target = "manufacturer")
    @Mapping(source = "model.modelName", target = "modelName")
    @Mapping(source = "model.category", target = "category")
    AssetResponse toResponse(Asset asset);

    AssetSummary toSummary(Asset asset);

    List<AssetSummary> toSummaryList(List<Asset> assets);

    @Mapping(target = "parentAsset", ignore = true)
    @Mapping(target = "model", ignore = true)
    @Mapping(target = "assetTag", ignore = true)
    @Mapping(target = "serialNumber", ignore = true)
    @Mapping(target = "purchaseDate", ignore = true)
    @Mapping(target = "purchaseCost", ignore = true)
    void updateFromRequest(AssetUpdateRequest request, @MappingTarget Asset asset);
}
