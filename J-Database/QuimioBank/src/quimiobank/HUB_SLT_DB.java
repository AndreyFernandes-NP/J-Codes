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
import java.sql.ResultSet;

public class HUB_SLT_DB { //Hub Select in Database, o nome da classe.
    public static Scanner ler = new Scanner(System.in);
    public static String SQLSelectDados, Database, UserL, Pass;
    
    //Parâmetros da Tabela\\
    public static String TableName, Params, PValues, Value, Type, QType, END;
    public static String[] IParams, ITypes;
    
    public static Statement st = null;
    public static ResultSet result = null;
      
    public static void SELECT() throws InterruptedException {
        SQLSelectDados = "SELECT * FROM " + TableName + " WHERE ";
        
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Consulta      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.println("1 - Consulta especifica de dados.");
        System.out.println("2 - Consultar se um dado contem algo.");
        System.out.println("Sair - Digite qualquer outra coisa.");
        
        System.out.print("Escolha: ");
        
        switch(ler.next()){
            case "1":
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Consulta      \\\\"); 
                System.out.println("//=============================\\\\\n");
                
                System.out.printf("Colunas de %s que podem ser consultadas: id, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s.\n", TableName, IParams[0], IParams[1].replace(" ", "_"),    //O crime
                IParams[2].replace(" ", "_"), IParams[3].replace(" ", "_"), IParams[4],IParams[5].replace(" ", "_"),                                        //nunca
                IParams[6].replace(" ", "_"), IParams[7].replace(" ", "_"), IParams[8], IParams[9].replace(" ", "_"), IParams[10]);                         //acaba.
                
                System.out.print("Escolha: ");
                SQLSelectDados += ler.next() + " ";
                
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Consulta      \\\\"); 
                System.out.println("//=============================\\\\\n");
                
                System.out.println("Operador = (Igual).");
                System.out.println("Operador > (Maior que).");
                System.out.println("Operador < (Menor que).");
                System.out.println("Operador >= (Igual ou Maior que).");
                System.out.println("Operador <= (Igual ou Menor que).");
                
                System.out.print("Escolha um dos operadores acima: ");
                SQLSelectDados += ler.next() + " ";
                ler.nextLine();
                
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Consulta      \\\\"); 
                System.out.println("//=============================\\\\\n");
                
                System.out.print("Digite o valor da consulta: ");
                Value = ler.nextLine();
                if(Value.matches(".*[a-zA-Z]*.")){
                   SQLSelectDados += "'" + Value + "'"; 
                }else{
                    SQLSelectDados += Value;
                }
                break;
            case "2":
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Consulta      \\\\"); 
                System.out.println("//=============================\\\\\n");
                
                System.out.printf("Colunas de %s que podem ser consultadas por 'contem': %s, %s, %s, %s, %s.\n", TableName, IParams[0],IParams[1].replace(" ", "_"),IParams[2].replace(" ", "_"),IParams[6].replace(" ", "_"), IParams[10]);
                
                System.out.print("Escolha: ");
                Value = ler.next();
                ler.nextLine();
                SQLSelectDados += Value + " ILIKE '%";
                
                System.out.print("\033[H\033[2J");
                System.out.println("//=============================\\\\");
                System.out.println("//          QuimioBank         \\\\");
                System.out.println("//        Hub de Consulta      \\\\"); 
                System.out.println("//=============================\\\\\n");
                
                System.out.printf("%s deve conter: ", Value);
                SQLSelectDados += ler.nextLine() + "%'";
                break;
            default:
                return;
        }
        
        SELECTOR();
    }
    
    public static void SLT_ALL() throws InterruptedException {
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Consulta      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.println("QB: Consultando dados...");
        Thread.sleep(3 + new Random().nextInt(7) * 100);
        
        System.out.println("QB: Dados consultados! Exibindo...");
        Thread.sleep(3 + new Random().nextInt(7) * 150);
        
        SQLSelectDados = "SELECT * FROM " + TableName;
        SELECTOR();
    }
    
    public static void SELECTOR() throws InterruptedException {
 
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Consulta      \\\\"); 
        System.out.println("//=============================\\\\\n");
            
        try(Connection conn = DriverManager.getConnection(Database, UserL, Pass)) {
            st = conn.createStatement();
            result = st.executeQuery(SQLSelectDados);
            while(result.next()){
                System.out.println("//=============================\\\\");
                System.out.printf("ID: %s\n", result.getString(1));
                System.out.printf("%s: %s\n", IParams[0], result.getString(2));
                System.out.printf("%s: %s\n", IParams[1], result.getString(3));
                System.out.printf("%s: %s\n", IParams[2], result.getString(4));
                System.out.printf("%s: %s\n", IParams[3], result.getString(5));
                System.out.printf("%s: %s\n", IParams[4], result.getString(6));
                System.out.printf("%s: %s\n", IParams[5], result.getString(7));
                System.out.printf("%s: %s\n", IParams[6], result.getString(8));
                System.out.printf("%s: %s\n", IParams[7], result.getString(9));
                System.out.printf("%s: %s\n", IParams[8], result.getString(10));
                System.out.printf("%s: %s\n", IParams[9], result.getString(11));
                System.out.printf("%s: %s\n", IParams[10], result.getString(12));
                System.out.println("//=============================\\\\\n");
                
                Thread.sleep(5 + new Random().nextInt(15) * 100);
            }
            System.out.print("Digite qualquer coisa para sair: ");
            ler.next();
        }catch(SQLException e) {
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
    }
    
    public static void SLT_OPT(String DB, String User, String Password, String Table, String PFunc, String[] ExParams, String[] ExTypes) throws InterruptedException { //Insert_Options, :clownface: a quantidade de parâmetros KKKKK
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
            System.out.println("//        Hub de Consulta      \\\\"); 
            System.out.println("//=============================\\\\\n");
            
            System.out.printf("1 - Consultar todos os dados em %s.\n", TableName);
            System.out.printf("2 - Consultar substancias em %s.\n", TableName);
            System.out.println("a - Executar um codigo Postgre de consulta. (1.0.2: Nao implementado)");
            System.out.println("Sair - Digite qualquer outra coisa.");
            
            System.out.print("Escolha: ");
            
            switch(ler.next().toLowerCase()){
                case "1":
                    SLT_ALL();
                    break;
                case "2":
                    SELECT();
                    break;
                case "a":
                    break;
                default:
                    break loop;
            }
        }
    }
}
