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
 * Classe utilitária responsável por gerenciar a conexão com o banco de dados.
 *
 * <p>
 * Fornece métodos para obter e fechar conexões, além de fechar objetos
 * {@link Statement} e {@link ResultSet}. Também realiza o carregamento das
 * configurações de conexão a partir do arquivo de propriedades
 * <code>db.properties</code>.</p>
 *
 * <p>
 * Utiliza a exceção personalizada {@link DbException} para encapsular possíveis
 * erros de SQL ou de leitura de arquivos.</p>
 *
 * @author Diego
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
