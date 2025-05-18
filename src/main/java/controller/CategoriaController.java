package controller;

import java.sql.Connection;
import java.util.List;
import model.Categoria;
import model.dao.CategoriaDao;
import model.dao.impl.CategoriaDaoJDBC;

/**
 * Controller responsável por intermediar a comunicação entre a interface e o DAO de Categoria.
 */
public class CategoriaController {

    private CategoriaDao categoriaDao;

    public CategoriaController(Connection conn) {
        this.categoriaDao = new CategoriaDaoJDBC(conn);
    }

    public void cadastrarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNome() == null || categoria.getNome().isBlank()) {
            throw new IllegalArgumentException("Categoria inválida. Nome é obrigatório.");
        }
        categoriaDao.cadastrarCategoria(categoria);
    }

    public void atualizarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getIdc()<= 0) {
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