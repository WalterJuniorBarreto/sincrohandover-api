#  SincroHandover API (Backend Core)

Este repositorio contiene el núcleo transaccional del ecosistema SincroHandover. Está construido sobre **Spring Boot 3** y **Java 17**, utilizando una arquitectura de **Monolito Modular** para garantizar un equilibrio óptimo entre mantenibilidad, escalabilidad y costos operativos.

##  Prerrequisitos

Para levantar este proyecto en tu máquina local, debes tener instalado:
- **Java 17 (JDK):** Aprovechamos características modernas del lenguaje.
- **Apache Maven 3.9+:** Gestor de dependencias y construcción.
- **Docker o Podman:** Para levantar el contenedor local de Oracle Database.

## Instalación y Despliegue Local

### 1. Levantar la Base de Datos (Oracle DB)
El proyecto requiere una instancia local de Oracle. Ejecuta el siguiente comando para levantar el contenedor con la versión gratuita (FREEPDB1):

```bash
docker run -d -p 1521:1521 --name oracle-freepdb1 -e ORACLE_PASSWORD=MiPassword123 gvenzl/oracle-free:latest# Sincrohandover API - Backend core
