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
 * Implementação JDBC da interface ProdutoDao para manipulação dos dados da
 * entidade Produto.
 *
 * Esta classe realiza operações CRUD (criar, ler, atualizar, deletar)
 * diretamente no banco de dados, utilizando uma conexão JDBC fornecida. Garante
 * a persistência dos dados dos produtos e mantém a integridade das informações
 * relacionadas a categorias e registros.
 *
 * @author Lorenzo
 */
public class RegistroDaoJDBC implements RegistroDao {

    private Connection conn;

    /**
     * Construtor que recebe a conexão com o banco.
     *
     * @param conn conexão JDBC com o banco de dados
     */
    public RegistroDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    /**
     * Adiciona um registro representando a entrada de um produto no sistema.
     *
     * Essa operação registra a movimentação como uma entrada (inclusão) de
     * determinada quantidade de um tipo de produto, com data, status e tipo de
     * movimentação.
     *
     * @param reg objeto Registro contendo os dados da entrada do produto
     * @throws DbException se ocorrer algum erro ao inserir no banco de dados
     */
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

    /**
     * Registra a saída de um produto do sistema, ou seja, uma movimentação de
     * remoção.
     *
     * Ao invés de deletar um registro existente, este método adiciona um novo
     * registro com movimentação do tipo SAIDA e status adequado (como FORA ou
     * REMOVIDO), mantendo o histórico completo das operações.
     *
     * @param reg objeto Registro contendo os dados da saída/remoção do produto
     * @throws DbException se ocorrer algum erro ao inserir no banco de dados
     */
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

    /**
     * Registra uma atualização nos dados de um produto como uma nova
     * movimentação.
     *
     * Esse método insere um novo registro na tabela para manter o histórico da
     * modificação feita no produto, marcando a data atual e os novos dados.
     *
     * @param reg objeto Registro com os dados atualizados do produto
     * @throws DbException se ocorrer algum erro ao inserir no banco de dados
     */
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

    /**
     * Retorna a lista completa de registros de movimentações de produtos.
     *
     * Cada registro representa uma operação realizada sobre um produto,
     * incluindo entradas, saídas e atualizações, com as informações de data,
     * tipo, quantidade, tipo de movimentação e status.
     *
     * @return lista de registros encontrados no banco de dados
     * @throws DbException se ocorrer algum erro na consulta ao banco
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
     * Instancia e retorna um objeto Registro a partir dos dados obtidos de um
     * ResultSet.
     *
     * Este método auxilia na conversão dos dados da tabela `registro` para
     * objetos Java.
     *
     * @param rs ResultSet com os dados do banco
     * @param produto objeto Produto associado ao tipo do registro
     * @return objeto Registro populado com os dados do ResultSet
     * @throws SQLException se ocorrer erro durante a leitura dos dados
     */
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
