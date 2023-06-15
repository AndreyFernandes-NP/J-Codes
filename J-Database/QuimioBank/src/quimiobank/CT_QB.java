package quimiobank;

/**
 *
 * @author Andrey
 */

// Imports básicos \\

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement; //Enviar um tipo de declaração ao banco de dados.
import java.util.Scanner;
import java.util.Random; //Hihi eu me divirto.

public class CT_QB {
    public static String Table;
    public static Scanner ler = new Scanner(System.in);
    
    // Parâmetros Padrões da QB \\
    public static String Param1 = "Substância";
    public static String P1Type = "VARCHAR(99)";
    public static String Param2 = "Nome_Popular";
    public static String P2Type = "VARCHAR(99)";
    public static String Param3 = "Forma_Molecular";
    public static String P3Type = "VARCHAR(99)";
    public static String Param4 = "Massa_Molar";
    public static String P4Type = "float4";
    public static String Param5 = "Densidade";
    public static String P5Type = "float4";
    public static String Param6 = "Ponto_de_Fusão";
    public static String P6Type = "float4";
    public static String Param7 = "Ponto_de_Ebulição";
    public static String P7Type = "float4";
    public static String Param8 = "Classificação_UE";
    public static String P8Type = "VARCHAR(99)";
    public static String Param9 = "pH";
    public static String P9Type = "float4";
    public static String Param10 = "Quantidade";
    public static String P10Type = "int";
    public static String Param11 = "Localização";
    public static String P11Type = "VARCHAR(200)";
    
    public static boolean Table(String BD, String User, String Password) throws InterruptedException{
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Seletor de Padroes    \\\\");
        System.out.println("//=============================\\\\\n");
        
        System.out.println("QB: Aplicando variaveis da QuimioBank...");
        Thread.sleep(3 + new Random().nextInt(7) * 100);
        
        String SQLcriarTabela = "CREATE TABLE " + Table + " (" + Param1 + " " + P1Type + ", " + Param2 + " " + P2Type + ", " + Param3 + " " + P3Type + ", " +   //PUTA QUE PARIU, ESSE É O MAIOR 
                Param4 + " " + P4Type + ", " + Param5 + " " + P5Type + ", " + Param6 + " " + P6Type + ", " + Param7 + " " + P7Type + ", " +                     //CRIME QUE EU JÁ FIZ NA MINHA VIDA
                Param8 + " " + P8Type + ", " + Param9 + " " + P9Type + ", " + Param10 + " " + P10Type + ", " + Param11 + " " + P11Type + ")";                   //DE PROGRAMADOR. QUANDO FOR FAZER UM
        Statement st = null;                                                                                                                                    //PROGRAMA DE BD 2, APLICAR ISSO DE FORMA EFICIENTE!!!
        
        try(Connection conn = DriverManager.getConnection(BD, User, Password)){
            //System.out.printf("QB-DEBUGG, SQL COMMAND: %s\n", SQLcriarTabela);
            System.out.printf("QB: Criando tabela %s...\n", Table);
            Thread.sleep(1 + new Random().nextInt(2) * 500);
            st = conn.createStatement();
            st.executeUpdate(SQLcriarTabela);
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
                    if(Table(BD, User, Password)){return;}
                    break;
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
