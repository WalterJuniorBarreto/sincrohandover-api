package com.sincrohandover.api.modules.category.application.service;

import com.sincrohandover.api.modules.category.application.dto.CategoryRequest;
import com.sincrohandover.api.modules.category.application.dto.CategoryResponse;

public interface CategoryService {
    CategoryResponse createCategory(CategoryRequest request);
}
