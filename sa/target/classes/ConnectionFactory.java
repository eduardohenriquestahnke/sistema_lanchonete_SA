import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static final Properties PROPERTIES = new Properties();
    static{
        try (InputStream input = ConnectionFactory.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (input == null) {
                System.err.println("Erro: Arquivo 'db.properties' não encontrado.");
                // Opcional: Lançar uma exceção de tempo de execução
            }
            PROPERTIES.load(input);
            // Carrega o Driver JDBC
            Class.forName(PROPERTIES.getProperty("db.driver"));
        } catch (IOException e) {
            System.err.println("Erro ao carregar o arquivo de propriedades: " + e.getMessage());
            throw new RuntimeException("Falha ao configurar a conexão.", e);
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver JDBC não encontrado: " + e.getMessage());
            throw new RuntimeException("Driver MySQL não encontrado.", e);
        }

    }

    public static Connection getConnection() throws SQLException {
        String url = PROPERTIES.getProperty("db.url");
        String user = PROPERTIES.getProperty("db.user");
        String password = PROPERTIES.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

    
    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}