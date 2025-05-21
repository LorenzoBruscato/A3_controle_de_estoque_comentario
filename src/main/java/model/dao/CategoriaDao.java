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
