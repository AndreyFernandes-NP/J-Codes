package quimiobank;

/**
 *
 * @author Andrey
 */

import java.sql.Connection;     // <-----v
import java.sql.DriverManager;  // <---> Ambos imports usados para a conexão e validação da conexão com o BD.
import java.sql.SQLException;   // Import para notificar quando houver qualquer erro envolvendo o SQL.
import java.util.Random;        // Brincar com o tempo de load do programa.

public class LC_To_DB { //"Load & Connect to Database", vai executar os dois numa função só.
    
    public static boolean LC(String driverJDBC, String DB, String User, String Password) throws InterruptedException {    // A url da conexão assim como o Driver são enviados
                                                                                            // por fora da função, para evitar repetição.  
        try{                                                                                // O motivo de ter o InterruptedException é por conta
            System.out.println("//=============================\\\\");                    // da função "thread.sleep" que pausa a thread.
            System.out.println("//          QuimioBank         \\\\");
            System.out.println("//       Para Laboratorios     \\\\"); 
            System.out.println("//=============================\\\\\n");
            System.out.println("QB: Carregando Driver JDBC...");
            Thread.sleep(3 + new Random().nextInt(7) * 100); //Pausinha meme pro programa hehe.
            Class.forName(driverJDBC);  // Carrega o Driver.
            System.out.println("QB: Driver carregado, tentando conexao...");
            Thread.sleep(3 + new Random().nextInt(7) * 100); //Outra pausinha meme pro programa.
        }catch (Exception e){   //Reportar se houve algum erro no loading do Driver.
            System.out.printf("QB-ERROR: %s\n", e);
            return false;
        }
        
        try (Connection conn = DriverManager.getConnection(DB, User, Password)){ //Realiza a conexão com o login determinado.
            if (conn != null){
                System.out.println("QB: Conectado ao Banco de Dados, carregando aplicativo...");
                Thread.sleep(1 + new Random().nextInt(2) * 1000);   //Eu me divirto.
                return true;
            }
        }catch (SQLException e){    //Reportar se houve algum erro SQL durante a conexão.
            System.out.printf("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
