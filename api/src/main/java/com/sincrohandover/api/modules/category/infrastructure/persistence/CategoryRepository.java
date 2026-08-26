package com.sincrohandover.api.modules.category.infrastructure.persistence;

import com.sincrohandover.api.modules.category.domain.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}



