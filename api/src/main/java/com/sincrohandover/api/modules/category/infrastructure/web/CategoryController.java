package com.sincrohandover.api.modules.category.infrastructure.web;


import com.sincrohandover.api.modules.category.application.dto.CategoryRequest;
import com.sincrohandover.api.modules.category.application.dto.CategoryResponse;
import com.sincrohandover.api.modules.category.application.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    /**
     * Crea una nueva categoría en el sistema.
     *
     * @param request Payload auditado por validaciones Jakarta (@Valid).
     * @return 201 Created con cabecera Location y el body enriquecido.
     */
    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@Valid @RequestBody CategoryRequest request){
        log.info("HTTP POST /api/v1/categories - Iniciando creacion de categorias");

        CategoryResponse response = categoryService.createCategory(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        log.debug("Categoria creado. Location: {}", location);

        return ResponseEntity.created(location).body(response);
    }
}

