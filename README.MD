# Teste Técnico - Publicações Online

## Essa API foi desenvolvida com Spring Boot e PostgreSQL.

### Versões:

    Java - 17
    Spring Framework - 3.2.11

### Endpoints:

#### 1. POST - Cadastro de Processo Judicial

    Url: http://localhost:8080/api/process

    Exemplo de cadastro: 
        {
            "processNumber": "0001234-56.2024.8.26.0000",
            "defant": "João Teste"
        }

#### 2. GET - Listagem dos Processos

    Url: http://localhost:8080/api/process