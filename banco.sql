-- Criação do banco de dados
CREATE DATABASE IF NOT EXISTS trabalhoa3;
USE trabalhoa3;

-- Criação da tabela categoria
CREATE TABLE IF NOT EXISTS categoria (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    tamanho ENUM('PEQUENO', 'MEDIO', 'GRANDE') NOT NULL,
    embalagem ENUM('LATA', 'VIDRO', 'PLASTICO') NOT NULL
);

-- Criação da tabela produto
CREATE TABLE IF NOT EXISTS produto (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    preco_unitario DECIMAL(10,2) NOT NULL,
    unidade VARCHAR(20) NOT NULL,
    quantidade_estoque INT NOT NULL,
    quantidade_minima INT NOT NULL,
    quantidade_maxima INT NOT NULL,
    categoria VARCHAR(100) NOT NULL
);
