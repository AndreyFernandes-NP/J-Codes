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

public class HUB_DEL_DB { //Hub Delete in Database, nome da classe.
    public static Scanner ler = new Scanner(System.in);
    public static String SQLDeletarDados, Database, UserL, Pass;
    
    //Parâmetros da Tabela\\
    public static String TableName, Params, PValues, Value, Type, QType, END;
    public static String[] IParams, ITypes;
    
    public static Statement st = null;
    
    public static void CST_CMM() throws InterruptedException { //Custom_Commands
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Exclusao      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o comando de Exclusao a ser executado: ");
        ler.nextLine();
        SQLDeletarDados = ler.nextLine();
        
        DELETER();
    }
    
    public static void DELETE(boolean Substance) throws InterruptedException {
        
        if(!Substance){
            SQLDeletarDados = "UPDATE " + TableName + " SET ";
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//        Hub de Exclusao      \\\\"); 
            System.out.println("//=============================\\\\\n");
        
            System.out.println("1 - Deletar dados por ID.");
            System.out.println("2 - Deletar dados por Nome de Substancia.");
            System.out.println("3 - Deletar dados por Forma Molecular.");
            
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
            System.out.println("//        Hub de Exclusao      \\\\"); 
            System.out.println("//=============================\\\\\n");
        
            System.out.printf("Digite %s", QType);
            END += "'" + ler.nextLine() + "'";            
            
            System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Exclusao      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.printf("Colunas de %s que podem ser deletadas: %s, %s, %s, %s, %s, %s.\n", TableName, IParams[1].replace(" ", "_"),
        IParams[4],IParams[5].replace(" ", "_"),IParams[6].replace(" ", "_"), IParams[9].replace(" ", "_"), IParams[10]);
        
        System.out.print("Escolha a coluna que deseja deletar: ");
        SQLDeletarDados += ler.next() + " = NULL " + END; 
        }else{
            SQLDeletarDados = "DELETE FROM " + TableName + " WHERE ";
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//        Hub de Exclusao      \\\\"); 
            System.out.println("//=============================\\\\\n");
        
            System.out.println("1 - Deletar por ID.");
            System.out.println("2 - Deletar por Nome de Substancia.");
            System.out.println("3 - Deletar por Forma Molecular.");
            
            System.out.print("Escolha: ");
            
            switch(ler.nextInt()){
            case 1:
                QType = "o ID: ";
                SQLDeletarDados += "id = ";
                break;
            case 2:
                QType = "o Nome da Substancia: ";
                SQLDeletarDados += "substância = ";
                break;
            case 3:
                QType = "a Forma Molecular: ";
                SQLDeletarDados += "forma_molecular = ";
                break;
            default:
                return;
            }
            ler.nextLine();
            
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Exclusao      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.printf("Digite %s", QType);
        SQLDeletarDados += "'" + ler.nextLine() + "'";
        }
        
        DELETER();
    }
    
    public static void DELTABLE() throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Exclusao      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.printf("Deseja mesmo deletar a tabela %s? (Y/N): ", TableName);
        switch(ler.next().toLowerCase()){
            case "y":
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Exclusao      \\\\"); 
                System.out.println("//=============================\\\\\n");
        
                System.out.printf("Tem certeza? (Y/N): ", TableName);
                
                switch(ler.next().toLowerCase()){
                    case "y":
                        System.out.print("\033[H\033[2J");
                        System.out.println("//=============================\\\\");
                        System.out.println("//          QuimioBank         \\\\");
                        System.out.println("//        Hub de Exclusao      \\\\"); 
                        System.out.println("//=============================\\\\\n");
                        
                        System.out.printf("Digite %s para deleta-la: ", TableName.toUpperCase());
                        SQLDeletarDados = "DROP TABLE " + ler.next().toLowerCase();
                        DELETER();
                        break;
                    case "n":
                        return;
                    default:
                        return;
                }
                break;
            case "n":
                return;
            default:
                return;
        }
    }
    
    public static void DELETER() throws InterruptedException {
        //System.out.printf("QB-DEBUGG, SQL COMMAND: %s\n", SQLDeletarDados);
        try(Connection conn = DriverManager.getConnection(Database, UserL, Pass)){
            st = conn.createStatement();
            System.out.println("QB: Deletando dados...");
            Thread.sleep(3 + new Random().nextInt(7) * 100);
            st.executeUpdate(SQLDeletarDados);
            System.out.println("QB: Dados deletados!");
            Thread.sleep(1000);
        }catch(SQLException e){
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
            Thread.sleep(1500);
        }
    }
    
    public static void DEL_OPT(String DB, String User, String Password, String Table, String PFunc, String[] ExParams, String[] ExTypes) throws InterruptedException { //Insert_Options, :clownface: a quantidade de parâmetros KKKKK
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
            System.out.println("//        Hub de Exclusao      \\\\"); 
            System.out.println("//=============================\\\\\n");
            
            System.out.printf("1 - Excluir dados em %s.\n", TableName);
            System.out.printf("2 - Excluir substancia em %s.\n", TableName);
            System.out.println("a - Executar um codigo Postgre de exclusao.");
            System.out.printf("del - Excluir tabela %s.\n", TableName);
            System.out.println("Sair - Digite qualquer outra coisa.");
            
            System.out.print("Escolha: ");
            
            switch(ler.next().toLowerCase()){
                case "1":
                    DELETE(false);
                    break;
                case "2":
                    DELETE(true);
                    break;
                case "a":
                    CST_CMM();
                    break;
                case "del":
                    DELTABLE();
                    break;
                default:
                    break loop;
            }
        }
    }
}
