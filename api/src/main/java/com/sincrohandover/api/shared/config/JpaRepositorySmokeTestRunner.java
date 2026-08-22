package com.sincrohandover.api.shared.config;


import com.sincrohandover.api.modules.category.infrastructure.persistence.CategoryRepository;
import com.sincrohandover.api.modules.handover.infrastructure.persistence.HandoverRepository;
import com.sincrohandover.api.modules.project.infrastructure.persistence.ProjectRepository;
import com.sincrohandover.api.modules.user.infrastructure.persistence.AppUserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JpaRepositorySmokeTestRunner  implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ProjectRepository projectRepository;
    private final AppUserRepository appUserRepository;
    private final HandoverRepository handoverRepository;


    @Override
    public void run(String... args){
        log.info("================================================");
        log.info("QA Phase: Iniciando JPA Smoke Test");

        try {
            long categories = categoryRepository.count();
            long projects = projectRepository.count();
            long users = appUserRepository.count();
            long handovers = handoverRepository.count();

            log.info("COntexto de Spring Data IOC Inicializando correctamente");
            log.info("Estado de las tablas en Oracle DB");
            log.info("  - Categories: {}", categories);
            log.info("  - Proyectos: {}", projects);
            log.info("  - Usuarios: {}", users);
            log.info("  - Handovers: {}", handovers);
            log.info("===============================================");
        } catch (Exception e){
            log.error("Fallo critico en el SMoke Test de JPA Repository: {}", e.getMessage());
            throw e;
        }
    }
}
