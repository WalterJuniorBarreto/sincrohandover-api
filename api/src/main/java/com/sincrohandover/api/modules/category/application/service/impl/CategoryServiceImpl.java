package com.sincrohandover.api.modules.category.application.service.impl;

import com.sincrohandover.api.modules.category.application.dto.CategoryRequest;
import com.sincrohandover.api.modules.category.application.dto.CategoryResponse;
import com.sincrohandover.api.modules.category.application.mapper.CategoryMapper;
import com.sincrohandover.api.modules.category.application.service.CategoryService;
import com.sincrohandover.api.modules.category.domain.model.Category;
import com.sincrohandover.api.modules.category.infrastructure.persistence.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {


    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;


    @Override
    @Transactional
    public CategoryResponse createCategory(CategoryRequest request){
        Category category = categoryMapper.toEntity(request);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponse(savedCategory);
    }
}


