package modelo.dao;

import java.util.List;
import modelo.Categoria;

/**
 * Interface para operações de acesso a dados (DAO) relacionadas à entidade Categoria.
 * Define métodos para cadastro, atualização, exclusão e consulta de categorias de produtos.
 */
public interface CategoriaDao {

    /**
     * Cadastra uma nova categoria no sistema.
     *
     * @param obj Objeto Categoria a ser cadastrado.
     */
    public void cadastrarCategoria(Categoria obj);

    /**
     * Atualiza os dados de uma categoria existente.
     *
     * @param obj Objeto Categoria com os dados atualizados.
     */
    public void atualizarCategoria(Categoria obj);

    /**
     * Remove uma categoria do sistema com base no seu identificador.
     *
     * @param id Identificador da categoria a ser deletada.
     */
    public void deletarCategoriaPorId(int id);

    /**
     * Recupera a lista completa de categorias cadastradas.
     *
     * @return Lista de objetos Categoria.
     */
    public List<Categoria> resgatarCategorias();

    /**
     * Busca uma categoria pelo seu identificador.
     *
     * @param id Identificador da categoria a ser buscada.
     * @return Objeto Categoria correspondente ao ID ou null se não encontrada.
     */
    Categoria procurarCategoriaPorId(Integer id);

    /**
     * Busca uma categoria pelo seu nome.
     *
     * @param nome Nome da categoria a ser buscada.
     * @return Objeto Categoria correspondente ao nome ou null se não encontrada.
     */
    Categoria CategoriabuscarPorNome(String nome);

}
