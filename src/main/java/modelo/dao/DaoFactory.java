package modelo.dao;

import modelo.dao.db.Database;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;

public class DaoFactory {
    
    public static ProdutoDao instanciarProdutoDao(){
        return new ProdutoDaoJDBC(Database.getConnection());
    }
    
    public static CategoriaDao instanciarCategoriaDao(){
        return new CategoriaDaoJDBC(Database.getConnection());
    }
    
}