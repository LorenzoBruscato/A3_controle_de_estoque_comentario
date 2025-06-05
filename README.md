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

📦 Requisitos Funcionais,,
RF01 - Cadastrar produtos:
Nome,
Preço unitário,
Unidade,
Quantidade em estoque,
Quantidade mínima em estoque,
Quantidade máxima em estoque,
Categoria,

RF02 - Cadastrar categorias:
Nome,
Tamanho (Pequeno, Médio, Grande),
Embalagem (Lata, Vidro, Plástico),

RF03 - Permitir operações:
Inclusão,
Alteração,
Exclusão,
Listagem de produtos e categorias,

RF03 - Reajuste de preço
Permite reajustar o percentual de preço em todos os produtos.

RF02: Cadastro de Produto com Categoria
O sistema deve permitir o cadastro de produtos já vinculando cada um a uma categoria existente

RF04: Listar Produtos e Categorias
O sistema deve exibir todos os produtos e categorias cadastrados.

RF05: Emissão de Relatórios
O sistema deve gerar relatórios nos formatos Excel, DOC ou PDF.

RF06 - Movimentação de Produtos
O sistema deve registrar entradas e saídas de produtos no estoque.

📦 Requisitos Não Funcionais,,
RNF01 – Desempenho de Relatórios
O sistema deve emitir relatórios em até 3 segundos.

RNF02 – Usabilidade da Interface
O sistema deve possuir uma interface gráfica de fácil entendimento.

RNF03 – Privacidade do Usuário
O sistema deve garantir a privacidade dos dados dos usuários.

RNF04 – Compatibilidade com Windows
O sistema deve ser compatível com qualquer versão do sistema operacional Windows.

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

## Regra de Negócios

📁 Categorias
- RN001 – O nome da categoria deve ser único (não pode haver duas categorias com o mesmo nome).
- RN002 – O tamanho da categoria deve ser selecionado entre os valores permitidos pelo ENUM.
- RN003 – A embalagem da categoria deve ser selecionada entre os valores definidos pelo ENUM.
- RN004 – Não é permitido excluir uma categoria que esteja associada a um ou mais produtos.

📦 Produtos
- RN005 – Cada produto deve obrigatoriamente estar vinculado a uma categoria existente.
- RN006 – O nome do produto deve conter entre 1 e 100 caracteres.
- RN007 – O preço do produto deve ser maior que zero.
- RN008 – A unidade do produto deve ser informada em valores fixos (ex: 1 = kg, 2 = litro, 3 = ml).
- RN009 – A quantidade mínima deve ser menor ou igual à quantidade máxima.
- RN010 – A quantidade em estoque deve estar entre a quantidade mínima e máxima permitida.
- RN011 – Não é permitido cadastrar dois produtos com o mesmo nome e categoria.
- RN012 – Não é permitido excluir um produto se ele estiver sendo utilizado em algum relatório ativo (se aplicável).
- RN013 – Produtos com estoque abaixo da quantidade mínima devem ser destacados (visualmente ou em relatório).

📊 Relatórios
- RN014 – O usuário deve escolher o formato do relatório (Excel, DOC ou PDF) antes da geração.
- RN015 – O nome do arquivo do relatório deve ser definido pelo usuário.
- RN016 – O relatório deve conter a data de geração e a lista completa de produtos ou categorias conforme a opção escolhida.
- RN017 – O caminho de salvamento do relatório deve ser um diretório válido no sistema de arquivos.

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

