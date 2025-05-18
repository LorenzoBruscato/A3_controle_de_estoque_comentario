package controller;

import java.sql.Connection;
import java.util.List;
import model.Produto;
import model.dao.ProdutoDao;
import model.dao.impl.ProdutoDaoJDBC;

/**
 *
 * @author HenriqueBrosa
 */

public class ProdutoController {

    private ProdutoDao produtoDao;

    public ProdutoController(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
    }

    public void cadastrarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Produto inválido. Nome é obrigatório.");
        }
        produtoDao.cadastrarProduto(produto);
    }

    public void atualizarProduto(Produto produto) {
        if (produto == null || produto.getIdp() <= 0) {
            throw new IllegalArgumentException("Produto inválido ou sem ID.");
        }
        produtoDao.atualizarProduto(produto);
    }

    public void deletarProduto(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        produtoDao.deletarProdutoPorId(id);
    }

    public List<Produto> listarProdutos() {
        return produtoDao.resgatarProdutos();
    }
}