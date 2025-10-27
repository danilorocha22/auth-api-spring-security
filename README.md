📌 README — Auth API Spring Security + Docker + Perfis Dev e Prod

# 📌 Auth API Spring Security + Docker + Perfis Dev e Prod

Este projeto é uma API de autenticação e segurança baseada em Spring Boot, utilizando Spring Security com JWT, persistência via JPA/Hibernate e PostgreSQL, tudo empacotado para execução em contêineres Docker, com suporte a perfis de Desenvolvimento (DEV) e Produção (PROD).

## ✅ Requisitos

Certifique-se de possuir as seguintes ferramentas instaladas e configuradas:
* **Java 17**
* **Maven 3.9+**
* **Docker e Docker Compose**
* VS Code (opcional, com a extensão Docker)

---

## ⚙️ Configuração de Variáveis de Ambiente (SO/Shell)

Seu `docker-compose.yml` utiliza a sintaxe `${VARIAVEL}` para ler variáveis diretamente do **sistema operacional (shell)**. Elas devem ser configuradas *antes* de executar o Docker Compose.

| Variável | Descrição | Exemplo de Valor (Dev) |
| :--- | :--- | :--- |
| **`DB_NAME`** | Nome do banco de dados PostgreSQL. | `mydatabase` |
| **`DB_USER`** | Usuário do banco de dados PostgreSQL. | `postgres` |
| **`DB_PASSWORD`** | Senha do banco de dados PostgreSQL. | `admin` |
| **`ENV_FILE`** | Caminho para o arquivo `.env` específico do ambiente. | `.env.dev` ou `.env.prod` |

**Como configurar (Exemplo Linux/macOS/Git Bash):**

```bash
# Configure as variáveis abaixo em seu terminal antes de executar o Docker Compose
export DB_NAME=mydatabase
export DB_USER=postgres
export DB_PASSWORD=admin
# Defina o arquivo de ambiente que será carregado (para o ambiente DEV)
export ENV_FILE=.env.dev


🧹 1. Limpeza e build do projeto

Executar na raiz do projeto:
* mvn clean package -DskipTests

O arquivo JAR será gerado em:
* target/app.jar
<br>

🐳 2. Gerenciamento com Docker

Listar containers em execução
* docker ps

Parar um container específico
* docker stop <CONTAINER_ID>

Parar todos os containers ativos
* docker stop $(docker ps -q)

Remover containers parados
* docker container prune -f
<br>


🌱 3. Ambiente de Desenvolvimento (DEV)
📄 3.1 Arquivo .env.dev
O Docker Compose usará este arquivo para configurar o container em modo dev.

Para subir o ambiente DEV:
* docker-compose --env-file .env.dev up --build

Para rodar em background:
* docker-compose --env-file .env.dev up --build -d

Ver logs:
* docker logs -f spring_backend

Testar disponibilidade da aplicação:
* curl http://localhost:8080/actuator/health
<br>


🚀 4. Ambiente de Produção (PROD)
📄 4.1 Arquivo .env.prod
Deve conter as variáveis de produção, incluindo credenciais do banco.

Subir containers em modo PROD:
* docker-compose --env-file .env.prod up --build

Rodar em background:
* docker-compose --env-file .env.prod up --build -d
<br>


🧽 5. Comandos úteis

Ver logs do PostgreSQL
* docker logs -f postgres

Acessar o banco via psql dentro do container
* docker exec -it postgres psql -U postgres

Derrubar containers e volumes
* docker-compose down -v
<br>


✅ Fluxo completo para DEV
* mvn clean package -DskipTests
* docker-compose --env-file .env.dev up --build -d
* curl http://localhost:8080/actuator/health ou http://localhost:8080/actuator/health | ConvertFrom-Json

<br>


✅ Fluxo completo para PROD
* mvn clean package -DskipTests
* docker-compose --env-file .env.prod up --build -d
* curl http://localhost:8080/actuator/health ou http://localhost:8080/actuator/health | ConvertFrom-Json
