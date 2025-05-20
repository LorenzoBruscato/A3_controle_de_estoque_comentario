package model.dao.impl;

import db.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DbException;
import java.util.HashMap;
import java.util.Map;
import model.Categoria;
import model.Categoria.Embalagem;
import model.Categoria.Tamanho;
import model.Produto;
import model.dao.ProdutoDao;

/**
 *
 * @author Lorenzo
 */
public class ProdutoDaoJDBC implements ProdutoDao {

    private Connection conn;

    public ProdutoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarProduto(Produto obj) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO produto "
                    + "(nome, preco, unidade, quantidade, quantidade_minima, quantidade_maxima, categoria_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");

            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setInt(7, obj.getCategoria().getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
            } else {
                throw new DbException("Unexpected error! No rows affected");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao cadastrar produto: " + e.getMessage());
        } finally {
            Database.closeStatement(st);
        }
    }

    @Override
    public void atualizarProduto(Produto obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("UPDATE produto SET "
                    + "nome = ?, "
                    + "preco = ?, "
                    + "unidade = ?, "
                    + "quantidade = ?, "
                    + "quantidade_minima = ?, "
                    + "quantidade_maxima = ?, "
                    + "categoria_id = ? "
                    + "WHERE id = ?");

            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setInt(7, obj.getCategoria().getId());
            st.setInt(8, obj.getId());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        } finally {
            Database.closeStatement(st);
        }
    }

    @Override
    public void deletarProdutoPorId(int objId) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM produto WHERE id = ?");

            st.setInt(1, objId);

            int rows = st.executeUpdate();
            if (rows == 0) {
                throw new DbException("ID do produto não foi encontrado");
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        } finally {
            Database.closeStatement(st);
        }
    }

    @Override
    public List<Produto> resgatarProdutos() {
        List<Produto> lista = new ArrayList<>();
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT p.id AS id, p.nome, p.preco, p.unidade, p.quantidade, "
                    + "p.quantidade_minima, p.quantidade_maxima, p.categoria_id AS categoria_id, "
                    + "c.nome AS categoria_nome, c.tamanho, c.embalagem "
                    + "FROM produto p "
                    + "JOIN categoria c ON p.categoria_id = c.id "
                    + "ORDER BY p.nome"
            );

            rs = st.executeQuery();

            Map<Integer, Categoria> map = new HashMap<>();

            while (rs.next()) {
                int categoriaId = rs.getInt("categoria_id");

                Categoria cat = map.get(categoriaId);
                if (cat == null) {
                    cat = instanciarCategoria(rs, categoriaId);
                    map.put(categoriaId, cat);
                }

                Produto prod = instanciarProduto(rs, cat);
                lista.add(prod);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao resgatar produtos: " + e.getMessage());
        } finally {
            Database.closeResultSet(rs);
            Database.closeStatement(st);
        }

        return lista;
    }

    private Produto instanciarProduto(ResultSet rs, Categoria cat) throws SQLException {
        Produto prod = new Produto();
        prod.setId(rs.getInt("id"));
        prod.setNome(rs.getString("nome"));
        prod.setPreco(rs.getDouble("preco"));
        prod.setUnidade(rs.getString("unidade"));
        prod.setQuantidade(rs.getInt("quantidade"));
        prod.setQuantidadeMinima(rs.getInt("quantidade_minima"));
        prod.setQuantidadeMaxima(rs.getInt("quantidade_maxima"));
        prod.setCategoria(cat);
        return prod;
    }

    private Categoria instanciarCategoria(ResultSet rs, int categoriaId) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(categoriaId);
        cat.setNome(rs.getString("categoria_nome"));
        cat.setTamanho(Tamanho.valueOf(rs.getString("tamanho")));
        cat.setEmbalagem(Embalagem.valueOf(rs.getString("embalagem")));
        return cat;
    }
}
