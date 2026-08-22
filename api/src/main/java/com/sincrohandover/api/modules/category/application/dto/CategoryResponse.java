package com.sincrohandover.api.modules.category.application.dto;

import java.time.Instant;
import java.util.UUID;

/**
 * DTO de Salida para Categorías.
 *
 * NOTA ARQUITECTÓNICA (Lean Model):
 * El modelo de transferencia se limita a 'id', 'name' y 'createdAt'.
 * La columna 'description' queda estrictamente fuera para optimizar el peso del JSON en red.
 */
public record CategoryResponse (
        UUID id,
        String name,
        Instant createdAt
){ }
