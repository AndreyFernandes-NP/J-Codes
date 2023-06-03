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

public class AtualizarDados {
    public static Scanner ler = new Scanner(System.in);
    public static String value1, SQLatualizarDados;
    
    public static void dados(String tname, String param1, String param2, String p1type, String p2type, String driver){
        Statement st = null;
        try(Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")){
            first:
            while(true){
                System.out.print("\033[H\033[2J");
                System.out.println("// Teste de UPDATE em " + tname + " \\\\");
                System.out.println("Escolha qual deseja atualizar na tabela.\n");
                System.out.println("1 - " + param1 + ".\n2 - " + param2 + ".\n");
                System.out.print("Escolha: ");
            
                switch(ler.nextInt()){
                    case 1:
                        System.out.print("Digite o " + param1 + " que ira substituir todos os " + param1 + "s: ");
                        value1 = ler.next();
                        if(p1type.contains("VARCHAR")){
                            value1 = "'" + value1 + "'";
                        }
                        SQLatualizarDados = "UPDATE " + tname + " SET " + param1 + " = " + value1;
                        break first;
                    case 2:
                        System.out.print("Digite o " + param2 + " que ira substituir todos os " + param2 + "s: ");
                        value1 = ler.next();
                        if(p2type.contains("VARCHAR")){
                            value1 = "'" + value1 + "'";
                        }
                        SQLatualizarDados = "UPDATE " + tname + " SET " + param2 + " = " + value1;
                        break first;
                    default:
                        System.out.println("Escolha invalida, repita.\n");
                        break;
                }
            }
            st = conn.createStatement();
            st.executeUpdate(SQLatualizarDados);
            System.out.println("\nDados atualizados.\n");
            st.close();
            conn.close();
        }catch(SQLException e){
            System.err.format("Estado SQL: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
    }
}
