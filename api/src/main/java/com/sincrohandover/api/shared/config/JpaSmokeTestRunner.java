package com.sincrohandover.api.shared.config;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * Runner temporal para el Smoke Test de la Tarea 1.4.
 * Evalúa que Hibernate puede resolver las entidades y consultas básicas tras la validación DDL.
 */

@Slf4j
@Component
public class JpaSmokeTestRunner implements CommandLineRunner {

    private final EntityManager entityManager;

    public JpaSmokeTestRunner(EntityManager entityManager) { this.entityManager = entityManager; }

    @Override
    @Transactional(readOnly = true)
    public void run(String... args) throws Exception {
        log.info("Iniciando smoke test de jpa y entitymanager");
        try{
            entityManager.createQuery("SELECT c.id FROM Category c").setMaxResults(1).getResultList();
            entityManager.createQuery("SELECT c.id FROM Handover c").setMaxResults(1).getResultList();
            log.info("smoke test superado: entidades mapeadas correctamente y el esquema fisico cuadra a la perfeccion");
        } catch (Exception e){
            log.error("Fallo critico en el smoke test de jpa: {}", e.getMessage());
            throw e;
        }

    }
}
