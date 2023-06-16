package quimiobank;

/**
 *
 * @author Andrey
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

public class HUB_UPD_DB { //Hub Update element in DataBase, nome da classe.
    public static Scanner ler = new Scanner(System.in);
    public static String SQLAtualizarDados, Database, UserL, Pass, QType, END;
    
    //Parâmetros da Tabela\\
    public static String TableName, Params, PValues, Value, Type;
    public static String[] IParams, ITypes;
    public static int ID;
    
    public static Statement st = null;
    
    public static void CST_CMM() throws InterruptedException { //Custom_Commands
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Hub Atualizacoes      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o comando de Atualizacao a ser executado: ");
        ler.nextLine();
        SQLAtualizarDados = ler.nextLine();
        
        UPDATE();
    }
    
    public static void UPDATE() throws InterruptedException {
        SQLAtualizarDados = "UPDATE " + TableName + " SET ";
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Hub Atualizacoes      \\\\"); 
        System.out.println("//=============================\\\\\n");
            
        System.out.println("1 - Atualizar por ID.");
        System.out.println("2 - Atualizar por Nome de Substancia.");
        System.out.println("3 - Atualizar por Forma Molecular.");
        
        System.out.print("Escolha: ");
        
        switch(ler.nextInt()){
            case 1:
                QType = "o ID: ";
                END = "WHERE id = ";
                break;
            case 2:
                QType = "o Nome da Substancia: ";
                END = "WHERE substância = ";
                break;
            case 3:
                QType = "a Forma Molecular: ";
                END = "WHERE forma_molecular = ";
                break;
            default:
                return;
        }
        ler.nextLine();
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Hub Atualizacoes      \\\\"); 
        System.out.println("//=============================\\\\\n");
            
        System.out.printf("Digite %s", QType);
        END += "'" + ler.nextLine() + "'";
        //System.out.printf("QB-DEBUGG, END VALUE: %s\n", END);
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Hub Atualizacoes      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.printf("Colunas de %s: %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s.\n", TableName, IParams[0], IParams[1].replace(" ", "_"),                     //O crime
                IParams[2].replace(" ", "_"), IParams[3].replace(" ", "_"), IParams[4],IParams[5].replace(" ", "_"),                   //nunca
                IParams[6].replace(" ", "_"), IParams[7].replace(" ", "_"), IParams[8], IParams[9].replace(" ", "_"), IParams[10]);    //acaba.
        
        System.out.print("Escolha a coluna que deseja atualizar: ");
        SQLAtualizarDados += ler.next();
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Hub Atualizacoes      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o novo valor: ");
        SQLAtualizarDados += " = '" + ler.next() + "' " + END;
        
        UPDATER();
    }
    
    public static void UPDATER() throws InterruptedException {
        //System.out.printf("QB-DEBUGG, SQL COMMAND: %s\n", SQLAtualizarDados);
        try(Connection conn = DriverManager.getConnection(Database, UserL, Pass)){
            st = conn.createStatement();
            System.out.println("QB: Atualizando novos dados...");
            Thread.sleep(3 + new Random().nextInt(7) * 100);
            st.executeUpdate(SQLAtualizarDados);
            System.out.println("QB: Dados atualizados!");
            Thread.sleep(1000);
        }catch(SQLException e){
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
            Thread.sleep(1500);
        }
    }
    
    public static void UPD_OPT(String DB, String User, String Password, String Table, String PFunc, String[] ExParams, String[] ExTypes) throws InterruptedException { //Insert_Options, :clownface: a quantidade de parâmetros KKKKK
        Database = DB;
        UserL = User;
        Pass = Password;
        TableName = Table;
        Params = PFunc;
        IParams = ExParams;
        ITypes = ExTypes;
        
        loop:
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//       Hub Atualizacoes      \\\\"); 
            System.out.println("//=============================\\\\\n");
            
            System.out.println("1 - Atualizar Substancia.");
            System.out.println("a - Executar um codigo Postgre de atualizacao.");
            System.out.println("Sair - Digite qualquer outra coisa.");
            
            System.out.print("Escolha: ");
            
            switch(ler.next().toLowerCase()){
                case "1":
                    UPDATE();
                    break;
                case "a":
                    CST_CMM();
                    break;
                default:
                    break loop;
            }
        }
    }
}
