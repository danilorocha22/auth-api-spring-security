📌 README — Auth API Spring Security + Docker + Perfis Dev e Prod

✅ Requisitos
Certifique-se de possuir instalados:
* Java 17
* Maven 3.9+
* Docker e Docker Compose
* VS Code (opcional, com extensão Docker)


🧹 1. Limpeza e build do projeto

Executar na raiz do projeto:
* mvn clean package -DskipTests

O arquivo JAR será gerado em:
* target/app.jar


🐳 2. Gerenciamento com Docker

Listar containers em execução
* docker ps

Parar um container específico
* docker stop <CONTAINER_ID>

Parar todos os containers ativos
* docker stop $(docker ps -q)

Remover containers parados
* docker container prune -f


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


🚀 4. Ambiente de Produção (PROD)
📄 4.1 Arquivo .env.prod
Deve conter as variáveis de produção, incluindo credenciais do banco.

Subir containers em modo PROD:
* docker-compose --env-file .env.prod up --build

Rodar em background:
* docker-compose --env-file .env.prod up --build -d


🧽 5. Comandos úteis

Ver logs do PostgreSQL
* docker logs -f postgres

Acessar o banco via psql dentro do container
* docker exec -it postgres psql -U postgres

Derrubar containers e volumes
* docker-compose down -v


✅ Fluxo completo para DEV
* mvn clean package -DskipTests
* docker-compose --env-file .env.dev up --build -d
* curl http://localhost:8080/actuator/health


✅ Fluxo completo para PROD
* mvn clean package -DskipTests
* docker-compose --env-file .env.prod up --build -d
* curl http://localhost:8080/actuator/health