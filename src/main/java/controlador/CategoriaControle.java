package controlador;

import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.dao.CategoriaDao;
import modelo.dao.impl.CategoriaDaoJDBC;

public class CategoriaControle {

    private CategoriaDao categoriaDao;

    public CategoriaControle(Connection conn) {
        this.categoriaDao = new CategoriaDaoJDBC(conn);
    }

    public void cadastrarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNome() == null || categoria.getNome().isBlank()
                || categoria.getTamanho() == null || categoria.getEmbalagem() == null) {
            throw new IllegalArgumentException("Categoria inválida. Nome, Tamanho e Embalagem são obrigatórios.");
        }
        categoriaDao.cadastrarCategoria(categoria);
    }

    public void atualizarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getId() <= 0) {
            throw new IllegalArgumentException("Categoria inválida ou sem ID.");
        }
        categoriaDao.atualizarCategoria(categoria);
    }

    public void deletarCategoria(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        categoriaDao.deletarCategoriaPorId(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaDao.resgatarCategorias();
    }
}
