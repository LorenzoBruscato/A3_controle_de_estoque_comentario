/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao.impl;

import db.DbException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Categoria;
import model.Categoria.Embalagem;
import model.Categoria.Tamanho;
import model.dao.CategoriaDao;

/**
 *
 * @author Diiego
 */
public class CategoriaDaoJDBC implements CategoriaDao {

    private Connection conn;

    public CategoriaDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void cadastrarCategoria(Categoria obj) {
        String sql = "INSERT INTO categoria "
                + "(nome, tamanho, embalagem) "
                + " VALUES (?, ?, ?)";

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTamanho().name());
            st.setString(3, obj.getEmbalagem().name());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                } else {
                    throw new DbException("Unexpected error! No rows affected");
                }
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void atualizarCategoria(Categoria obj) {
        String sql = "UPDATE categoria "
                + "SET nome = ?, "
                + "tamanho = ?, "
                + "embalagem = ? "
                + "WHERE id = ?";

        try (PreparedStatement st = conn.prepareStatement(sql)) {
            st.setString(1, obj.getNome());
            st.setString(2, obj.getTamanho().name());
            st.setString(3, obj.getEmbalagem().name());
            st.setInt(4, obj.getId());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("Nenhum produto encontrado com esse ID.");
            }

        } catch (SQLException e) {
            throw new DbException("Erro ao atualizar produto: " + e.getMessage());
        }
    }

    @Override
    public void deletarCategoriaPorId(int id) {
        String sql = "DELETE FROM categoria WHERE Id = ?";

        try (PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            st.setInt(1, id);
            int rowsAffected = st.executeUpdate();

            if (rowsAffected == 0) {
                throw new DbException("No categoria found with the given Id");
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public List<Categoria> resgatarCategorias() {
        String sql = "SELECT * FROM categoria ORDER BY nome";
        try (PreparedStatement st = conn.prepareStatement(sql)) {
            try (ResultSet rs = st.executeQuery()) {

                List<Categoria> list = new ArrayList<>();

                while (rs.next()) {
                    Categoria cat = instantiateCategoria(rs);
                    list.add(cat);
                }

                return list;
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    private Categoria instantiateCategoria(ResultSet rs) throws SQLException {
        Categoria cat = new Categoria();
        cat.setId(rs.getInt("id"));
        cat.setNome(rs.getString("nome"));
        cat.setTamanho(Tamanho.valueOf(rs.getString("tamanho")));
        cat.setEmbalagem(Embalagem.valueOf(rs.getString("embalagem")));

        return cat;
    }
}
