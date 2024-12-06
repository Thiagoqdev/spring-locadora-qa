Feature: Cadastra agencia e realiza teste

  Scenario: Deve cadastrar um novo veículo e testar
    Given o banco de dados deve estar limpo
    When cadastrar um novo veículo com marca "Volks" modelo "Gol" e ano de fabricacao "2020" e salvar no banco de dados
    Then o veiculo com id 1 e marca "Volks" deve estar salvo no banco de dados