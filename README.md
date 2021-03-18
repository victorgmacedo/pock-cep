# Serviço para busca de CEP

## Estratégia

A estrategia para o desenvolvimento foi utilizar ferramentas e frameworks que estou habituado facilitando assim todo o processo de desenvolvimento. Separei as informações necessárias para apresentação dos dados em duas classes, uma mapeando os dados de ```Cidade``` e outra mapeando os dados de Endereço onde a classe ```Endereco``` esta diretamente ligada a classe de ```Cidade```.
Foi criado endpoints simples de cadastro tanto para Cidade quanto para o Endereço para facilitar a avaliação da regra solicitada, mantive também os registros cadastrados em um banco relacional, mantendo os mocks apenas para os testes. Utilizei o heroku para fazer o deploy da documentação junto com a aplicação.

A documentação do sistema pode ser acessada pelo link https://cep-rest-api.herokuapp.com/swagger-ui/ onde pode ser feita todas as operações disponibilizadas pela aplicação.

Alguns registros ja foram previamente cadastrados e podem ser utilizados durante a avaliação.

- 86801360
- 86802781
- 86806570
- 86803400
- 86807600

## Arquitetura 

### Para o desenvolvimento deste serviço foram utilizadas os seguintes Framerowk/Ferramenta.
1. [Spring framework](https://spring.io/)
2. [Liquibase](https://www.liquibase.org/)
3. [Postgres](https://www.postgresql.org/)
4. [Swagger](https://swagger.io/)
5. [Heroku](https://www.heroku.com/)

### Padrão de projeto 
Foi utilizado o padrão MVC aplicando os princípios de SOLID.
