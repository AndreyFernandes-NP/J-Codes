package bdprojeto;

/**
 *
 * @author Andrey
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JAVA_Database {
    
    public static boolean Database(String driver){
        try (Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")){
            System.out.println("Conectando ao BD...");
            if (conn != null){
                System.out.println("Conectado.");
                return true;
            }else{
                System.out.println("Falha ao conectar.");
            }
        }catch(SQLException e){
            System.out.printf("Estado do SQL: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
