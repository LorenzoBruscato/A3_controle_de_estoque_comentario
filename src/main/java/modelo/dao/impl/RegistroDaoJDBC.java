package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Produto;
import modelo.Registro;
import modelo.dao.RegistroDao;
import modelo.dao.db.DbException;

/**
 * Implementação JDBC da interface RegistroDao para manipulação dos registros de
 * movimentação de produtos no banco de dados.
 *
 * @author Lorenzo
 */
public class RegistroDaoJDBC implements RegistroDao {

    private Connection conn;

    /**
     * Construtor que recebe a conexão JDBC ativa.
     *
     * @param conn conexão com o banco de dados.
     */
    public RegistroDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adiciona um registro de movimentação de produto no banco.
     *
     * @param reg Objeto Registro contendo os dados da movimentação.
     */
    @Override
    public void AdicionarProdutoRegistro(Registro reg) {
        String sql = "INSERT INTO registro "
                + "(data, tipo, quantidade, movimentacao) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            java.sql.Date sqlDate = new java.sql.Date(reg.getData().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, reg.getTipoDoProduto().getNome());
            st.setInt(3, reg.getQuantidade());
            st.setString(4, reg.getMovimentacao().name());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Registra a remoção de um produto no banco de dados.
     *
     * @param reg Objeto Registro contendo os dados da remoção.
     */
    @Override
    public void RemoverProdutoRegistro(Registro reg) {
        String sql = "INSERT INTO registro "
                + "(data, tipo, quantidade, movimentacao) "
                + "VALUES (?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            java.sql.Date sqlDate = new java.sql.Date(reg.getData().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, reg.getTipoDoProduto().getNome());
            st.setInt(3, reg.getQuantidade());
            st.setString(4, reg.getMovimentacao().name());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Recupera todos os registros de movimentação de produtos cadastrados no
     * banco.
     *
     * @return Lista com todos os registros encontrados.
     */
    @Override
    public List<Registro> resgatarRegistros() {
        List<Registro> lista = new ArrayList<>();
        String sql = "SELECT id, data, tipo, quantidade, movimentacao, status FROM registro";
        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {
            while (rs.next()) {
                Registro reg = new Registro();
                reg.setId(rs.getInt("id"));
                reg.setData(rs.getDate("data"));
                Produto produto = new Produto();
                produto.setNome(rs.getString("tipo"));
                reg.setTipoDoProduto(produto);
                reg.setQuantidade(rs.getInt("quantidade"));
                reg.setMovimentacao(Registro.Movimentacao.valueOf(rs.getString("movimentacao")));
                String statusStr = rs.getString("status");
                if (statusStr != null) {
                    reg.setStatus(Registro.Status.valueOf(statusStr));
                } else {
                    // Define valor padrão para evitar nulo
                    reg.setStatus(Registro.Status.DENTRO);
                }
                lista.add(reg);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    /**
     * Instancia um objeto Registro a partir do ResultSet e do produto
     * fornecido.
     *
     * @param rs ResultSet com os dados do registro.
     * @param produto Produto associado ao registro.
     * @return Objeto Registro populado.
     * @throws SQLException Caso ocorra erro no acesso ao ResultSet.
     */
    private Registro instanciarRegistro(ResultSet rs, Produto produto) throws SQLException {
        Registro reg = new Registro();
        reg.setId(rs.getInt("id"));
        reg.setData(rs.getDate("data"));
        reg.setTipoDoProduto(produto);
        reg.setQuantidade(rs.getInt("quantidade"));
        reg.setMovimentacao(Registro.Movimentacao.valueOf(rs.getString("movimentacao").toUpperCase()));
        return reg;
    }
}
