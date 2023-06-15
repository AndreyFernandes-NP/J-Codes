package quimiobank;
import quimiobank.*; //Importando classes externas que serão usadas...

/**
 *
 * @author Andrey
 * @author Pablo
 * @version 1.0.1
 * Idealization: Pablo S.; Andrey F.
 */

public class QuimioBank {
    public static String driver = "jdbc:postgresql://127.0.0.1:5432/QuimioBank"; //Conexão ao Database.
    public static String driverJDBC = "org.postgresql.Driver"; //Driver à ser utilizado.

    public static void main(String[] args) throws InterruptedException{ //Como haverá diversos "Thread.sleep", é necessário declarar um Throw.
        LC_To_DB startup = new LC_To_DB();
        if(startup.LC(driverJDBC, driver)){}else{
            System.out.println("QB-EXCEPTION: FALHA, SUSPENDENDO PROGRAMA...");
        }
    }
    
}
