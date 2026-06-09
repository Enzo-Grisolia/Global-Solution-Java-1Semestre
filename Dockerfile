# ============================================================
# Etapa 1 - Build: compila o projeto e gera o JAR
# ============================================================
FROM maven:3.9-eclipse-temurin-17 AS build
WORKDIR /app

# Copia primeiro o pom para aproveitar o cache de dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o codigo-fonte e empacota (pula os testes no build da imagem)
COPY src ./src
RUN mvn clean package -DskipTests -B

# ============================================================
# Etapa 2 - Runtime: imagem enxuta apenas com o JRE + o JAR
# ============================================================
FROM eclipse-temurin:17-jre
WORKDIR /app

# O finalName no pom.xml e "app", entao o artefato e app.jar
COPY --from=build /app/target/app.jar app.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "app.jar"]
