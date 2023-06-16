package quimiobank;

/**
 *
 * @author Andrey
 */

import java.util.Scanner; // Para o sistema de logon.

public class LI_DB { //LogIn the DataBase, o nome da classe.
    public static Scanner ler = new Scanner(System.in);
    
    public static String USER(){
        System.out.print("\033[H\033[2J");                                               
        System.out.println("//=============================\\\\");                        
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Sistema de Logon      \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        System.out.print("Digite o nome de Usuario: ");
        return ler.next();
    }
    
    public static String PASS(String User){
        System.out.printf("Digite a senha de %s: ", User);
        return ler.next();
    }
}
