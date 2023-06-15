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
    
    public static boolean LC(String driverJDBC, String driver) throws InterruptedException {     // A url da conexão assim como o Driver são enviados
        try{                                                                                     // por fora da função, para evitar repetição.
            System.out.println("//=============================\\\\");                         // O motivo de ter o InterruptedException é por conta
            System.out.println("//          QuimioBank         \\\\");                         // da função "thread.sleep" que pausa a thread.
            System.out.println("//       Para Laboratorios     \\\\"); 
            System.out.println("//=============================\\\\\n");
            System.out.println("QB: Carregando Driver JDBC...");
            Class.forName(driverJDBC);  // Carrega o Driver.
            System.out.println("QB: Driver carregado, tentando conexao...");
        }catch (Exception e){   //Reportar se houve algum erro no loading do Driver.
            System.out.printf("QB-ERROR: %s", e);
            return false;
        }
        try (Connection conn = DriverManager.getConnection(driver, "postgres", "andrey1361")){ //Realiza a conexão com o login determinado.
            if (conn != null){
                System.out.println("QB: Conectado ao Banco de Dados, carregando aplicativo...");
                Thread.sleep(new Random().nextInt(5) * 1000);   //Apenas uma pausa "meme" pro programa.
                return true;
            }
        }catch (SQLException e){    //Reportar se houve algum erro SQL durante a conexão.
            System.out.printf("QB-ERROR, SQL STATE: %s\n%s\n", e.getSQLState(), e.getMessage());
        }
        return false;
    }
}
