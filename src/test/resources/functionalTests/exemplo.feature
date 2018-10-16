#Author: your.email@your.domain.com

Feature: Pesquisa no google e filtrar pelo tipo de consulta
  Eu quero realizar uma pesquisa no google e selecionar o tipo de pesquisa apenas para notícias

  Scenario Outline: Pesquisa no google por Noticia
  
    Given eu acesse a pagina do google
    When eu preencher a "<pesquisa>"
    And clicar no botao pesquisar
    And quando as consultas forem apresentadas
    And eu selecionar o "<tipoPesquisa>"
    Then valido que apenas foram apresentadas as noticias
    
    Examples:
	|pesquisa|tipoPesquisa|
	|Google|Noticia|