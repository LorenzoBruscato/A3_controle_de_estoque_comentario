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

public class RegistroDaoJDBC implements RegistroDao {

    private Connection conn;

    public RegistroDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void AdicionarProdutoRegistro(Registro reg) {
        String sql = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            java.sql.Date sqlDate = new java.sql.Date(reg.getData().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, reg.getTipoDoProduto().getNome());
            st.setInt(3, reg.getQuantidade());
            st.setString(4, reg.getMovimentacao().name());
            st.setString(5, reg.getStatus().name());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void RemoverProdutoRegistro(Registro reg) {
        String sql = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            java.sql.Date sqlDate = new java.sql.Date(reg.getData().getTime());
            st.setDate(1, sqlDate);
            st.setString(2, reg.getTipoDoProduto().getNome());
            st.setInt(3, reg.getQuantidade());
            st.setString(4, reg.getMovimentacao().name());
            st.setString(5, reg.getStatus().name());
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void AtualizarProdutoRegistro(Registro reg) {
        String sqlRegistro = "INSERT INTO registro (data, tipo, quantidade, movimentacao, status) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement st = conn.prepareStatement(sqlRegistro)) {
            st.setDate(1, new java.sql.Date(System.currentTimeMillis()));  // data atual
            st.setString(2, reg.getTipoDoProduto().getNome());             // nome do produto
            st.setInt(3, reg.getQuantidade());                             // quantidade
            st.setString(4, reg.getMovimentacao().name());                 // tipo movimentação
            st.setString(5, reg.getStatus().name());                       // status do registro
            int rowsAffected = st.executeUpdate();
            if (rowsAffected == 0) {
                throw new DbException("Erro inesperado! Nenhuma linha afetada.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

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
                    reg.setStatus(Registro.Status.DENTRO);
                }
                lista.add(reg);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    private Registro instanciarRegistro(ResultSet rs, Produto produto) throws SQLException {
        Registro reg = new Registro();
        reg.setId(rs.getInt("id"));
        reg.setData(rs.getDate("data"));
        reg.setTipoDoProduto(produto);
        reg.setQuantidade(rs.getInt("quantidade"));
        reg.setMovimentacao(Registro.Movimentacao.valueOf(rs.getString("movimentacao").toUpperCase()));
        reg.setStatus(Registro.Status.valueOf(rs.getString("status").toUpperCase()));
        return reg;
    }
}
