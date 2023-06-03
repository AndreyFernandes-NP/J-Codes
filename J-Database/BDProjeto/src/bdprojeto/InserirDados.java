
package bdprojeto;

/**
 *
 * @author Andrey
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InserirDados {
    public static Scanner ler = new Scanner(System.in);
    public static String value1, value2;
    
    public static boolean Table (String tname, String param1, String p1type, String param2, String p2type, String driver, int reg) throws InterruptedException {
        Statement st = null;
        try(Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")) {
            for(int i = 0; i < reg; i++){
                System.out.print("\033[H\033[2J");
                System.out.println("// Insira os Dados em " + tname + " \\\\\n");
                System.out.printf("%s: ", param1);
                value1 = ler.next();
                System.out.printf("%s: ", param2);
                value2 = ler.next();
                
                if(p1type.contains("VARCHAR")){
                    value1 = "'" + value1 + "'";
                }
                
                if(p2type.contains("VARCHAR")){
                    value2 = "'" + value2 + "'";
                }
            
                String SQLinserirDados = "INSERT INTO " + tname + " (" + param1 + ", " + param2 + ") VALUES (" + value1 + " , " + value2 + ")";
                st = conn.createStatement();
                st.executeUpdate(SQLinserirDados);
            }
            
            st.close();
            conn.close();
            
            System.out.println("\nDados inseridos!");
            Thread.sleep(1500);
            return true;
        }catch (SQLException e) {
            System.err.format("Estado SQL: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
