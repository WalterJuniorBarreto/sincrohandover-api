package com.sincrohandover.api.modules.category.domain.model;


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


/**
 * Entidad de Dominio: Category
 *
 * Representa la tabla física 'category' en Oracle Database.
 *
 * NOTA ARQUITECTÓNICA (Lean Model / YAGNI):
 * El modelo de datos de esta entidad fue ajustado estrictamente para
 * mantener únicamente el 'id', 'createdAt' (heredados) y 'name'.
 * La columna 'description' fue removida intencionalmente del modelo
 * para optimizar el peso de las consultas SQL, reducir el consumo de
 * memoria en la JVM y agilizar la transferencia del payload.
 */

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Category extends BaseEntity{

    @Column(name = "name", nullable = false, length = 100)
    private String name;

}