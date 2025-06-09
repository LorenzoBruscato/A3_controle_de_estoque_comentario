package modelo.dao;

import java.util.List;
import modelo.Categoria;

/**
 * Interface para operações CRUD da entidade Categoria.
 *
 * @author Diego
 */
public interface CategoriaDao {

    /**
     * Cadastra uma nova categoria.
     *
     * @param obj objeto Categoria a ser cadastrado
     */
    public void cadastrarCategoria(Categoria obj);

    /**
     * Atualiza uma categoria existente.
     *
     * @param obj objeto Categoria com dados atualizados
     */
    public void atualizarCategoria(Categoria obj);

    /**
     * Deleta uma categoria pelo seu identificador.
     *
     * @param id identificador da categoria a ser deletada
     */
    public void deletarCategoriaPorId(int id);

    /**
     * Recupera a lista de todas as categorias cadastradas.
     *
     * @return lista de categorias
     */
    public List<Categoria> resgatarCategorias();

    /**
     * Procura uma categoria pelo seu identificador.
     *
     * @param id identificador da categoria
     * @return categoria encontrada ou null se não existir
     */
    Categoria procurarCategoriaPorId(Integer id);

    /**
     * Busca uma categoria pelo nome.
     *
     * @param nome nome da categoria
     * @return categoria encontrada ou null se não existir
     */
    Categoria CategoriabuscarPorNome(String nome);
}
