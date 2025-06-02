package controlador;

import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.Registro;
import modelo.dao.CategoriaDao;
import modelo.dao.ProdutoDao;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;

public class ProdutoControle {

    private ProdutoDao produtoDao;
    private CategoriaDao categoriaDao;

    public ProdutoControle(Connection conn) {
        this.produtoDao = new ProdutoDaoJDBC(conn);
        this.categoriaDao = new CategoriaDaoJDBC(conn);
    }

    public void cadastrarProduto(Produto produto) {
        if (produto == null || produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Produto inválido. Nome é obrigatório.");
        }
        produtoDao.cadastrarProduto(produto, new Registro());
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

    public void aumentarTodosPrecos(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para aumentar.");
        }
        produtoDao.aumentarTodosPrecos(percentual);
    }

    public void diminuirTodosPrecos(double percentual) {
        if (percentual <= 0) {
            throw new IllegalArgumentException("Percentual deve ser maior que zero para diminuir.");
        }
        produtoDao.diminuirTodosPrecos(percentual);
    }

    public void aumentarPrecoPorCategoria(String nomeCategoria, double percentual) {
        Categoria cat = categoriaDao.CategoriabuscarPorNome(nomeCategoria);
        if (cat == null) {
            throw new IllegalArgumentException("Categoria não encontrada com nome: " + nomeCategoria);
        }
        produtoDao.aumentarPrecoPorCategoria(percentual, cat.getNome());
    }

    public void diminuirPrecoPorCategoria(String nomeCategoria, double percentual) {
        Categoria cat = categoriaDao.CategoriabuscarPorNome(nomeCategoria);
        if (cat == null) {
            throw new IllegalArgumentException("Categoria não encontrada com nome: " + nomeCategoria);
        }
        produtoDao.diminuirPrecoPorCategoria(percentual, cat.getNome());
    }
}
