package modelo.dao.db;

import java.io.InputStream;
import java.io.IOException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class Database {

    private Connection conn = null;

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
