

# Projeto de Serviço de Validação de Usuários e Obtenção de Perfis

[Link do Repositório](https://github.com/paulo-2048/distributed-user-eureka.git)

Este repositório contém um conjunto de aplicações Java Spring Boot que implementam um serviço de validação de usuários e obtenção de perfis. As aplicações utilizam Eureka para descoberta de serviços e DNS para resolução de nomes.

## Aplicações Envolvidas

- **Servidor ISP (localhost:8080)**: Servidor principal que recebe as requisições do usuário e coordena as chamadas para as outras aplicações.
- **DNS**: Obtém do Eureka a lista de aplicações e envia o id da aplicação desejada ao ISP
- **Aplicação de Validação**: Efetua a validação de e-mails do usuário.
- **Aplicação de Perfis**: Verifica o perfil do usuário com base no e-mail fornecido.

## Funcionamento

1. **Validação do E-mail**:
   - O usuário envia uma requisição para o endpoint http:localhost:8084/{email}/{cargo}
   - O servidor ISP recebe a requisição e consulta o servidor DNS para obter o IP de acesso.
   - O servidor DNS consulta o servidor Eureka para obter a lista de aplicações ativas.
   - O servidor DNS retorna o IP e porta para o servidor ISP.
   - O servidor ISP faz a chamada à aplicação de validação com o IP e porta e retorna o resultado ao usuário.

2. **Obtenção de Dados de Perfil**:
   - O usuário envia uma requisição POST para o endpoint http:localhost:8082/profilesinfo
   - O servidor ISP recebe a requisição e consulta o servidor DNS para obter o IP de acesso.
   - O servidor DNS consulta o servidor Eureka para obter a lista de aplicações ativas.
   - O servidor DNS retorna o IP e porta para o servidor ISP.
   - O servidor ISP faz a chamada à aplicação de perfil com o IP e porta e retorna o resultado ao usuário.

## Tecnologias Utilizadas

- Java Spring Boot
- Eureka (para descoberta de serviços)
- DNS (para resolução de nomes)
- Spring WEB
- Lombok
- Java 21
- Maven 3.9.6+
- API REST

## Desenvolvedores e Disciplina

Este conjunto de aplicações foi desenvolvido por Paulo Vitor de Santana Santos, Luis Guilherme de Oliveira Carvalho e Yuri de Jesus Gomes para a disciplina de Sistemas Distribuídos, ministrada pelo professor Everton Mendonça de Jesus na Universidade Católica de Salvador.

## Licença

Este projeto está licenciado sob a Apache-2.0.
