# language: pt
Funcionalidade: Busca de endereços por CEP
  Sendo o usuário do aplicativo
  Posso buscar por números de CEP
  Para que possa ver, o endereço completo retornado nessa consulta.

  Cenário: Busca por CEP
    Dado que eu estou na home de BuscaCEP
    E informo o CEP que vai ser consultado
    Quando clico no icone de busca
    Então vejo o endereço completo do CEP consultado.
