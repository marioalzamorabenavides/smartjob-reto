
# Api Creacion de usuarios

## Descripción

Esta API proporciona funcionalidades para la gestión de usuarios, incluyendo la creación, actualización, activación/desactivación, y listado de usuarios. La API utiliza tokens JWT para la autenticación y autorización.

## Requisitos Previos

- **Java 21**
- **Maven 3.9.6**
- **Docker (opcional, si deseas usar Docker)**
- **Postman o cualquier cliente REST para probar la API**

## Instalación

### Opción 1: Ejecutar Localmente con Maven

1. Clona este repositorio:
   ```bash
   git clone https://github.com/marioalzamorabenavides/smartjob-reto.git
   cd smartjob-reto
   ```

2. Construye el proyecto con Maven:
   ```bash
   mvn clean install
   ```

3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

### Opción 2: Ejecutar con Docker

1. Clona este repositorio:
   ```bash
   git clone https://github.com/marioalzamorabenavides/smartjob-reto.git
   cd smartjob-reto
   ```

2. Construye y levanta la aplicación con Docker Compose:
   ```bash
   docker-compose up --build -d
   ```

3. Accede a la API en `http://localhost:9090`.

## Configuración

### Variables de Entorno

Si es necesario, puedes configurar las siguientes variables de entorno en tu `application.properties` o directamente en tu entorno:

- `SPRING_PROFILES_ACTIVE`: El perfil de Spring activo (por defecto, `prod`).
- `DB_NAME`: Nombre de la base de datos en memoria
- `DB_USER`: Nombre de usuario de la base de datos
- `DB_PASSWORD`: Password de la base de datos
- `H2_CONSOLE`: Flag para exponer ui de h2
- `PASSWORD_REGEX`: El regex configurable de la validacion del password.
- `PASSWORD_MESSAGE`: El mensaje configurable de la validacion del password.
- `APPLICATION_PORT`: Puerto donde va a correr la aplicacion
- `app.jwt-secret`: La clave secreta utilizada para firmar los tokens JWT.
- `app.jwt-expiration-milliseconds`: La duración de los tokens JWT en milisegundos.
