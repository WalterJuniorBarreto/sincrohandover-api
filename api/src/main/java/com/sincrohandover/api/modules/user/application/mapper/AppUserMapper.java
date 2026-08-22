package com.sincrohandover.api.modules.user.application.mapper;

import com.sincrohandover.api.modules.user.application.dto.AppUserRequest;
import com.sincrohandover.api.modules.user.application.dto.AppUserResponse;
import com.sincrohandover.api.modules.user.domain.model.AppUser;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper estandarizado para la entidad AppUser.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface AppUserMapper {

    AppUserResponse toResponse(AppUser appUser);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AppUser toEntity(AppUserRequest request);
}
