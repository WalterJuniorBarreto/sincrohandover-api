package com.sincrohandover.api.modules.category.application.service.impl;

import com.sincrohandover.api.modules.category.application.service.AppUserService;
import com.sincrohandover.api.modules.user.application.dto.AppUserRequest;
import com.sincrohandover.api.modules.user.application.dto.AppUserResponse;
import com.sincrohandover.api.modules.user.application.mapper.AppUserMapper;
import com.sincrohandover.api.modules.user.domain.model.AppUser;
import com.sincrohandover.api.modules.user.infrastructure.persistence.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private final AppUserRepository appUserRepository;
    private final AppUserMapper appUserMapper;


    @Override
    @Transactional
    public AppUserResponse createUser(AppUserRequest request){
        log.debug("Intentando crear un nuevo usuario con email: {}", request.email());

        appUserRepository.findByEmail(request.email()).ifPresent(existingUser -> {
            log.warn("Intento de registro fallido: el mail {} ya existe", request.email());
            throw new RuntimeException("El usuaio con este correo electronico ya esta registrado");
        });


        AppUser user = appUserMapper.toEntity(request);
        AppUser savedUser = appUserRepository.save(user);

        log.info("Usuario creado exitosamente con ID: {}", savedUser.getId());
        return appUserMapper.toResponse(savedUser);
    }


    @Override
    @Transactional(readOnly = true)
    public AppUserResponse getByEmail(String email){
        log.debug("BUscando usuario por email: {}", email);

        AppUser user = appUserRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("Busqueda fallida: No se enocntro usuario con el email {}", email);
                    return new RuntimeException("User not found");
                });


        return appUserMapper.toResponse(user);
    }
}
