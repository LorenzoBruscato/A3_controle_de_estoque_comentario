package model.dao.impl;

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
        String sql = "INSERT INTO produto "
                + "(nome, preco, unidade, quantidade, quantidade_minima, quantidade_maxima, categoria_id) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setInt(7, obj.getCategoria().getIdc());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Erro: Nenhuma linha foi inserida.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao inserir produto: " + e.getMessage());
        }
    }

    @Override
    public void atualizarProduto(Produto obj) {
        String sql = "UPDATE produto SET "
                + "nome = ?, "
                + "preco = ?, "
                + "unidade = ?, "
                + "quantidade = ?, "
                + "quantidade_minima = ?, "
                + "quantidade_maxima = ?, "
                + "categoria_id = ? "
                + "WHERE idp = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNome());
            st.setDouble(2, obj.getPreco());
            st.setString(3, obj.getUnidade());
            st.setInt(4, obj.getQuantidade());
            st.setInt(5, obj.getQuantidadeMinima());
            st.setInt(6, obj.getQuantidadeMaxima());
            st.setInt(7, obj.getCategoria().getIdc());
            st.setInt(8, obj.getIdp());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Nenhum produto encontrado com esse ID.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Override
    public void deletarProdutoPorId(int objId) {
        String sql = "DELETE FROM produto WHERE idp = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setInt(1, objId);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Nenhum produto encontrado com esse ID.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao deletar produto: " + e.getMessage());
        }
    }

    @Override
    public List<Produto> resgatarProdutos() {
        List<Produto> lista = new ArrayList<>();
        String sql = "SELECT p.id AS idp, p.nome, p.preco, p.unidade, p.quantidade, "
                + "p.quantidade_minima, p.quantidade_maxima, p.categoria_id AS categoria_id, "
                + "c.nome AS categoria_nome, c.tamanho, c.embalagem "
                + "FROM produto p "
                + "JOIN categoria c ON p.categoria_id = c.id "
                + "ORDER BY p.nome";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            List<Produto> list = new ArrayList<>();
            Map<Integer, Categoria> map = new HashMap<>();
            
            while (rs.next()) {
                
                Categoria cat = map.get(rs.getInt("Idc"));
                if(cat == null){
                    cat = instanciarCategoria(rs);
                    map.put(rs.getInt("Idc"), cat);
                }
                Produto prod = instanciarProduto(rs, cat);
                lista.add(prod);
            }
        } catch (SQLException e) {
            throw new DbException("Erro ao resgatar produtos: " + e.getMessage());
        }

        return lista;
    }

    private Produto instanciarProduto(ResultSet rs, Categoria cat) throws SQLException {
        Produto prod = new Produto();
        prod.setIdp(rs.getInt("idp"));
        prod.setNome(rs.getString("nome"));
        prod.setPreco(rs.getDouble("preco"));
        prod.setUnidade(rs.getString("unidade"));
        prod.setQuantidade(rs.getInt("quantidade"));
        prod.setQuantidadeMinima(rs.getInt("quantidade_minima"));
        prod.setQuantidadeMaxima(rs.getInt("quantidade_maxima"));
        prod.setCategoria(cat);
        return prod;
    }

    private Categoria instanciarCategoria(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("categoria_id"));
        cat.setNome(rs.getString("categoria_nome"));
        cat.setTamanho(rs.getString("tamanho"));
        cat.setEmbalagem(rs.getString("embalagem"));
        return cat;
    }
}
