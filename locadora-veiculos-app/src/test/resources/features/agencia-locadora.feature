Feature: Cadastra agencia e realiza teste

  Scenario: Deve cadastrar uma nova agencia e testar
    Given o banco de dados deve estar limpo
    When cadastrar uma nova agencia com numero 135 e nome "Agencia1Teste" e salvar no banco de dados
    Then a agencia com nome "Agencia1Teste" deve estar salvo no banco de dados