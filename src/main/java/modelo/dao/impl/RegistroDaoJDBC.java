package modelo.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    
    @Override
    public List<Registro> resgatarRegistros() {
        List<Registro> lista = new ArrayList<>();
        Map<String, Produto> map = new HashMap<>();

        String sql = "SELECT * FROM registro ORDER BY data DESC";

        try (PreparedStatement st = conn.prepareStatement(sql); ResultSet rs = st.executeQuery()) {

            while (rs.next()) {
                String nomeProduto = rs.getString("tipo");

                Produto prod = map.get(nomeProduto);
                if (prod == null) {
                    prod = new Produto();
                    prod.setNome(nomeProduto);
                    map.put(nomeProduto, prod);
                }

                Registro reg = instanciarRegistro(rs, prod);
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
        return reg;
    }
    
}
