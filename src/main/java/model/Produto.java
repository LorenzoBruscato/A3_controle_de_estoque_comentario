package model;

import DAO.ProdutoDAO;
import java.util.ArrayList;

public class Produto {

    private String nome;
    private double preco;
    private String unidade; // Ex: "kg", "litro", "unidade"
    private Categoria categoria;
    private int quantidade;
    private int quantidadeMinima;
    private int quantidadeMaxima;

    public Produto() {
    }

    public Produto(String nome, double preco, String unidade, Categoria categoria, int quantidade, int quantidadeMinima, int quantidadeMaxima) {
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    //Métodos auxiliares
    public boolean acimadoMAX() {
        return this.quantidade > quantidadeMaxima;
    }

    public boolean abaixodoMIN() {
        return this.quantidade < quantidadeMinima;
    }

    public double ValorTotal() {
        return this.preco += quantidade;
    }

    public void entrada() {
        this.quantidade += quantidade;
    }

    public void saida() {
        this.quantidade -= quantidade;
    }

    public void ajustarPreco(double porcentual) {
        this.preco += preco * (porcentual / 100);
    }

    @Override
    public String toString() {
        return String.format("%s | %.2f | %s | %s | Qtd: %d",
                nome, preco, unidade, categoria.getNome(), quantidade);
    }

    // Retorna a lista de produtos do DAO
    public ArrayList getListaProduto() {
        return ProdutoDAO.getListaProduto();
    }

// Insere um novo produto na lista
    public boolean insertProduto(String nome, double preco, String unidade, Categoria categoria, int quantidade, int quantidadeMinima, int quantidadeMaxima) {
        Produto novoProduto = new Produto(nome, preco, unidade, categoria, quantidade, quantidadeMinima, quantidadeMaxima);
        ProdutoDAO.getListaProduto().add(novoProduto);
        return true;
    }

// Insere um produto na lista usando um objeto Produto
    public boolean insertProdutonome(Produto nome) {
        ProdutoDAO.getListaProduto().add(nome);
        return true;
    }

// Procura o índice de um produto pelo nome
    private int procurarIndiceProdutoNome(String nome) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return indice;
    }

// Remove um produto com base no nome
    public boolean deletarProdutoNome(String nome) {
        int indice = this.procurarIndiceProdutoNome(nome);
        ProdutoDAO.getListaProduto().remove(indice);
        return true;
    }

// Atualiza o nome de um produto
    public boolean updateProdutoNome(String nomeantigo, String nomenovo) {
        int indice = this.procurarIndiceProdutoNome(nomeantigo);
        ProdutoDAO.getListaProduto().get(indice).setNome(nomenovo);
        return true;
    }

// Procura o índice de um produto pelo preço
    private int procurarIndiceProdutoPreco(double preco) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getPreco() == preco) {
                return i;
            }
        }
        return indice;
    }

// Remove um produto com base no preço
    public boolean deleteProdutProdutoPreco(double preco) {
        int indice = procurarIndiceProdutoPreco(preco);
        ProdutoDAO.getListaProduto().remove(indice);
        return true;
    }

// Atualiza o preço de um produto
    public boolean updateProdutoPreco(double precoAntigo, double precoNovo) {
        int indice = this.procurarIndiceProdutoPreco(precoAntigo);
        ProdutoDAO.getListaProduto().get(indice).setPreco(precoNovo);
        return true;
    }

// Insere produto com base na unidade
    public boolean insertProdutoUnidade(Produto unidade) {
        ProdutoDAO.getListaProduto().add(unidade);
        return true;
    }

// Procura o índice de um produto pela unidade
    private int procurarIndicePorUnidade(String unidade) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getUnidade().equalsIgnoreCase(unidade)) {
                return i;
            }
        }
        return indice;
    }

