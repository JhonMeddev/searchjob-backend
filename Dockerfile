FROM postgres

# Variáveis de ambiente para o PostgreSQL
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=jhonatan
ENV POSTGRES_DB=searchjobs
ENV POSTGRES_HOST=localhost
ENV POSTGRES_PORT=5432

# Copiar o script SQL para criar o banco de dados
COPY createdb.sql /docker-entrypoint-initdb.d/

# Instalação do PostgreSQL client
RUN apt-get update && \
    apt install openjdk-17-jre -y && \
    apt install openjdk-17-jdk -y && \
    apt-get install maven -y

# Configuração do diretório de trabalho
WORKDIR /app

# Copiar o conteúdo do diretório atual para o contêiner
COPY . .


# Instalar o Maven e construir o projeto
RUN mvn install -DskipTests

# Expor a porta 8080
EXPOSE 8080

# Comando de execução do aplicativo
CMD ["java", "-jar", "target/searchjob-0.0.1-SNAPSHOT.jar"]
