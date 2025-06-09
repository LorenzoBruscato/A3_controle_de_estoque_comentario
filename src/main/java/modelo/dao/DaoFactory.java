package modelo.dao;

import modelo.dao.db.Database;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;
import modelo.dao.impl.RegistroDaoJDBC;

/**
 * Fábrica de objetos DAO para acesso ao banco de dados. Fornece instâncias dos
 * DAOs para Produto, Categoria e Registro.
 *
 * @author Diego
 */
public class DaoFactory {

    private Database database = new Database();

    /**
     * Instancia o DAO para manipulação de produtos.
     *
     * @return objeto ProdutoDao
     */
    public ProdutoDao instanciarProdutoDao() {
        return new ProdutoDaoJDBC(database.getConnection());
    }

    /**
     * Instancia o DAO para manipulação de categorias.
     *
     * @return objeto CategoriaDao
     */
    public CategoriaDao instanciarCategoriaDao() {
        return new CategoriaDaoJDBC(database.getConnection());
    }

    /**
     * Instancia o DAO para manipulação de registros.
     *
     * @return objeto RegistroDao
     */
    public RegistroDao insinstanciarRegistro() {
        return new RegistroDaoJDBC(database.getConnection());
    }
}
