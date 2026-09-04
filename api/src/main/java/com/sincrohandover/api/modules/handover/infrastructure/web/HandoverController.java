package com.sincrohandover.api.modules.handover.infrastructure.web;

import com.sincrohandover.api.modules.handover.application.dto.HandoverRequest;
import com.sincrohandover.api.modules.handover.application.dto.HandoverResponse;
import com.sincrohandover.api.modules.handover.application.service.HandoverService;
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
}
