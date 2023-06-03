package bdprojeto;
import bdprojeto.*;

/**
 *
 * @author Andrey
 */

import java.util.Scanner;
import java.io.IOException;

public class BDProjeto {
    public static Scanner ler = new Scanner(System.in);
    
    public static String driver = "jdbc:postgresql://127.0.0.1:5432/DadosGerais";
    public static String driverJDBC = "org.postgresql.Driver";
    public static String TableName, Param1, Param2, P1Type, P2Type;
    
    public static boolean Startup(){
        CarregarDrive load = new CarregarDrive();
        JAVA_Database connect = new JAVA_Database();
        
        if(load.Drive(driverJDBC)){
            return connect.Database(driver);
        }else{
            return false;
        }
    }

    public static void main(String args[]) throws InterruptedException, IOException {     
        if(Startup()){
            CriarTabela create = new CriarTabela();
            Thread.sleep(2500);
            
            System.out.print("\033[H\033[2J");  
            System.out.println("// Config de Tabelas \\\\\n");
            System.out.print("Digite o nome da Tabela: ");
            TableName = ler.next();
            System.out.print("Digite o nome do 1st Parametro: ");
            Param1 = ler.next();
            System.out.print("Digite o tipo do 1st Parametro: ");
            P1Type = ler.next();
            System.out.print("Digite o nome do 2nd Parametro: ");
            Param2 = ler.next();
            System.out.print("Digite o tipo do 2nd Parametro: ");
            P2Type = ler.next();
            System.out.println("");
            
            if(create.Table(TableName, Param1, P1Type, Param2, P2Type, driver)){
                InserirDados insert = new InserirDados();
                Thread.sleep(1500);
                
                System.out.print("\nNumeros de registros a criar na tabela " + TableName + ": ");
                int reg = ler.nextInt();
                if(insert.Table(TableName, Param1, P1Type, Param2, P2Type, driver, reg)){
                    ConsultarDados consulta = new ConsultarDados();
                    
                    System.out.println("\nConsultando Dados...");
                    Thread.sleep(2000);
                    
                    consulta.dados(TableName, driver);
                }else{
                    System.out.println("ERR: Erro ao inserir dados na tabela...");
                    return;
                }
                
            }else{
                System.out.println("ERR: Erro ao criar a tabela, tente novamente mais tarde, ou confira os tipos digitados...");
                return;
            }
            Thread.sleep(2500);
            AtualizarDados update = new AtualizarDados();
            ExcluirDados delete = new ExcluirDados();
            
            while(true){
                System.out.print("\033[H\033[2J"); 
                System.out.println("// Alternativas Finais \\\\");
                System.out.println("Escolha o ultimo teste de UPDATE. Logo apos, para sair do programa, escolha a alternativa de exclusao.\n");
                System.out.println("1 - Teste de Update.\n2 - Exclusao dos Dados.\n");
                System.out.print("Escolha: ");
                switch(ler.nextInt()){
                    case 1:
                        update.dados(TableName, Param1, Param2, P1Type, P2Type, driver);
                        break;
                    case 2:
                        delete.dados(TableName, driver);
                        return;
                    default:
                        System.out.println("\nEscolha invalida, repita.\n");
                        break;
                }
            }
        }
    }
}
