package modelo.dao;

import java.util.List;
import modelo.Categoria;

public interface CategoriaDao {

    public void cadastrarCategoria(Categoria obj);

    public void atualizarCategoria(Categoria obj);

    public void deletarCategoriaPorId(int id);

    public List<Categoria> resgatarCategorias();

}
