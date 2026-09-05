package com.sincrohandover.api.modules.handover.infrastructure.persistence;

import com.sincrohandover.api.modules.handover.domain.model.Handover;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface HandoverRepository extends JpaRepository<Handover, UUID> {

    /**
     * DBA & Performance: Consulta dinámica paginada.
     * Evalúa los parámetros; si son nulos, ignora el filtro.
     * Se inyecta Pageable para forzar sentencias LIMIT/OFFSET a nivel de SQL.
     */
    @Query("SELECT h FROM Handover h WHERE " +
            "(:projectId IS NULL OR h.project.id = :projectId) AND " +
            "(:status IS NULL OR h.status = :status)")
    Page<Handover> findByFiltersDynamically(
            @Param("projectId") UUID projectId,
            @Param("status") String status,
            Pageable pageable
    );


}
