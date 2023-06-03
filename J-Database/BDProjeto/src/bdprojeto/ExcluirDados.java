package bdprojeto;

/**
 *
 * @author Andrey
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExcluirDados {
    public static void dados(String tname, String driver){
        String SQLexcluirDados = "DELETE FROM " + tname;
        Statement st = null;
        try(Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")){
            System.out.println("\nExcluindo dados da tabela...");
            st = conn.createStatement();
            st.executeUpdate(SQLexcluirDados);
            System.out.println("Dados excluidos.\n");
            st.close();
            conn.close();
        }catch(SQLException e){
            System.err.format("Estado SQL: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
    }
}
