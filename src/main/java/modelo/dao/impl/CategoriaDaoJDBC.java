package modelo.dao.impl;

import modelo.dao.db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Categoria.Embalagem;
import modelo.Categoria.Tamanho;
import modelo.dao.CategoriaDao;

/**
 * Implementação JDBC da interface CategoriaDao para manipulação de dados da
 * tabela categoria no banco de dados.
 *
 * @author Lorenzo
 */
public class CategoriaDaoJDBC implements CategoriaDao {

    private Connection conn;

    /**
     * Construtor que recebe a conexão com o banco.
     *
     * @param conn conexão JDBC com o banco de dados
     */
    public CategoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * Insere uma nova categoria no banco e define seu id.
     *
     * @param obj objeto Categoria a ser cadastrado
     */
    @Override
    public void cadastrarCategoria(Categoria obj) {
        String sql = "INSERT INTO categoria "
                + "(nome, tamanho, embalagem) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTamanho().name());
            st.setString(3, obj.getEmbalagem().name());
            int rowsAffected = st.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = st.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        obj.setId(id);
                    } else {
                        throw new DbException("Erro inesperado! Nenhuma linha afetada.");
                    }
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Atualiza os dados de uma categoria existente no banco.
     *
     * @param obj objeto Categoria com dados atualizados
     */
    @Override
    public void atualizarCategoria(Categoria obj) {
        String sql = "UPDATE categoria "
                + "SET nome = ?, tamanho = ?, embalagem = ? "
                + "WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTamanho().name());
            st.setString(3, obj.getEmbalagem().name());
            st.setInt(4, obj.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Remove uma categoria do banco pelo seu ID.
     *
     * @param id identificador da categoria a ser removida
     */
    @Override
    public void deletarCategoriaPorId(int id) {
        String sql = "DELETE FROM categoria WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new DbException("Nenhuma categoria encontrada com o ID informado.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Recupera todas as categorias do banco ordenadas por nome.
     *
     * @return lista de categorias
     */
    @Override
    public List<Categoria> resgatarCategorias() {
        String sql = "SELECT * FROM categoria ORDER BY nome";
        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            List<Categoria> list = new ArrayList<>();
            while (rs.next()) {
                Categoria cat = instantiateCategoria(rs);
                list.add(cat);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Busca uma categoria pelo seu ID.
     *
     * @param id identificador da categoria
     * @return categoria encontrada ou null se não existir
     */
    @Override
    public Categoria procurarCategoriaPorId(Integer id) {
        String sql = "SELECT * FROM categoria WHERE id = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, id);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instantiateCategoria(rs);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;
    }

    /**
     * Busca uma categoria pelo seu nome.
     *
     * @param nome nome da categoria
     * @return categoria encontrada ou null se não existir
     */
    @Override
    public Categoria CategoriabuscarPorNome(String nome) {
        String sql = "SELECT * FROM categoria WHERE nome = ?";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, nome);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    return instantiateCategoria(rs);
                }
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return null;
    }

    /**
     * Cria uma instância de Categoria a partir dos dados do ResultSet.
     *
     * @param rs ResultSet com os dados da categoria
     * @return objeto Categoria preenchido
     * @throws SQLException se ocorrer erro na leitura dos dados
     */
    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("id"));
        cat.setNome(rs.getString("nome"));
        cat.setTamanho(Tamanho.valueOf(rs.getString("tamanho").toUpperCase()));
        cat.setEmbalagem(Embalagem.valueOf(rs.getString("embalagem").toUpperCase()));
        return cat;
    }
}
