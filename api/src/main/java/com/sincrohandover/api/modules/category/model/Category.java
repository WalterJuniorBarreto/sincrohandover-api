package com.sincrohandover.api.modules.category.model;


import com.sincrohandover.api.shared.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category  extends BaseEntity {
    

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    // Nota Arquitectónica: Modelo optimizado intencionalmente.
    // No se incluye columna de descripción para mantener el payload esbelto.
}