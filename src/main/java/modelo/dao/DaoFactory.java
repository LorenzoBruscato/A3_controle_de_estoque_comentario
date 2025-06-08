package modelo.dao;

import modelo.dao.db.Database;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;
import modelo.dao.impl.RegistroDaoJDBC;

/**
 *
 *
 */
public class DaoFactory {

    Database database = new Database();

    /**
     *
     * @return
     */
    public ProdutoDao instanciarProdutoDao() {
        return new ProdutoDaoJDBC(database.getConnection());
    }

    /**
     *
     * @return
     */
    public CategoriaDao instanciarCategoriaDao() {
        return new CategoriaDaoJDBC(database.getConnection());
    }

    /**
     *
     * @return
     */
    public RegistroDao insinstanciarRegistro() {
        return new RegistroDaoJDBC(database.getConnection());
    }
}
