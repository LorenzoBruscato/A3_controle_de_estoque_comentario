/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import db.Database;
import model.dao.impl.CategoriaDaoJDBC;
import model.dao.impl.ProdutoDaoJDBC;

/**
 *
 * @author Diiego
 */
public class DaoFactory {
    
    public static ProdutoDao instanciarProdutoDao(){
        return new ProdutoDaoJDBC(Database.getConnection());
    }
    
    public static CategoriaDao instanciarCategoriaDao(){
        return new CategoriaDaoJDBC(Database.getConnection());
    }
    
}
