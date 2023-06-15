package quimiobank;
import quimiobank.*; //Importando classes externas que serão usadas...

/**
 *
 * @author Andrey
 * @author Pablo
 * @version 1.0.1
 * Idealization: Pablo S.; Andrey F.
 */

import java.util.Scanner;
import java.util.Random; //Mesma little troll de outras classes.

public class QuimioBank {
    public static Scanner ler = new Scanner(System.in); //Scanner para os Switches.
    
    public static String driver = "jdbc:postgresql://127.0.0.1:5432/QuimioBank"; //Conexão ao Database.
    public static String driverJDBC = "org.postgresql.Driver"; //Driver à ser utilizado.

    public static void main(String[] args) throws InterruptedException{ //Como haverá diversos "Thread.sleep", é necessário declarar um Throw.
        LC_To_DB startup = new LC_To_DB();  //Aqui será criada a classe que realizará a conexão.
        
        if(startup.LC(driverJDBC, driver)){}else{                                   //Se o valor retornado for 'True', prossiga, caso contrário, 
            System.out.println("QB-EXCEPTION: FALHA, SUSPENDENDO PROGRAMA...");   //o programa se encerra com uma mensagem de erro.
            return;
        }
        loop:
        while(true){
            System.out.print("\033[H\033[2J");
            System.out.println("//=============================\\\\");
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//       Seletor de Opcoes     \\\\");
            System.out.println("//=============================\\\\\n");
            
            System.out.println("1 - Criar Tabela no Banco de Dados.");
            System.out.println("2 - Modificar Tabela ja existente.");
            System.out.println("Para sair, digite qualquer outro numero.\n");
            
            System.out.print("Escolha: ");
            switch(ler.nextInt()){
                case 1:
                    break;
                case 2:
                    break;
                default:
                    break loop;
            }
        }
        System.out.print("\033[H\033[2J");
        System.out.println("//=============================\\\\");
        System.out.println("//          QuimioBank         \\\\");
        System.out.println("//       Para Laboratorios     \\\\"); 
        System.out.println("//=============================\\\\\n");
        
        Thread.sleep(new Random().nextInt(5) * 1000);
        return;
    }
    
}
