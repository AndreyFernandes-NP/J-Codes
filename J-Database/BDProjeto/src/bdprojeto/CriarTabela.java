package bdprojeto;

/**
 *
 * @author Andrey
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CriarTabela {
    
    public static boolean Table(String tname, String param1, String p1type, String param2, String p2type, String driver){
        String SQLcriarTabela = "CREATE TABLE " + tname + " (" + param1 + " " + p1type + ", " + param2 + " " + p2type + ")";
        Statement st = null;
        try(Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")){
            System.out.println("Criando tabela, aguarde...");
            st = conn.createStatement();
            st.executeUpdate(SQLcriarTabela);
            System.out.println("Tabela criada com sucesso.");
            st.close();
            conn.close();
            return true;
        }catch(SQLException e){
            System.err.format("Estado do SQl: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}

