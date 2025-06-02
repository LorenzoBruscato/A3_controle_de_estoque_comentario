# 🛠️ Sistema de Controle de Estoque


## 👥 Integrantes

| Nome Completo                     | Usuário GitHub       | RA            |
|----------------------------------|-----------------------|---------------|
| Diego Wobeto Maglia Muller       | diegowmmuller        | 10724265729   |
| Victor Hugo Andrade              | andradevh            | 10725115564   |
| Lorenzo Bruscato                 | Lorenzo, LorenzoBruscato              | 10724262961   |
| Murilo Vieira Moura              | Murilo, ivaxs              | 10724269339   |
| Henrique Bernardes Rosa          | INTEL, HenriqueBrosa        | 10724263295   |

Este é um software desenvolvido em conjunto para gerenciar um banco de dados MySQL com duas tabelas principais: **Produto** e **Categoria**. Ele permite cadastrar, listar, atualizar e excluir produtos e categorias de forma simples e eficiente.

## 📦 Requisitos Funcionais

- RF001: Cadastro de categorias
- RF002: Cadastro de produtos com categoria associada
- RF003: Edição e exclusão de produtos e categorias
- RF004: Listagem completa de produtos e categorias
- RF005: Conexão com banco de dados MySQL via JDBC

## 📦 Requisitos Não Funcionais

- NRF001: Escolha do caminho para salvar relatórios
- NRF002: Seleção do tipo de relatório (Excel, DOC ou PDF)
- NRF003: Definir nome do arquivo dos relatórios
- NRF004: Resposta eficiente aos comandos de CRUD
- NRF005: Compatível com banco de dados MySQL

## 🧱 Estrutura do Banco de Dados

**Tabela: categoria**

| Campo     | Tipo        | Descrição                      |
| --------- | ----------- | ------------------------------ |
| id        | INT         | Identificador único            |
| name      | VARCHAR(50) | Nome da categoria              |
| tamanho   | ENUM        | Tamanho da categoria           |
| embalagem | ENUM        | Tipo de embalagem da categoria |

**Tabela: produto**

| Campo              | Tipo          | Descrição                        |
| ------------------ | ------------- | -------------------------------- |
| id                 | INT           | Identificador único              |
| nome               | VARCHAR(100)  | Nome do produto                  |
| preço              | DECIMAL(10,2) | Preço do produto                 |
| unidade            | INT           | kg, litro, ml                    |
| quantidade_estoque | INT           | quantidade do produto em estoque |
| quantidade_minima  | INT           | quantidade minima permitida      |
| quantidade_maxima  | INT           | quantidade maxima permitida      |
| categoria          | categoria     | categoria do produto             |

## ⚙️ Tecnologias Utilizadas

- Java 21
- JDBC 9.2.0 (Java Database Connectivity) 
- MySQL 8.0.42
- Maven 3.9.9
- IDE Netbeans 25

## ⚙️ Configurando o banco de dados MySql

- Para rodar o programa é necessario fazer o download do MySql workbench 8.0
- Acesse o arquivo [banco.sql](banco.sql) para criar o banco de dados
- Usuario: root
- Senha: 1234567

