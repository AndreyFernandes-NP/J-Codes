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

public class HUB_INS_DB { //Hub Insert to DataBase, nome da classe.
    public static Scanner ler = new Scanner(System.in);
    public static String SQLInserirDados, Database, UserL, Pass;
    
    //Parâmetros da Tabela\\
    public static String TableName, Params, PValues, Value, Type;
    public static String[] IParams, ITypes;
    
    public static Statement st = null;
    
    public static void CST_CMM() throws InterruptedException { //Custom_Commands
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//        Hub de Insercao      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o comando de Registro a ser executado: ");
        ler.nextLine();
        SQLInserirDados = ler.nextLine();
        
        INSERT();
    }
    
    public static void INSERTION() throws InterruptedException {
        ler.nextLine();
        PValues = " VALUES (";
        for(int i = 0; i < ITypes.length; i++){
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//        Registro de Sub      \\\\"); 
            System.out.println("//=============================\\\\\n");
            
            Type = ITypes[i];
            System.out.printf("%s: ", IParams[i]);
            Value = ler.nextLine();
            
            if(!Value.contains("NULL")){}else{
                if(Type.contains("NOT NULL")){
                    if(Type.contains("VARCHAR")){
                        Value = " ";
                    }else{
                        Value = "0";
                    }
                }
            }
            
            if(Type.contains("VARCHAR") & !Value.contains("NULL")){
                Value = "'" + Value + "'";
            }
            
            if((i > 0) && (i < ITypes.length)){
                PValues += ", ";
            }
            
            PValues += Value;
            
            //System.out.printf("QB-DEBUGG, LOOP VALUE: %d\n", i);
            //System.out.printf("QB-DEBUGG, VARIABLE VALUE: %s\n", Value);
            //System.out.printf("QB-DEBUGG, COMMAND VALUE: %s\n", PValues);
            
        }
        PValues += ");";
        SQLInserirDados = "INSERT INTO " + TableName + " " + Params + PValues;
        INSERT();
    }
    
    public static void INSERT() throws InterruptedException {
        //System.out.printf("QB-DEBUGG, SQL COMMAND: %s\n", SQLInserirDados);
        try(Connection conn = DriverManager.getConnection(Database, UserL, Pass)){
            st = conn.createStatement();
            System.out.println("QB: Registrando novos dados...");
            Thread.sleep(3 + new Random().nextInt(7) * 100);
            st.executeUpdate(SQLInserirDados);
            System.out.println("QB: Dados registrados!");
            Thread.sleep(1000);
        }catch(SQLException e){
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
            Thread.sleep(1500);
        }
    }
    
    public static void INS_OPT(String DB, String User, String Password, String Table, String PFunc, String[] ExParams, String[] ExTypes) throws InterruptedException { //Insert_Options, :clownface: a quantidade de parâmetros KKKKK
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
            System.out.println("//        Hub de Insercao      \\\\"); 
            System.out.println("//=============================\\\\\n");
            
            System.out.println("1 - Registrar nova Substancia.");
            System.out.println("a - Executar um codigo Postgre de registro.");
            System.out.println("Sair - Digite qualquer outra coisa.");
            
            System.out.print("Escolha: ");
            
            switch(ler.next().toLowerCase()){ //Se funciona, funciona. Vai converter tudo para LowerCase se for letra, por conta do case "a".
                case "1":
                    INSERTION();
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
