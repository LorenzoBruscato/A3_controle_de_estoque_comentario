package modelo.dao;

import modelo.dao.db.Database;
import modelo.dao.impl.CategoriaDaoJDBC;
import modelo.dao.impl.ProdutoDaoJDBC;

public class DaoFactory {
    
    Database database = new Database();
    
    public  ProdutoDao instanciarProdutoDao(){
        return new ProdutoDaoJDBC(database.getConnection());
    }
    
    public  CategoriaDao instanciarCategoriaDao(){
        return new CategoriaDaoJDBC(database.getConnection());
    }
    
}