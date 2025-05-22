<<<<<<< HEAD:src/main/java/model/dao/CategoriaDao.java
package model.dao;
=======

package modelo.dao;
>>>>>>> 44aa295 (Atualização nos nomes dos pacotes):src/main/java/modelo/dao/CategoriaDao.java

import java.util.List;
import modelo.Categoria;

public interface CategoriaDao {
    
    public void cadastrarCategoria(Categoria obj);
    
    public void atualizarCategoria(Categoria obj);
    
    public void deletarCategoriaPorId(int id);
    
    public List<Categoria> resgatarCategorias();
    
}
