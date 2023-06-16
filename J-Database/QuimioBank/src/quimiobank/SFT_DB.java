package quimiobank;

/**
 *
 * @author Andrey
 */

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random; //Me divirto muito mesmo.
import java.sql.ResultSet; //Serve para eu recuperar 'True/False' se a tabela foi encontrada ou não.

public class SFT_DB { // Search For Table in DataBase, o nome da classe.
    public static Scanner ler = new Scanner(System.in);
    public static String TableName;
    
    public static String Table(){
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Encontrar Tabelas     \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.printf("Digite o nome da Tabela que deseja modificar: ");
        TableName = ler.next();
        
        return TableName;
    }
    
    public static boolean FindTable(String BD, String User, String Password, String Table) throws InterruptedException {
        
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Procurando Tabela     \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.println("QB: Procurando tabela...");
        Thread.sleep(3 + new Random().nextInt(7) * 100);
        
        String SQLChecarTabela = "SELECT EXISTS (SELECT FROM information_schema.tables WHERE table_schema = 'public' AND table_name = '" + Table.toLowerCase() + "')";
        Statement st;
        ResultSet result;
        
        try(Connection conn = DriverManager.getConnection(BD, User, Password)) {
            st = conn.createStatement();
            result = st.executeQuery(SQLChecarTabela);
            result.next();
            //System.out.printf("QB-DEBUGG, SQL RECEIVED: %s\n", result.getBoolean(1));
            if(result.getBoolean(1)){   // Se o valor retornado pelo comando for 'True', irá prosseguir.
                System.out.println("QB: Tabela encontrada!");
                System.out.println("QB: Acessando tabela...");
                Thread.sleep(3 + new Random().nextInt(7) * 100);

                result.close();
                st.close();
                conn.close();
                
                return true;
            }else{
                System.err.format("QB-ERROR: TABELA %s NAO ENCONTRADA!\n", Table.toUpperCase());
                Thread.sleep(1500);
            }
        }catch(SQLException e) {
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
