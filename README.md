# Sistema de Controle de Vagas de Condomínio

Um sistema desenvolvido em **Java** com arquitetura **MVC** para gerenciamento e controle de vagas de estacionamento em condomínios, integrado com banco de dados relacional. Projeto desenvolvido como parte da minha formação acadêmica.

## Tecnologias Utilizadas
* **Linguagem:** Java (versão 21)
* **IDE:** Eclipse
* **Banco de Dados:** MySQL (via XAMPP)

## Funcionalidades
* Autenticação de funcionários via banco de dados.
* Cadastro e controle de apartamentos.
* Cadastro e remoção de veículos e moradores vinculados.
* Aplicação de avisos e multas para moradores infratores.
* Interface interativa via terminal/console.

## Como Executar o Projeto

1. Certifique-se de ter o **XAMPP** iniciado com os módulos Apache e MySQL ativos.
2. Importe o script do banco de dados no seu phpMyAdmin ou execute os comandos SQL via Shell.
3. Certifique-se de adicionar o driver do MySQL (`mysql-connector-j`) ao Classpath do projeto no Eclipse.
4. Abra o projeto no **Eclipse**.
5. Execute a classe principal (`ControleDoCondominio.java`) para iniciar a interface via terminal.
