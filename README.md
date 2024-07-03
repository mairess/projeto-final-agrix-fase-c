# <p align="center">Projeto Final Agrix Fase C</p>

## Contexto

Agrix é uma simples API que permite a gestão e o monitoramento das fazendas. Nessa fase o projeto
conta com a segurança do spring-security.

## Rode o projeto localmente

> ⚠️ É preciso ter [Java](https://www.oracle.com/java/) instalado em sua máquina.

> ⚠️ É preciso ter [Docker](https://www.docker.com/get-started/) instalado em sua máquina.

1. Clone o repositório:

```BASH
git clone git@github.com:mairess/projeto-final-agrix-fase-c.git
```

2. Instale as dependências:

```BASH
mvn install -DskipTests
```

3. Inicie o banco de dados:

```BASH
docker compose up database -d --build 
```

4. Rode a API:

```BASH
mvn spring-boot:run
```

4. Veja as rotas disponíveis em:

```BASH
http://localhost:8080/swagger-ui/index.html
```

5. Rode o teste:

```BASH
mvn test
```

## Rode o projeto com docker

> ⚠️ É preciso ter [Docker](https://www.docker.com/get-started/) instalado em sua máquina.

1. Clone o repositório:

```BASH
git clone git@github.com:mairess/projeto-final-agrix-fase-c.git
```

2. Inicie a API e o banco de dados:

```BASH
docker compose up -d --build 
```

3. Veja as rotas disponíveis em:

```BASH
http://localhost:8080/swagger-ui/index.html
```

4. Rode o teste:

```BASH
mvn test
```