package com.sincrohandover.api.modules.user.infrastructure.persistence;

import com.sincrohandover.api.modules.user.domain.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AppUserRepository extends JpaRepository<AppUser, UUID> {

    /**
     * Busca un usuario por su correo electrónico.
     * Implementa Derived Query Method de Spring Data JPA.
     *
     * @param email El correo electrónico exacto a buscar.
     * @return Un Optional que contiene el AppUser si existe, o vacío si no se encuentra.
     */
    Optional<AppUser> findByEmail(String email);
}
