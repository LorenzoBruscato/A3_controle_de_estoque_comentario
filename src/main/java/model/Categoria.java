package model;

import DAO.CategoriaDAO;
import java.util.ArrayList;

public class Categoria {

    private int id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    public enum Tamanho {
        PEQUENO, MEDIO, GRANDE
    }

    public enum Embalagem {
        LATA, VIDRO, PLASTICO
    }

    // Construtor
    public Categoria(int id, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    @Override
    public String toString() {
        return "Categoria{"
                + "id=" + id
                + ", nome='" + nome + '\''
                + ", tamanho=" + tamanho
                + ", embalagem=" + embalagem
                + '}';
    }

    // Operações com DAO
    // Retorna a lista de categorias do DAO
    public ArrayList getListaCategoria() {
        return CategoriaDAO.getListaCategoria();
    }

    // Insere uma nova categoria na lista
    public boolean insertCategoria(int id, String nome, Tamanho tamanho, Embalagem embalagem) {
        Categoria novaCategoria = new Categoria(id, nome, tamanho, embalagem);
        CategoriaDAO.getListaCategoria().add(novaCategoria);
        return true;
    }

    // Procura o índice da categoria pelo ID
    private int procuraIndiceId(int id) {
        int indice = -1;
        ArrayList<Categoria> lista = CategoriaDAO.getListaCategoria();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == id) {
                return i;
            }
        }
        return indice;
    }

    // Remove uma categoria da lista com base no ID
    public boolean deleteCategoriaId(int id) {
        int indice = this.procuraIndiceId(id);
        CategoriaDAO.getListaCategoria().remove(indice);
        return true;
    }

    // Atualiza os dados de uma categoria com base no ID
    public boolean updateCategoriaId(int id, Categoria objeto) {
        int indice = this.procuraIndiceId(id);
        CategoriaDAO.getListaCategoria().set(indice, objeto);
        return true;
    }

    // Insere uma categoria na lista com base em um objeto
    public boolean insertCategoriaNome(Categoria objeto) {
        CategoriaDAO.getListaCategoria().add(objeto);
        return true;
    }

    // Procura o índice da categoria pelo nome
    public int procurarIndicePorNome(String nome) {
        int indice = -1;
        ArrayList<Categoria> lista = CategoriaDAO.getListaCategoria();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getNome().equalsIgnoreCase(nome)) {
                return i;
            }
        }
        return indice;
    }

    // Remove uma categoria da lista com base no nome
    public boolean deleteCategoriaPorNome(String nome) {
        int indice = this.procurarIndicePorNome(nome);
        CategoriaDAO.getListaCategoria().remove(indice);
        return true;
    }

    // Atualiza os dados de uma categoria com base no nome
    public boolean updateCategoriaNome(String nome, Categoria objeto) {
        int indice = this.procurarIndicePorNome(nome);
        CategoriaDAO.getListaCategoria().set(indice, objeto);
        return true;
    }
}
