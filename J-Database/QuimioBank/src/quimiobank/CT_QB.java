package quimiobank;

/**
 *
 * @author Andrey
 */

// Imports básicos \\

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Random; //Hihi eu me divirto.

public class CT_QB { // CREATE TABLE in QUIMIOBANK params, o nome da classe.
    public static String Table;
    public static Scanner ler = new Scanner(System.in);
    
    // Parâmetros Padrões da QB \\
    public static String Param1 = "Substância";
    public static String P1Type = "VARCHAR(99) NOT NULL";
    public static String Param2 = "Nome Popular";
    public static String P2Type = "VARCHAR(99)";
    public static String Param3 = "Forma Molecular";
    public static String P3Type = "VARCHAR(99) NOT NULL";
    public static String Param4 = "Massa Molar";
    public static String P4Type = "float4 NOT NULL";
    public static String Param5 = "Densidade";
    public static String P5Type = "float4";
    public static String Param6 = "Ponto de Fusão";
    public static String P6Type = "float4";
    public static String Param7 = "Ponto de Ebulição";
    public static String P7Type = "float4";
    public static String Param8 = "Classificação UE";
    public static String P8Type = "VARCHAR(99) NOT NULL";
    public static String Param9 = "pH";
    public static String P9Type = "float4 NOT NULL";
    public static String Param10 = "Quantidade em Litro";
    public static String P10Type = "float4";
    public static String Param11 = "Localização";
    public static String P11Type = "VARCHAR(200)";
    
    //Variáveis e Parâmetros para serem retornados se necessário\\
    public static String ParamsF = "(" + Param1 + ", " + Param2.replace(" ", "_") + ", " + Param3.replace(" ", "_")     //Essa classe tá tão cheia
            + ", " + Param4.replace(" ", "_") + ", " + Param5 + ", " + Param6.replace(" ", "_") + ", "                  //de código criminoso, que
            + Param7.replace(" ", "_") + ", " + Param8.replace(" ", "_") + ", "                                         //pqp...
            + Param9 + ", " + Param10.replace(" ", "_") + ", " + Param11 + ")";
    public static String[] PTypes = {P1Type, P2Type, P3Type, P4Type, P5Type, P6Type, P7Type, P8Type, P9Type, P10Type, P11Type};                       //Apenas esses dois aqui se salvam...
    public static String[] Params = {Param1, Param2, Param3, Param4, Param5, Param6, Param7, Param8, Param9, Param10, Param11};                       //mas por MUITO pouco.
    
    public static String getParamsCode(){
        return ParamsF;
    }
    
    public static String[] getParams(){
        return Params;
    }
    
    public static String[] getTypes(){
        return PTypes;
    }
    
    public static boolean Table(String BD, String User, String Password) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Seletor de Padroes    \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.println("QB: Aplicando variaveis da QuimioBank...");
        Thread.sleep(3 + new Random().nextInt(7) * 100);
        
        String SQLcriarTabela = "CREATE TABLE " + Table + " (id SERIAL PRIMARY KEY, " + Param1 + " " + P1Type + ", " + Param2.replace(" ", "_") + " " + P2Type + ", " + Param3.replace(" ", "_") + " "          //ESSE É O MAIO CRIME QUE
                + P3Type + ", " + Param4.replace(" ", "_") + " " + P4Type + ", " + Param5 + " " + P5Type + ", " + Param6.replace(" ", "_") + " " + P6Type + ", " + Param7.replace(" ", "_")      //JÁ COMETI EM TODA A MINHA VIDA
                + " " + P7Type + ", " + Param8.replace(" ", "_") + " " + P8Type + ", " + Param9 + " " + P9Type + ", " + Param10.replace(" ", "_") + " " + P10Type + ", " + Param11 + " " + P11Type + ")";       //DE PROGRAMADOR. QUANDO FOR FAZER UM PROGRAMA
        Statement st = null;                                                                                                                                                                                                                  //DE BD 2, APLICAR ISSO DE FORMA EFICIENTE!!! 
                                                                                                                                                                                                                                              //Sinceramente, nem tente entender ou explicar o que fiz aqui.
        try(Connection conn = DriverManager.getConnection(BD, User, Password)){
            //System.out.printf("QB-DEBUGG, SQL COMMAND: %s\n", SQLcriarTabela);
            System.out.printf("QB: Criando tabela %s...\n", Table);
            Thread.sleep(1 + new Random().nextInt(2) * 500);
            st = conn.createStatement();                // Cria a declaração,
            st.executeUpdate(SQLcriarTabela);     // e em seguida executa ela.
            System.out.printf("QB: Tabela %s criada com sucesso!\n", Table);
            Thread.sleep(1500);
            st.close();
            conn.close();
            return true;
        }catch(SQLException e){
            System.err.format("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
    
    public static void TO_DB(String BD, String User, String Password) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Criacao de Tabelas    \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o nome da tabela a ser criada: ");
        Table = ler.next();
        
        loop:
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//       Seletor de Padroes    \\\\");
            System.out.println("//=============================\\\\\n");
        
            System.out.println("1 - Utilizar padrao da QuimioBank.");
            System.out.println("2 - Utilizar padrao proprio. (Avancado)/(1.0.2: Nao implementado)");
            
            System.out.print("Escolha: ");
            
            switch(ler.nextInt()){
                case 1:
                    if(Table(BD, User, Password)){return;}else{break loop;}
                case 2:
                    System.out.println("\nQB-ERROR: Nao implementado.");
                    Thread.sleep(1500);
                    break;
                default:
                    System.out.println("\nQB-ERROR: Valor invalido, repita.");
                    Thread.sleep(1500);
                    break;
            }  
        }
    }
}
