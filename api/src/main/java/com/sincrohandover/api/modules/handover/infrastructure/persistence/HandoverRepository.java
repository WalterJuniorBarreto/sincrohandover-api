package com.sincrohandover.api.modules.handover.infrastructure.persistence;

import com.sincrohandover.api.modules.handover.domain.model.Handover;
import com.sincrohandover.api.modules.handover.domain.model.HandoverStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface HandoverRepository extends JpaRepository<Handover, UUID> {

    /**
     * Busca todas las entregas (handovers) asociadas a un proyecto específico.
     * Spring Data navega automáticamente por la relación para comparar el ID.
     *
     * @param projectId Identificador único del proyecto.
     * @return Lista de entregas pertenecientes al proyecto.
     */
    List<Handover> findByProjectId(UUID projectId);

    /**
     * Filtra las entregas según su estado en el ciclo de vida.
     *
     * @param status Estado de la entrega (ej. DRAFT, COMPLETED).
     * @return Lista de entregas que coinciden con el estado indicado.
     */
    List<Handover> findByStatus(HandoverStatus status);
}
