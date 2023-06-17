package quimiobank;

/**
 *
 * @author Andrey
 */

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random; //Hihihi haw.
import java.sql.ResultSet; //Mostra todas as tabelas que encontrar.

public class GAT_DB { // Get All Tables in DataBase, o nome da classe.  
    public static String Tables = "";
    public static boolean GetTable(String BD, String User, String Password) throws InterruptedException {
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Consultar Tabelas     \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.println("QB: Procurando tabelas...");
        Thread.sleep(3 + new Random().nextInt(7) * 100);
        
        String SQLChecarTabela = "SELECT table_name FROM information_schema.tables WHERE table_schema='public'";
        Statement st;
        ResultSet result;
        
        try(Connection conn = DriverManager.getConnection(BD, User, Password)) {
            st = conn.createStatement();
            result = st.executeQuery(SQLChecarTabela);
            while(result.next()){
                if(result.isFirst()){
                    Tables = "Tabelas encontradas: " + result.getString(1);
                }else{
                  Tables += ", " + result.getString(1);  
                }
            }
            Tables += ".";
            System.out.printf("%s\n", Tables);
            Thread.sleep(2500);
            
        }catch(SQLException e) {
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
