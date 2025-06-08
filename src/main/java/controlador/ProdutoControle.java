package controlador;

import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.dao.CategoriaDao;
import modelo.dao.ProdutoDao;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;

/**
 * Classe de controle responsável por gerenciar operações relacionadas à
 * entidade Produto. Atua como intermediária entre a camada de apresentação e a
 * camada de persistência.
 *
 * Funcionalidades: - Cadastrar, atualizar e deletar produtos. - Listar todos os
 * produtos. - Ajustar preços de produtos, de forma geral ou por categoria.
 *
 * Inclui validações básicas para integridade dos dados.
 *
 * @author Henrique
 */
public class ProdutoControle {

    /**
     * Objeto de acesso a dados para Produto.
     */
    private ProdutoDao produtoDao;

    /**
     * Objeto de acesso a dados para Categoria.
     */
    private CategoriaDao categoriaDao;

    /**
     * Construtor que inicializa os DAOs de Produto e Categoria com uma conexão
     * específica.
     *
     * @param conn Conexão com o banco de dados.
     */
    public ProdutoControle(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
        this.categoriaDao = new CategoriaDaoJDBC(conn);
    }

    /**
     * Cadastra um novo produto após validar seu nome.
     *
     * @param produto Produto a ser cadastrado.
     * @throws IllegalArgumentException se o produto for nulo ou se o nome
     * estiver ausente ou em branco.
     */
    public void cadastrarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Produto inválido. Nome é obrigatório.");
        }
        produtoDao.cadastrarProduto(produto);
    }

    /**
     * Atualiza um produto existente, validando a presença de um ID válido.
     *
     * @param produto Produto a ser atualizado.
     * @throws IllegalArgumentException se o produto for nulo ou se o ID for
     * inválido.
     */
    public void atualizarProduto(Produto produto) {
        if (produto == null || produto.getId() <= 0) {
            throw new IllegalArgumentException("Produto inválido ou sem ID.");
        }
        produtoDao.atualizarProduto(produto);
    }

    /**
     * Deleta um produto a partir do seu ID.
     *
     * @param id Identificador do produto a ser deletado.
     * @throws IllegalArgumentException se o ID for inválido (menor ou igual a
     * zero).
     */
    public void deletarProduto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        produtoDao.deletarProdutoPorId(id);
    }

    /**
     * Lista todos os produtos cadastrados no banco de dados.
     *
     * @return Lista de objetos Produto.
     */
    public List<Produto> listarProdutos() {
        return produtoDao.resgatarProdutos();
    }

    /**
     * Aumenta o preço de todos os produtos cadastrados, com base em um
     * percentual informado.
     *
     * @param percentual Percentual de aumento (deve ser maior que zero).
     * @throws IllegalArgumentException se o percentual for menor ou igual a
     * zero.
     */
    public void aumentarTodosPrecos(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para aumentar.");
        }
        produtoDao.aumentarTodosPrecos(percentual);
    }

    /**
     * Diminui o preço de todos os produtos cadastrados, com base em um
     * percentual informado.
     *
     * @param percentual Percentual de redução (deve ser maior que zero).
     * @throws IllegalArgumentException se o percentual for menor ou igual a
     * zero.
     */
    public void diminuirTodosPrecos(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para diminuir.");
        }
        produtoDao.diminuirTodosPrecos(percentual);
    }

    /**
     * Aumenta o preço de todos os produtos de uma categoria específica.
     *
     * @param nomeCategoria Nome da categoria cujos produtos terão os preços
     * ajustados.
     * @param percentual Percentual de aumento (deve ser maior que zero).
     * @throws IllegalArgumentException se a categoria não for encontrada ou se
     * o percentual for inválido.
     */
    public void aumentarPrecoPorCategoria(String nomeCategoria, double percentual) {
        Categoria cat = categoriaDao.CategoriabuscarPorNome(nomeCategoria);
        if (cat == null) {
            throw new IllegalArgumentException("Categoria não encontrada com nome: " + nomeCategoria);
        }
        produtoDao.aumentarPrecoPorCategoria(percentual, cat.getNome());
    }

    /**
     * Diminui o preço de todos os produtos de uma categoria específica.
     *
     * @param nomeCategoria Nome da categoria cujos produtos terão os preços
     * ajustados.
     * @param percentual Percentual de redução (deve ser maior que zero).
     * @throws IllegalArgumentException se a categoria não for encontrada ou se
     * o percentual for inválido.
     */
    public void diminuirPrecoPorCategoria(String nomeCategoria, double percentual) {
        Categoria cat = categoriaDao.CategoriabuscarPorNome(nomeCategoria);
        if (cat == null) {
            throw new IllegalArgumentException("Categoria não encontrada com nome: " + nomeCategoria);
        }
        produtoDao.diminuirPrecoPorCategoria(percentual, cat.getNome());
    }
}
