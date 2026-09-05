package com.sincrohandover.api.modules.handover.infrastructure.web;

import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;
import com.sincrohandover.api.modules.handover.application.service.HandoverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/handovers")
@RequiredArgsConstructor
public class HandoverController {

    private final HandoverService handoverService;

    /**
     * Registra un nuevo traspaso de turno (Handover).
     *
     * @param request Payload inmutable. Sanitizado mediante validaciones Jakarta (@Valid).
     * @return 201 Created con cabecera Location dinámica y el grafo de datos persistido.
     */
    @PostMapping
    public ResponseEntity<HandoverResponse> createHandover(@Valid @RequestBody HandoverRequest request){

        log.info("HTTP POST /api/v1/handovers - Solicitud de creacion de turno recibida para autor {}", request.authorId());
        HandoverResponse response = handoverService.createHandover(request);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();

        log.debug("Handover orquestado correctamente, location asignada: {}", location);
        return ResponseEntity.created(location).body(response);

    }



    /**
     * Obtiene un listado paginado de Handovers.
     *
     * @param projectId (Opcional) Filtro por ID de proyecto.
     * @param status    (Opcional) Filtro por estado del turno (ej. COMPLETED, DRAFT).
     * @param pageable  Inyectado automáticamente por Spring. Protegido por PageableDefault.
     * @return 200 OK con el objeto Page (contenido + metadatos de paginación para Angular).
     */
    @GetMapping
    public ResponseEntity<Page<HandoverResponse>> getHandovers(
            @RequestParam(required = false) UUID projectId,
            @RequestParam(required = false) String status,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)Pageable pageable){

        log.info("HTTP GET /api/v1/handovers = SOlicitando pagina {} con tamaño {}", pageable.getPageNumber(), pageable.getPageSize());

        Page<HandoverResponse> pageResult = handoverService.getHandovers(projectId, status, pageable);
        return ResponseEntity.ok(pageResult);
    }

}





















