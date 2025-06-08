package controlador;

import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.dao.CategoriaDao;
import modelo.dao.impl.CategoriaDaoJDBC;

/**
 * Classe de controle responsável por gerenciar operações relacionadas à
 * entidade Categoria. Atua como intermediária entre a camada de apresentação e
 * a camada de persistência.
 *
 * Funcionalidades: - Cadastrar uma nova categoria. - Atualizar uma categoria
 * existente. - Deletar uma categoria pelo ID. - Listar todas as categorias.
 *
 * Regras de validação são aplicadas antes das operações de persistência.
 *
 * @author Henrique
 */
public class CategoriaControle {

    /**
     * Objeto de acesso a dados para Categoria.
     */
    private CategoriaDao categoriaDao;

    /**
     * Construtor que inicializa o DAO de Categoria com uma conexão específica.
     *
     * @param conn Conexão com o banco de dados.
     */
    public CategoriaControle(Connection conn) {
        this.categoriaDao = new CategoriaDaoJDBC(conn);
    }

    /**
     * Cadastra uma nova categoria após validar seus atributos obrigatórios.
     *
     * @param categoria Categoria a ser cadastrada.
     * @throws IllegalArgumentException se a categoria for nula ou se seus
     * atributos obrigatórios estiverem ausentes.
     */
    public void cadastrarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getNome() == null || categoria.getNome().isBlank()
                || categoria.getTamanho() == null || categoria.getEmbalagem() == null) {
            throw new IllegalArgumentException("Categoria inválida. Nome, Tamanho e Embalagem são obrigatórios.");
        }
        categoriaDao.cadastrarCategoria(categoria);
    }

    /**
     * Atualiza uma categoria existente, validando a presença de um ID válido.
     *
     * @param categoria Categoria a ser atualizada.
     * @throws IllegalArgumentException se a categoria for nula ou se o ID for
     * inválido.
     */
    public void atualizarCategoria(Categoria categoria) {
        if (categoria == null || categoria.getId() <= 0) {
            throw new IllegalArgumentException("Categoria inválida ou sem ID.");
        }
        categoriaDao.atualizarCategoria(categoria);
    }

    /**
     * Deleta uma categoria a partir do seu ID.
     *
     * @param id Identificador da categoria a ser deletada.
     * @throws IllegalArgumentException se o ID for inválido (menor ou igual a
     * zero).
     */
    public void deletarCategoria(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID inválido para exclusão.");
        }
        categoriaDao.deletarCategoriaPorId(id);
    }

    /**
     * Lista todas as categorias cadastradas no banco de dados.
     *
     * @return Lista de objetos Categoria.
     */
    public List<Categoria> listarCategorias() {
        return categoriaDao.resgatarCategorias();
    }
}
