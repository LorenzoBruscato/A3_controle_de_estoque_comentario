package modelo.dao;

import java.util.List;
import modelo.Categoria;

/**
 *
 *
 */
public interface CategoriaDao {

    /**
     *
     * @param obj
     */
    public void cadastrarCategoria(Categoria obj);

    /**
     *
     * @param obj
     */
    public void atualizarCategoria(Categoria obj);

    /**
     *
     * @param id
     */
    public void deletarCategoriaPorId(int id);

    /**
     *
     * @return
     */
    public List<Categoria> resgatarCategorias();

    /**
     *
     * @param id
     * @return
     */
    Categoria procurarCategoriaPorId(Integer id);

    /**
     *
     * @param nome
     * @return
     */
    Categoria CategoriabuscarPorNome(String nome);

}
