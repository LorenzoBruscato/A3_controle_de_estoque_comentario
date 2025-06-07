package modelo.dao;

import modelo.dao.db.Database;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;
import modelo.dao.impl.RegistroDaoJDBC;

/**
 * Fábrica de objetos DAO (Data Access Object) para acessar os dados da aplicação.
 * <p>
 * Esta classe encapsula a criação dos objetos DAO específicos que acessam
 * o banco de dados, utilizando uma conexão única provida pela classe {@link Database}.
 * </p>
 */
public class DaoFactory {
    
    Database database = new Database();
    
    /**
     * Cria e retorna uma instância de ProdutoDao implementada por ProdutoDaoJDBC.
     * 
     * @return uma instância de {@link ProdutoDao} para manipulação de produtos no banco de dados.
     */
    public ProdutoDao instanciarProdutoDao() {
        return new ProdutoDaoJDBC(database.getConnection());
    }
    
    /**
     * Cria e retorna uma instância de CategoriaDao implementada por CategoriaDaoJDBC.
     * 
     * @return uma instância de {@link CategoriaDao} para manipulação de categorias no banco de dados.
     */
    public CategoriaDao instanciarCategoriaDao() {
        return new CategoriaDaoJDBC(database.getConnection());
    }
    
    /**
     * Cria e retorna uma instância de RegistroDao implementada por RegistroDaoJDBC.
     * 
     * @return uma instância de {@link RegistroDao} para manipulação de registros no banco de dados.
     */
    public RegistroDao insinstanciarRegistro() {
        return new RegistroDaoJDBC(database.getConnection());
    }
}
