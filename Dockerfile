# Usar una imagen base de OpenJDK 17
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo
WORKDIR /app

# Copiar el jar del proyecto al contenedor
COPY target/myapp.jar myapp.jar

# Exponer el puerto en el que Spring Boot está corriendo
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "myapp.jar"]