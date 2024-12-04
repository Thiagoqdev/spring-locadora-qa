Feature: Cadastra usuario e realiza login

  Scenario: Deve cadastrar um usuário novo e realizar o login
    Given o banco de dados deve estar limpo
    When cadastrar um novo usuário com nome "Test01", com a senha "123456" e o tipo de usuario "ADMIN"
    Then o usuario com nome "Test01" deve estar salvo no banco de dados