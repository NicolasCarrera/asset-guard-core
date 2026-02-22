package com.assetguard.core.mapper;

import com.assetguard.core.dto.user.UserRequest;
import com.assetguard.core.dto.user.UserResponse;
import com.assetguard.core.dto.user.UserSummary;
import com.assetguard.core.model.user.User;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User toEntity(UserRequest request);

    UserResponse toResponse(User user);

    UserSummary toSummary(User user);

    List<UserSummary> toSummaryList(List<User> users);

    void updateFromRequest(UserRequest request, @MappingTarget User user);
}
