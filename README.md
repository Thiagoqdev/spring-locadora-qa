# API REST com Testes Automatizados de Locadora de Veículos

## Descrição do Projeto
Este projeto é uma API REST desenvolvida em Java utilizando o framework Spring Boot. Ele implementa funcionalidades específicas (descreva aqui brevemente as funcionalidades principais da API, como gerenciamento de usuários, autenticação, etc.).

Além disso, foram criados testes automatizados abrangendo as camadas unitária, de integração e de aceitação (end-to-end), utilizando JUnit, WireMock, e Cucumber para garantir a qualidade e confiabilidade do sistema.

## Principais Funcionalidades

- **Endpoints REST:**
  - (Liste os principais endpoints e suas funções, ex.: /api/login para autenticação, /api/users para gerenciamento de usuários, etc.).
- **Testes Automatizados:**
  - Unitários: Validam a lógica de negócio.
  - Integração: Verificam interações entre componentes.
  - End-to-End: Simulam fluxos completos do sistema com cenários descritos em Gherkin.

## Tecnologias Utilizadas
**Java 17**

**Spring Boot 3.x**

**JUnit 5**

**Cucumber para BDD**

**WireMock para mock de APIs externas**

**H2 Database (banco em memória para testes)**

**Maven como gerenciador de dependências**

## Configuração do Ambiente
**Pré-requisitos**

Certifique-se de ter as seguintes ferramentas instaladas:

- Java 17+
- Maven 3.8+

**Passos para Configuração**

1. Clone o repositório:
   ```bash
   git clone <https://github.com/Thiagoqdev/spring-locadora-qa>
   ```
2. Configure o ambiente de desenvolvimento:

- Os testes utilizam o banco de dados H2 em memória, não sendo necessário configurar um banco externo.

- Para os testes de integração com APIs externas, o **WireMock** será configurado automaticamente durante a execução.

3. Para executar os testes, veja a seção "Como Executar os Testes".

## Como Executar a Aplicação
1. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
2. Acesse a documentação Swagger (caso configurado):
   ```bash
   http://localhost:8080/swagger-ui.html
   ```

## Como Executar os Testes
**Testes Unitários e de Integração**

- Para rodar todos os testes unitários e de integração:
   ```bash
   mvn test
   ```
**Testes Funcionais (Cucumber)**
- Os testes end-to-end estão localizados em src/test/resources/features. Para executá-los:
   ```bash
   mvn clean test
   ```
##  Estrutura do Projeto
   ```bash
/src/main/java/com/seuprojeto/
├── config/           # Configurações do projeto (Swagger, segurança, etc.)
├── controller/       # Endpoints REST
├── dto/              # Objetos de transferência de dados (DTOs)
├── model/            # Entidades do sistema
├── repository/       # Interfaces de repositório (JPA)
├── service/          # Lógica de negócios e serviços

/src/test/java/com/seuprojeto/
├── unit/             # Testes unitários
├── integracao/       # Testes de integração
├── stepdefinitions/  # Definições dos cenários Cucumber
/src/test/resources/features/
├── *.feature         # Cenários descritos em Gherkin
   ```
## Como Contribuir
1. Faça um fork do repositório.


2. Crie uma branch para sua feature:
    ```bash
   git checkout -b minha-feature
   ```
3. Commit suas alterações:
    ```bash
   git commit -m "Adicionando minha feature"
   ```
4. Commit suas alterações:
    ```bash
   git push origin minha-feature
   ```
5. Abra um Pull Request.

---