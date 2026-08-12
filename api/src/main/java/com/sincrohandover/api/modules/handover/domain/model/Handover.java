package com.sincrohandover.api.modules.handover.domain.model;

import com.sincrohandover.api.modules.category.domain.model.Category;
import com.sincrohandover.api.modules.project.domain.model.Project;
import com.sincrohandover.api.modules.user.domain.model.AppUser;
import com.sincrohandover.api.shared.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;


/**
 * Entidad de Dominio Transaccional: Handover
 *
 * Centraliza la información del proceso de entrega. Extiende de BaseEntity
 * para heredar el ID (UUID) y la precisión temporal (createdAt en UTC).
 */
@Entity
@Table(name = "handover")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Handover extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private AppUser author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 30)
    private HandoverStatus status;


    /**
     * Payload dinámico de la entrega.
     * En Hibernate 6, @JdbcTypeCode(SqlTypes.JSON) es el estándar recomendado
     * para delegar al motor el mapeo seguro del tipo de dato JSON (o CLOB con
     * validación IS JSON en bases de datos más antiguas).
     */

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "payload", columnDefinition = "JSON")
    private String payload;
}
