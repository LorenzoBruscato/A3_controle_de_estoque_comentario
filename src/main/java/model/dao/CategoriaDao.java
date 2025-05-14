/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.Categoria;

/**
 *
 * @author Diiego
 */
public interface CategoriaDao {
    
    public void cadastrarCategoria(Categoria obj);
    
    public void atualizarCategoria(Categoria obj);
    
    public void deletarCategoriaPorId(int id);
    
    public List<Categoria> resgatarCategorias();
    
}
