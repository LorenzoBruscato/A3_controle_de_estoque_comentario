# 🛠️ Sistema de Gerenciamento de Produtos e Categorias

## Integrantes

## 👥 Integrantes

| Nome Completo                     | Usuário GitHub       | RA            |
|----------------------------------|-----------------------|---------------|
| Diego Wobeto Maglia Muller       | diegowmmuller        | 10724265729   |
| Victor Hugo Andrade              | andradevh            | 10725115564   |
| Lorenzo Bruscato                 | Lorenzo              | 10724262961   |
| Murilo Vieira Moura              | Murilo               | 10724269339   |
| Henrique Bernardes Rosa          | INTEL, HenriqueBrosa        | 10724263295   |

Este é um software desenvolvido em conjunto para gerenciar um banco de dados MySQL com duas tabelas principais: **Produto** e **Categoria**. Ele permite cadastrar, listar, atualizar e excluir produtos e categorias de forma simples e eficiente.

## 📦 Funcionalidades

- ✅ Cadastro de categorias
- ✅ Cadastro de produtos com categoria associada
- ✅ Edição e exclusão de produtos e categorias
- ✅ Listagem completa de produtos e categorias
- ✅ Conexão com banco de dados MySQL via JDBC

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
- JDBC (Java Database Connectivity)
- MySQL
- Maven
- IDE (Eclipse, IntelliJ ou Netbeans)

## Configurando o banco de dados MySql

- Para rodar o programa é necessario fazer o download do MySql workbench 8.0
- Acesse o arquivo banco.sql para criar o banco de dados

