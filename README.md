# My First Backend

Una aplicación backend desarrollada con Spring Boot que incluye integración con OpenAI y base de datos PostgreSQL.

## 🚀 Características

- **Spring Boot 3.5.6** con Java 21
- **Base de datos PostgreSQL** con JPA/Hibernate
- **Integración con OpenAI** usando LangChain4j
- **API REST** documentada con OpenAPI/Swagger
- **Validación de datos** con Spring Boot Validation
- **Perfiles de configuración** para desarrollo y producción
- **Dockerización** completa con multi-stage build

## 📋 Requisitos

- Java 21 (Eclipse Adoptium recomendado)
- PostgreSQL 12+
- Docker y Docker Compose (opcional)
- Gradle 8.6+ (incluido wrapper)

## 🛠️ Instalación y Configuración

### Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto con las siguientes variables:

```env
# Base de datos
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/my_first_backend_db
SPRING_DATASOURCE_USERNAME=tu_usuario
SPRING_DATASOURCE_PASSWORD=tu_password

# OpenAI API
OPENAI_API_KEY=tu_openai_api_key
```

### Desarrollo Local

1. **Clona el repositorio:**
   ```bash
   git clone <url-del-repositorio>
   cd my-first-backend
   ```

2. **Configura PostgreSQL:**
   - Instala PostgreSQL localmente
   - Crea una base de datos llamada `my_first_backend_db`
   - Configura las variables de entorno

3. **Ejecuta la aplicación:**
   ```bash
   ./gradlew bootRun
   ```

   La aplicación estará disponible en: http://localhost:8090

### Con Docker Compose

```bash
docker-compose up -d
```

## 📚 API Documentation

Una vez que la aplicación esté ejecutándose, puedes acceder a la documentación de la API en:

- **Swagger UI:** http://localhost:8090/swagger-ui.html
- **OpenAPI JSON:** http://localhost:8090/v3/api-docs

## 🏗️ Estructura del Proyecto

```
src/
├── main/
│   ├── java/com/platzi/
│   │   └── [código fuente]
│   └── resources/
│       ├── application.properties
│       ├── application-dev.properties
│       ├── application-prod.properties
│       └── data.sql
└── test/
    └── [tests]
```

## 🚀 Despliegue

### Docker

```bash
# Construir imagen
docker build -t my-first-backend .

# Ejecutar contenedor
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=tu_url_db \
  -e SPRING_DATASOURCE_USERNAME=tu_usuario \
  -e SPRING_DATASOURCE_PASSWORD=tu_password \
  -e OPENAI_API_KEY=tu_api_key \
  my-first-backend
```

### Producción

La aplicación está configurada para usar el perfil `prod` automáticamente cuando se detecta el entorno de producción.

## 🧪 Testing

```bash
# Ejecutar todos los tests
./gradlew test

# Ejecutar tests con reporte
./gradlew test jacocoTestReport
```

## 📝 Notas de Desarrollo

- El perfil `dev` usa el puerto 8090 y configuraciones de desarrollo
- El perfil `prod` usa el puerto 8080 y configuraciones optimizadas
- Los datos iniciales se cargan automáticamente desde `data.sql`
- La aplicación usa Hibernate con `create-drop` en desarrollo y `update` en producción

## 🤝 Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.