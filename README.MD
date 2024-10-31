# Teste Técnico - Publicações Online

## Essa API foi desenvolvida com Spring Boot e PostgreSQL.

### Versões:

    Java - 17
    Spring Framework - 3.2.11

### Endpoints:

#### 1. POST - Cadastro de Processo Judicial

    Url: http://localhost:8080/api/process

    Exemplo: 
        {
            "processNumber": "0001234-56.2024.8.26.0000",
            "defendant": "João Teste"
        }

#### 2. GET - Listagem dos Processos

    Url: http://localhost:8080/api/process


#### 3. DELETE - Deletar Processo Via Número

    Url: http://localhost:8080/api/process/{processNumber}

    Exemplo: http://localhost:8080/api/process/0001234-56.2024.8.26.0000

#### 4. PUT - Alterar Processo/Inserir Réu

    Url: http://localhost:8080/api/process/

    Exemplo:

        {
            "id": 11,
            "processNumber": "0001234-56.2024.8.26.0000",
            "defendantName": "João Teste"
        }

## Testes

Os teste dos endpoints da aplicação estão no caminho:

    api\src\test\java\br\com\process\api\Controller\ProcessControllerTest.java

## Configuração de Banco de Dados


As configurações de banco devem ser definidas no arquivo application.properties:

    spring.application.name=api
    spring.application.name=api
    spring.datasource.url=jdbc:postgresql://localhost:5432/DB_NAME
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    spring.jpa.hibernate.ddl-auto=validate
    spring.jpa.show-sql=true
    spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.xml

As definições da tabela estão no arquivo db.changelog-master.xml

    <?xml version="1.0" encoding="UTF-8" standalone="no"?>
    <databaseChangeLog
        xmlns="http://www.liquibase.org/xml/ns/dbchangelog"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://www.liquibase.org/xml/ns/dbchangelog
            http://www.liquibase.org/xml/ns/dbchangelog/dbchangelog-3.8.xsd">

        <changeSet id="1" author="seu_nome">
            <createTable tableName="process">
                <column name="id" type="BIGINT" autoIncrement="true">
                    <constraints primaryKey="true" nullable="false"/>
                </column>
                <column name="process_number" type="VARCHAR(255)">
                    <constraints nullable="false" unique="true"/>
                </column>
                <column name="defendant_name" type="VARCHAR(255)">
                    <constraints nullable="true"/>
                </column>
            </createTable>
        </changeSet>

    </databaseChangeLog>
