# Projeto Spring Boot - API de Locadora de Veículos

Este projeto implementa uma API REST para uma locadora de veículos, fornecendo funcionalidades de gerenciamento de veículos, clientes e transações de aluguel. A API suporta operações CRUD completas, segurança com controle de rotas, login, e oferece opções de consumo de APIs externas e documentação via Swagger.

## Funcionalidades

- **Persistência de dados**: Armazena informações no banco de dados (H2 para desenvolvimento ou PostgreSQL para produção).
- **Operações CRUD**: Suporte para métodos HTTP (GET, POST, PUT e DELETE) nas principais entidades.
- **Paginação**: Endpoints de listagem com suporte a paginação para facilitar a navegação entre os dados.
- **Validações**: Validação de dados nos controllers usando Regex e a anotação `@Valid` para garantir integridade.
- **Segurança**: Controle de rotas e autenticação por meio de JWT (opcional).
- **Consumo de API externa (opcional)**: Integração com uma API pública, como uma API de informações sobre veículos.
- **Documentação com Swagger (opcional)**: Documentação automática dos endpoints, acessível via interface web.
- **Frontend (opcional)**: Interface para visualização e gerenciamento dos recursos da API.

## Tecnologias Utilizadas
**Linguagem:** Java

**Framework:** Spring Boot

**Banco de Dados:** H2 (desenvolvimento) ou Postgres (produção)

**Autenticação:** JWT (opcional)

**Documentação:** Swagger (opcional)


## Endpoints Principais

Abaixo estão os principais endpoints da API:

| Recurso     | Método     | Descrição                          |
|:------------|:-----------|:-----------------------------------|
| `/veiculos` | **GET**    | Lista veículos com paginação       |
|             | **POST**   | Cria um novo veículo               |
|             | **PUT**    | Atualiza informações de um veículo |
|             | **DELETE** | Remove um veículo                  |

| Recurso     | Método     | Descrição                    |
|:------------|:-----------|:-----------------------------|
| `/clientes` | **GET**    | Lista clientes               |
|             | **POST**   | Cria um novo cliente         |
|             | **PUT**    | Atualiza dados de um cliente |
|             | **DELETE** | Remove um cliente            |

| Recurso     | Método     | Descrição                             |
|:------------|:-----------|:--------------------------------------|
| `/alugueis` | **GET**    | Lista clientes                        |
|             | **POST**   | Realiza uma nova transação de aluguel |
|             | **PUT**    | Atualiza uma transação de aluguel     |
|             | **DELETE** | Cancela uma transação de aluguel      |

| Recurso                       | Método   | Descrição                                      |
|:------------------------------|:---------|:-----------------------------------------------|
| `/auth/register`              | **POST** | criação de usuário                             |		
|                               |          | {                                              |		
|                               |          | "login": "usuario",                            |		
|                               |          | "password": "senha123",                        |		
|                               |          | "role": "ADMIN" // ou "USER"                   |		
|                               |          | }                                              |		                            
| `/auth/login`                 | **POST** | Autenticação de usuário e geração de token JWT |
|                               |          | {                                              |	
|                               |          | "login": "usuario",                            |	
|                               |          | "password": "senha123"                         |
|                               |          | }                                              |

| Recurso              | Método   | Descrição                                             |
|:---------------------|----------|:------------------------------------------------------|
| `Agência (Agencia):` | **JSON** | {                                                     |
|                      |          | "nomeAgencia": "Locadora Veloz",                      |
|                      |          | "enderecoAgencia": "Av. Brasil, 1500 - São Paulo, SP" |
|                      |          | }                                                     |

| Recurso              | Método   | Descrição                            |
|:---------------------|----------|:-------------------------------------|
| `Usuário (Usuario):` | **JSON** | {                                    |
|                      |          | "nome": "Carlos Eduardo Oliveira",   |
|                      |          | "email": "carlos.eduardo@email.com", |
|                      |          | "cpf": "12345678901",                |
|                      |          | "senha": "SenhaForte@2024",          |
|                      |          | "tipoUsuario": "CLIENTE"             |
|                      |          | }                                    |

| Recurso              | Método   | Descrição                      |
|:---------------------|----------|:-------------------------------|
| `Veículo (Veiculo):` | **JSON** | {                              |
|                      |          | "placa": "ABC1D23",            |
|                      |          | "modelo": "Gol",               |
|                      |          | "marca": "Volkswagen",         |
|                      |          | "anoFabricacao": "2022",       |
|                      |          | "statusAluguel": "DISPONIVEL", |
|                      |          | "tipoVeiculo": "CARRO"         |
|                      |          | }                              |

| Recurso              | Método   | Descrição                            |
|:---------------------|----------|:-------------------------------------|
| `Aluguel (Aluguel):` | **JSON** | {                                    |
|                      |          | "agencia": {"numeroAgencia": 1},     |
|                      |          | "veiculo": {"veiculo_id": 1},        |
|                      |          | "valorCobrado": 350.75,              |
|                      |          | "dataInicio": "2024-03-15T10:00:00", |
|                      |          | "dataFim": "2024-03-20T18:00:00",    |
|                      |          | "tipoPagamento": "CREDITO"           |
|                      |          | }                                    |
	
## Instalação e Configuração

### Pré-requisitos

- Java JDK 17+
- Maven
- Banco de Dados H2 (desenvolvimento) ou PostgreSQL (produção)

### Passos para executar o projeto

1. **Clonar o repositório**:
   ```bash
   git clone https://github.com/Thiagoqdev/Projeto-Spring-LocadoraVeiculos.git
   cd locadora-veiculos-api
   ```

2. **Configurar o banco de dados**:
   - Para desenvolvimento, o projeto usa H2 por padrão.
   - Para PostgreSQL, edite o arquivo `application.properties` com suas credenciais e a URL de conexão.


3. **Compilar e rodar a aplicação**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### Documentação (Swagger)

A documentação da API, gerada automaticamente com Swagger, está disponível em: http://localhost:8080/swagger-ui/index.html#/

## Estrutura do Projeto

- `src/main/java`: Código-fonte do projeto.
- `src/main/resources`: Configurações da aplicação e schema do banco de dados.
- `pom.xml`: Configurações do Maven e dependências.

### Principais Classes

- **Controller**: Camada que expõe os endpoints da API.
- **Service**: Contém a lógica de negócios.
- **Repository**: Responsável pela interação com o banco de dados.
- **Config**: Configurações de segurança e JWT.

## Licença

Este projeto está licenciado sob a licença MIT. Consulte o arquivo LICENSE para mais detalhes.

---