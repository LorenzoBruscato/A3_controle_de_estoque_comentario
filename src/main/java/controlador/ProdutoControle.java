package controlador;

import java.sql.Connection;
import java.util.List;
import modelo.Produto;
import modelo.dao.ProdutoDao;
import modelo.dao.impl.ProdutoDaoJDBC;

public class ProdutoControle {

    private ProdutoDao produtoDao;

    public ProdutoControle(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
    }

    public void cadastrarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Produto inválido. Nome é obrigatório.");
        }
        produtoDao.cadastrarProduto(produto);
    }

    public void atualizarProduto(Produto produto) {
        if (produto == null || produto.getId() <= 0) {
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

    public void aumentarPreco(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para aumentar.");
        }
        produtoDao.aumentarPreco(percentual);
    }

    public void diminuirPreco(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para diminuir.");
        }
        produtoDao.diminuirPreco(percentual);
    }

}
