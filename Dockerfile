# --------------------- ETAPA 1: LA FÁBRICA 🏭 ---------------------
# "Empecemos con una máquina que ya tiene Gradle y Java 21 instalados.
# La llamaremos 'builder' para referirnos a ella más tarde."
FROM gradle:8.6.0-jdk21-alpine AS builder

# "Dentro de esa máquina, creemos una carpeta llamada 'app' y entremos en ella."
WORKDIR /app

# "Copiemos los archivos de configuración de Gradle y luego el código fuente."
# (Se hace en dos pasos para que Docker sea más rápido en futuras compilaciones)
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle
COPY src /app/src

# ¡LA SOLUCIÓN! Damos permiso de ejecución al wrapper de Gradle.
RUN chmod +x gradlew

# Ahora sí, el comando de construcción funcionará
RUN ./gradlew bootJar --no-daemon

# --------------------- ETAPA 2: EL COCHE 🚗 ---------------------
# "Ahora, olvidémonos de la fábrica. Empecemos con una máquina nueva y limpia
# que solo tiene lo mínimo para ejecutar Java (JRE)."
FROM eclipse-temurin:21-jre-alpine

# "Le decimos al mundo que este coche (contenedor) usará el puerto 8080."
EXPOSE 8080

# "Vayamos a la fábrica ('builder'), encontremos el archivo .jar que creamos
# (está en build/libs/), y copiémoslo a nuestra máquina limpia.
# Para que sea más fácil, lo renombraremos a 'app.jar'."
ARG JAR_FILE=build/libs/*.jar
COPY --from=builder /app/${JAR_FILE} app.jar

# "Esta es la instrucción final. Cuando alguien 'encienda' este coche,
# el único comando que se ejecutará será para arrancar nuestra aplicación
# en modo producción."
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "app.jar"]