// Remove produto com base na unidade
    public boolean deletarProdutoUnidade(String unidade) {
        int indice = this.procurarIndicePorUnidade(unidade);
        if (indice != -1) {
            ProdutoDAO.getListaProduto().remove(indice);
            return true;
        }
        return false; // retorna false se não encontrar
    }

// Atualiza o produto com base na unidade
    public boolean updateProdutoUnidade(String unidade, Produto unidadenova) {
        int indice = this.procurarIndicePorUnidade(unidade);
        ProdutoDAO.getListaProduto().set(indice, unidadenova);
        return true;
    }

// Insere produto com base na quantidade
    public boolean insertProdutoQuantidade(Produto quantidade) {
        ProdutoDAO.getListaProduto().add(quantidade);
        return true;
    }

// Procura índice do produto pela quantidade
    private int procurarIndicePorQuantidade(int quantidade) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getQuantidade() == quantidade) {
                return i;
            }
        }
        return indice;
    }

// Remove produto com base na quantidade
    private boolean deletarProdutoQuantidade(int quantidade) {
        int quantidadeant = this.procurarIndicePorQuantidade(quantidade);
        ProdutoDAO.getListaProduto().remove(quantidadeant);
        return true;
    }

// Atualiza produto com base na quantidade
    private boolean updateProdutoquantidade(int quantidadeantiga, Produto quantidadenovo) {
        int indice = this.procurarIndicePorQuantidade(quantidadeantiga);
        ProdutoDAO.getListaProduto().set(indice, quantidadenovo);
        return true;
    }

// Insere produto com quantidade mínima
    public boolean insertIndiceProdutoQuantidadeMinima(Produto quantidademin) {
        ProdutoDAO.getListaProduto().add(quantidademin);
        return true;
    }

// Procura índice do produto pela quantidade mínima
    private int procuraIndiceProdutoQuantidadeMinima(int quantidadeMinima) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getQuantidadeMinima() == quantidadeMinima) {
                return i;
            }
        }
        return indice;
    }

// Remove produto com base na quantidade mínima
    public boolean deletarProdutoQuantidadeMinima(int quantidadeMinima) {
        int indice = this.procuraIndiceProdutoQuantidadeMinima(quantidadeMinima);
        ProdutoDAO.getListaProduto().remove(indice);
        return true;
    }

// Atualiza produto com base na quantidade mínima
    public boolean updateProdutoQuantidadeMinima(int quantidadeMinima, Produto quantidadeMinimaNova) {
        int indice = this.procuraIndiceProdutoQuantidadeMinima(quantidadeMinima);
        ProdutoDAO.getListaProduto().set(indice, quantidadeMinimaNova);
        return true;
    }

// Insere produto com quantidade máxima
    public boolean insertIndiceProdutoQuantidadeMaxima(Produto quantidadeMaxima) {
        ProdutoDAO.getListaProduto().add(quantidadeMaxima);
        return true;
    }

// Procura índice do produto pela quantidade máxima
    private int procurarIndiceProdutoQuantidadeMaxima(int quantidadeMaxima) {
        int indice = -1;
        ArrayList<Produto> lista = ProdutoDAO.getListaProduto();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getQuantidadeMaxima() == quantidadeMaxima) {
                return i;
            }
        }
        return indice;
    }

// Remove produto com base na quantidade máxima
    public boolean deletarProdutoQuantidadeMaxima(int quantidadeMaxima) {
        int indice = this.procurarIndiceProdutoQuantidadeMaxima(quantidadeMaxima);
        ProdutoDAO.getListaProduto().remove(indice);
        return true;
    }

// Atualiza produto com base na quantidade máxima
    public boolean updateProdutoQuantidadeMaxima(int quantidadeMaxima, Produto quantidadeMaximaNova) {
        int indece = this.procurarIndiceProdutoQuantidadeMaxima(quantidadeMaxima);
        ProdutoDAO.getListaProduto().set(indece, quantidadeMaximaNova);
        return true;
    }
}
