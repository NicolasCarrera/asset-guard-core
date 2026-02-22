package com.assetguard.core.mapper;

import com.assetguard.core.dto.asset.AssetModelRequest;
import com.assetguard.core.dto.asset.AssetModelResponse;
import com.assetguard.core.model.asset.AssetModel;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AssetModelMapper {

    AssetModel toEntity(AssetModelRequest request);

    AssetModelResponse toResponse(AssetModel model);

    List<AssetModelResponse> toResponseList(List<AssetModel> models);

    void updateFromRequest(AssetModelRequest request, @MappingTarget AssetModel model);
}
