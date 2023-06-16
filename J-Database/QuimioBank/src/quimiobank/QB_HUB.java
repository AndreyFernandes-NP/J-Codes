package quimiobank;

/**
 *
 * @author Andrey
 */

import java.util.Scanner;

public class QB_HUB { //QuimioBank HUB, onde se encontra a maioria das mais importantes funções de cada tabela.
    public static Scanner ler = new Scanner(System.in);
    
    //Sub-Classes que serão usadas\\
    public static HUB_INS_DB INSERT = new HUB_INS_DB(); //Sub-classe feita para inserir dados na tabela.
    public static HUB_UPD_DB UPDATE = new HUB_UPD_DB(); //Sub-classe feita para atualizar dados na tabela.
    
    public static void HUB(String TableName, String DB, String User, String Password, String PFunc, String[] Params, String[] Types) throws InterruptedException {
        loop:
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//        Hub de Comandos      \\\\"); 
            System.out.println("//=============================\\\\\n");
        
            System.out.printf("1 - Registrar dados em %s.\n", TableName);
            System.out.printf("2 - Atualizar dados em %s.\n", TableName);
            System.out.printf("3 - Deletar dados em %s.\n", TableName);
            System.out.printf("4 - Consultar dados em %s.\n", TableName);
            System.out.printf("Sair - Digite qualquer outra numero.\n");
        
            System.out.print("Escolha: ");
            
            switch(ler.nextInt()){
            case 1:
                INSERT.INS_OPT(DB, User, Password, TableName, PFunc, Params, Types);
                break;
            case 2:
                UPDATE.UPD_OPT(DB, User, Password, TableName, PFunc, Params, Types);
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                break loop;
            }
        }
    }
}
