package com.sincrohandover.api.modules.user.infrastructure.web;

import com.sincrohandover.api.modules.user.application.dto.AppUserRequest;
import com.sincrohandover.api.modules.user.application.dto.AppUserResponse;
import com.sincrohandover.api.modules.user.application.service.AppUserService;
import com.sincrohandover.api.modules.user.domain.model.AppUser;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * Endpoint de entrada (Infraestructura / Web) para la gestión de usuarios.
 * Mantiene la cohesión del módulo 'user' en la arquitectura de Monolito Modular.
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class AppUserController {

    private final AppUserService appUserService;

    /**
     * Crea un nuevo usuario en el sistema.
     *
     * @param request Payload auditado contra ataques de inyección y campos vacíos.
     * @return 201 Created con el DTO del usuario persistido.
     */
    @PostMapping
    public ResponseEntity<AppUserResponse> createUser(@Valid @RequestBody AppUserRequest request){
        log.info("HTTP POST /api/v1/users - Iniciando creacion de usuario");
        AppUserResponse response = appUserService.createUser(request);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{email}")
                .buildAndExpand(response.email())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    /**
     * Recupera un usuario basándose en su correo electrónico.
     *
     * @param email Correo electrónico. Es validado por Regex antes de procesarse.
     * @return 200 OK con el DTO del usuario encontrado.
     */
    @GetMapping("/{email}")
    public ResponseEntity<AppUserResponse> getUserByEmail(
            @PathVariable
            @Email(message = "El formato de correo en la URL no es valido")
            String email
    )
    {
        log.info("HTTP GET /api/v1/users/{} - BUscando usuario", email);
        AppUserResponse response = appUserService.getByEmail(email);
        return ResponseEntity.ok(response);

    }
}
