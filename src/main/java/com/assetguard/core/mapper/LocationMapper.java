package com.assetguard.core.mapper;

import com.assetguard.core.dto.location.LocationRequest;
import com.assetguard.core.dto.location.LocationResponse;
import com.assetguard.core.model.location.Location;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface LocationMapper {

    @Mapping(target = "parentLocation", ignore = true)
    Location toEntity(LocationRequest request);

    @Mapping(source = "parentLocation.id", target = "parentLocationId")
    LocationResponse toResponse(Location location);

    List<LocationResponse> toResponseList(List<Location> locations);

    @Mapping(target = "parentLocation", ignore = true)
    void updateFromRequest(LocationRequest request, @MappingTarget Location location);
}
