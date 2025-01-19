package config;

/**
 * @author joaoleal
 */


public interface credenciaisDB {
    /**
     * Constantes para acesso ao banco de dados - MySQL (XAMPP).
     */
    String DRV = "com.mysql.cj.jdbc.Driver";
    String URL = "jdbc:mysql://localhost:3307/autor_db"; // Porta padrão do MySQL => 3306 
    String USER = "root";
    String PASSWORD = "123456";
}
