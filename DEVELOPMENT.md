# Guía de Desarrollo

Esta guía contiene información específica para desarrolladores que trabajen en este proyecto.

## 🚀 Configuración Rápida

### Opción 1: Solo Base de Datos con Docker

Si prefieres ejecutar la aplicación localmente pero usar PostgreSQL en Docker:

```bash
# Levantar solo PostgreSQL
docker-compose -f docker-compose.dev.yml up -d

# Configurar variables de entorno
cp .env.example .env
# Editar .env con tus valores

# Ejecutar aplicación
./gradlew bootRun
```

### Opción 2: Todo con Docker

```bash
# Configurar variables de entorno
cp .env.example .env
# Editar .env con tus valores

# Levantar todo el stack
docker-compose up -d
```

### Opción 3: Desarrollo Completamente Local

```bash
# Instalar PostgreSQL localmente
# Crear base de datos: my_first_backend_db

# Configurar .env con tu instalación local
cp .env.example .env

# Ejecutar aplicación
./gradlew bootRun
```

## 🛠️ Herramientas de Desarrollo

### Acceso a Base de Datos

- **Adminer:** http://localhost:8080 (cuando uses Docker)
- **Credenciales por defecto:**
  - Sistema: PostgreSQL
  - Servidor: postgres (Docker) o localhost (local)
  - Usuario: dev_user
  - Contraseña: dev_password
  - Base de datos: my_first_backend_db

### API Documentation

- **Swagger UI:** http://localhost:8090/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8090/v3/api-docs

## 📝 Comandos Útiles

### Gradle

```bash
# Ejecutar aplicación
./gradlew bootRun

# Ejecutar tests
./gradlew test

# Limpiar build
./gradlew clean

# Generar JAR
./gradlew bootJar

# Ver dependencias
./gradlew dependencies
```

### Docker

```bash
# Ver logs de la aplicación
docker-compose logs -f app

# Ver logs de PostgreSQL
docker-compose logs -f postgres

# Reiniciar solo la aplicación
docker-compose restart app

# Acceder al contenedor de la aplicación
docker-compose exec app sh

# Acceder a PostgreSQL
docker-compose exec postgres psql -U dev_user -d my_first_backend_db
```

## 🔧 Configuración de IDE

### IntelliJ IDEA

1. Importar como proyecto Gradle
2. Configurar SDK a Java 21
3. Habilitar annotation processing
4. Instalar plugins recomendados:
   - Spring Boot
   - Database Navigator

### VS Code

Extensiones recomendadas:
- Extension Pack for Java
- Spring Boot Extension Pack
- PostgreSQL

## 🧪 Testing

### Estructura de Tests

```
src/test/java/
├── integration/     # Tests de integración
├── unit/           # Tests unitarios
└── TestApplication.java
```

### Ejecutar Tests Específicos

```bash
# Tests unitarios
./gradlew test --tests "*Unit*"

# Tests de integración
./gradlew test --tests "*Integration*"

# Test específico
./gradlew test --tests "com.platzi.MyTest"
```

## 🐛 Debugging

### Configuración de Debug

Para debugging remoto, agregar al comando de ejecución:

```bash
./gradlew bootRun --debug-jvm
```

O configurar en `application-dev.properties`:

```properties
# Habilitar debug logging
logging.level.com.platzi=DEBUG
logging.level.org.springframework.web=DEBUG
```

### Logs Útiles

```properties
# Ver SQL queries
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Ver parámetros de queries
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

## 📊 Monitoreo

### Actuator Endpoints

Agregar a `application-dev.properties`:

```properties
management.endpoints.web.exposure.include=health,info,metrics,env
management.endpoint.health.show-details=always
```

Endpoints disponibles:
- http://localhost:8090/actuator/health
- http://localhost:8090/actuator/info
- http://localhost:8090/actuator/metrics

## 🔄 Workflow de Desarrollo

1. **Crear rama:** `git checkout -b feature/nueva-funcionalidad`
2. **Desarrollar:** Hacer cambios y commits frecuentes
3. **Probar:** `./gradlew test`
4. **Verificar:** Revisar con Swagger UI
5. **Commit:** `git commit -m "feat: descripción del cambio"`
6. **Push:** `git push origin feature/nueva-funcionalidad`
7. **PR:** Crear Pull Request

## 🚨 Troubleshooting

### Problemas Comunes

**Error de conexión a base de datos:**
```bash
# Verificar que PostgreSQL esté corriendo
docker-compose ps

# Ver logs de PostgreSQL
docker-compose logs postgres
```

**Puerto ocupado:**
```bash
# Cambiar puerto en application-dev.properties
server.port=8091
```

**Problemas con Gradle:**
```bash
# Limpiar cache de Gradle
./gradlew clean --refresh-dependencies
```