# AgroOrbit Core API

API REST do projeto **AgroOrbit Core**, desenvolvida em **Java com Spring Boot** e persistência em **MySQL**.

> Disciplina: **Microservice and Web Engineering & IT Services** (Prof. Antonio Carlos de Lima Junior) — Global Solution Space Connect 2026.1 — Turma 3SIR.

---

## Objetivo da solução e tema da Global Solution

O **AgroOrbit Core** conecta a economia espacial ao agronegócio brasileiro: usa dados de satélite e meteorologia para gerar **alertas climáticos por propriedade rural**, ajudando o pequeno e médio produtor a reduzir perdas de safra.

Esta API é o **back-end** do ecossistema: gerencia produtores, propriedades, leituras de satélite e alertas climáticos. É a mesma base de dados consumida pelos aplicativos mobile do projeto (Android/Kotlin e Flutter).

---

## Equipe (3SIR)

- Enzo Grisolia de Souza
- Gabriel Borges Medeiros
- Matheus Lion Muzzi

---

## Tecnologias

- Java 17
- Spring Boot 4.0.3 (Web MVC, Data JPA, Validation)
- MySQL 8
- ModelMapper (mapeamento Entity ↔ DTO)
- SpringDoc OpenAPI / Swagger
- Docker / Docker Compose

---

## Modelo de dados (4 entidades)

```
Produtor (1) --< (N) Propriedade (1) --< (N) LeituraSatelite
                              (1) --< (N) AlertaClimatico
```

- **Produtor**: id, nome, cpf, telefone, cooperativa, ativo
- **Propriedade**: id, nome, areaHectares, culturaPrincipal, latitude, longitude, produtor_id
- **LeituraSatelite**: id, data, ndvi, temperaturaSolo, umidade, fonteSatelite, propriedade_id
- **AlertaClimatico**: id, tipoAlerta, severidade, descricao, dataGeracao, status, propriedade_id

Tabelas criadas no plural: `produtores`, `propriedades`, `leituras_satelite`, `alertas_climaticos`.

---

# COMO EXECUTAR (passo a passo)

A aplicação sobe na **porta 9000** e o Swagger fica na **raiz** (`http://localhost:9000/`).

Você precisa de: **Java 17+**, **Maven** (ou usar o `mvnw` incluso) e **Docker** (para o MySQL).

---

## Opção 1 — Subir o MySQL com Docker e rodar a API com Maven (recomendada)

Esta é a forma de teste mais direta. Use **dois terminais** (ou rode o banco em segundo plano).

### Passo 1: subir o banco de dados MySQL (Docker)

Na raiz do projeto, execute:

```bash
docker compose up -d mysql
```

Esse comando usa o arquivo **`docker-compose.yml`** (incluso no projeto) e sobe um container MySQL 8 já configurado:
- banco: `agroorbit`
- usuário: `root`
- senha: `root_pwd`
- porta: `3306`

Para conferir se o banco subiu:

```bash
docker ps
```

(deve aparecer o container `agroorbit-mysql`)

### Passo 2: rodar a API Spring Boot

Ainda na raiz do projeto:

```bash
./mvnw spring-boot:run
```

(no Windows: `mvnw.cmd spring-boot:run`)

Quando aparecer **"Started AgroOrbitCoreApplication"**, a API está no ar.

### Passo 3: abrir o Swagger e testar

Acesse no navegador:

```
http://localhost:9000/
```

O Swagger UI abre com todos os endpoints, prontos para teste.

---

## Opção 2 — Subir tudo (MySQL + API) com Docker Compose

Se preferir rodar a aplicação inteira em containers, sem precisar do Maven:

```bash
docker compose up --build
```

Esse comando sobe o **MySQL e a API juntos** (a API espera o banco ficar saudável antes de iniciar). Depois acesse `http://localhost:9000/`.

Para parar:

```bash
docker compose down
```

---

## Testando a API do zero (sequência sugerida)

Como o banco começa vazio, crie os dados **nesta ordem** (por causa dos relacionamentos). Tudo pode ser feito pelo Swagger em `http://localhost:9000/`.

1. **Criar um produtor** — `POST /api/v2/produtores`
```json
{ "nome": "João Pereira", "cpf": "12345678901", "cooperativa": "Coamo", "ativo": true }
```

2. **Criar uma propriedade** (usando o id do produtor) — `POST /api/v2/propriedades`
```json
{ "nome": "Fazenda Boa Vista", "areaHectares": 120.0, "culturaPrincipal": "Soja", "produtorId": 1 }
```

3. **Criar uma leitura de satélite** — `POST /api/v2/leituras`
```json
{ "data": "2026-05-28", "ndvi": 0.72, "fonteSatelite": "Sentinel-2", "propriedadeId": 1 }
```

4. **Criar um alerta** — `POST /api/v2/alertas`
```json
{ "tipoAlerta": "Risco de geada", "severidade": "ALTA", "dataGeracao": "2026-05-28", "propriedadeId": 1 }
```

5. **Listar** qualquer recurso com o `GET` correspondente (ex: `GET /api/v2/produtores`).

> O arquivo **`requests.http`** (na raiz) traz todas as requisições prontas para usar na extensão REST Client do VS Code, ou para importar no Postman/Insomnia.

---

## Endpoints (CRUD completo para as 4 entidades)

Base: `http://localhost:9000/api/v2`

| Método | Rota | Ação |
| --- | --- | --- |
| POST | `/produtores` | Cria |
| GET | `/produtores` | Lista todos |
| GET | `/produtores/{id}` | Busca por id |
| PUT | `/produtores/{id}` | Atualiza |
| DELETE | `/produtores/{id}` | Remove |

As mesmas 5 operações existem para `/propriedades`, `/leituras` e `/alertas`.

---

## Arquitetura em camadas

```
Controller  ->  Service  ->  Repository  ->  MySQL
     |            (regras)     (JPA)
     +-- usa DTOs (Request/Response) + Mapper para nao expor as entidades
```

```
br.com.fiap.agroorbit
├── model/        # Entidades JPA
├── repository/   # Interfaces JpaRepository
├── service/      # Regras de negocio
├── dto/          # CreateRequest, UpdateRequest, Response e Mapper
├── controller/   # Endpoints REST (versao v2)
└── config/       # Configuracao do Swagger
```

---

## Configuração

- Porta: **9000** (`server.port=9000`)
- Versão da API na rota: **v2** (`api/v2/...`)
- Banco: MySQL em `localhost:3306`, base `agroorbit` (criada automaticamente)
- Swagger: na raiz `/`

Para alterar usuário/senha do banco, edite `src/main/resources/application.properties`.
