package bdprojeto;

/**
 *
 * @author Andrey
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ConsultarDados {
    public static void dados(String tname, String driver){
        String SQLconsultarDados = "SELECT * FROM " + tname;
        Statement st;
        ResultSet result;
        
        try(Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")) {
            st = conn.createStatement();
            result = st.executeQuery(SQLconsultarDados);
            
            System.out.print("\033[H\033[2J");  
            System.out.println("// Consulta de Dados \\\\\n");
            
            while(result.next()){
                System.out.println("CPF: " + result.getString(1));
                System.out.println("Nome: " + result.getString(2) + "\n");
            }
            
            result.close();
            st.close();
            conn.close();
            
            System.out.println("Consulta finalizada.");
        }catch(SQLException e) {
            System.err.format("Estado SQL: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
    }
}
