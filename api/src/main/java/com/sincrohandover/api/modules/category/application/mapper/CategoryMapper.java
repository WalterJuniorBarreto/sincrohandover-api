package com.sincrohandover.api.modules.category.application.mapper;

import com.sincrohandover.api.modules.category.application.dto.CategoryRequest;
import com.sincrohandover.api.modules.category.application.dto.CategoryResponse;
import com.sincrohandover.api.modules.category.domain.model.Category;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

/**
 * Mapper estandarizado para la entidad Category.
 * Genera la implementación de forma automática e inyectable como Bean de Spring.
 */
@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true)
)
public interface CategoryMapper {

    CategoryResponse toResponse(Category category);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Category toEntity(CategoryRequest request);
}


