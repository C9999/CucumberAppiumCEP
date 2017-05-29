# language: pt
Funcionalidade: Consultar histórico de pesquisas anteriores
  Sendo o usuário do aplicativo
  Posso consultar um histórico de endereços já buscados
  Para que eu possa saber de forma rápida quais pesquisas já foram feitas e não precise repetilas.

  Cenário: Consultar CEPs já buscados
    Dado que eu estou na tela de BuscaCEP
    E já busquei por alguns CEPs
    Quando clico em Consultas Anteriores
    Então vejo todos os endereços já consultados
