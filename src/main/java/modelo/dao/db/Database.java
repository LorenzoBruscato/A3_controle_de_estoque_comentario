package modelo.dao.db;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 *
 * @author Lorenzo
 */
public class Database {
    /**
     * Atributo do tipo Connection para realizar a conexão com o banco de dados
     */
    private Connection conn = null;

    /**
     * Retorna uma conexão com o banco de dados, carregando as propriedades do
     * arquivo
     *
     * @return Connection ativa com o banco de dados
     */
    public Connection getConnection() {
        if (conn == null) {
            try {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");
                conn = DriverManager.getConnection(url, props);
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
        return conn;
    }

    /**
     * Fecha a conexão com o banco de dados se ela estiver aberta.
     */
    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Fecha um objeto {@link Statement} caso ele não seja {@code null}.
     *
     * <p>
     * Esse método é utilizado para liberar os recursos associados a um
     * {@link Statement} após sua execução, evitando vazamentos de memória e
     * conexões abertas no banco de dados.</p>
     *
     * @param st O {@link Statement} a ser fechado
     * @throws DbException Se ocorrer uma {@link SQLException} durante o
     * fechamento
     */
    public static void closeStatement(Statement st) {
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Fecha um objeto {@link ResultSet} caso ele não seja {@code null}.
     *
     * <p>
     * Esse método é utilizado para liberar os recursos associados a um
     * {@link ResultSet} após sua utilização, evitando vazamentos de recursos e
     * mantendo a aplicação eficiente.</p>
     *
     * @param rs O {@link ResultSet} a ser fechado
     * @throws DbException Se ocorrer uma {@link SQLException} durante o
     * fechamento
     */
    public static void closeResultSet(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException(e.getMessage());
            }
        }
    }

    /**
     * Método para carregar as propriedades do arquivo db.properties
     *
     * @return Properties objeto contendo as propriedades carregadas
     * @throws IOException se ocorrer erro ao ler o arquivo
     */
    private static Properties loadProperties() {
        try (InputStream in = Database.class.getClassLoader().getResourceAsStream("db.propriedade")) {
            Properties props = new Properties();
            props.load(in);
            System.out.println("URL do banco carregada: " + props.getProperty("dburl"));
            return props;
        } catch (IOException e) {
            throw new DbException("Erro ao carregar db.properties: " + e.getMessage());
        }
    }
}