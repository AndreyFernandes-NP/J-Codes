package quimiobank;
import quimiobank.*; //Importando classes externas que serão usadas...

/**
 *
 * @author Andrey
 * @author Pablo
 * @version 1.0.2
 * Idealization: Pablo S.; Andrey F.
 */

import java.util.Scanner;
import java.util.Random; //Mesma little troll que usei nas outras classes.

public class QuimioBank {
    public static Scanner ler = new Scanner(System.in); //Scanner para os Switches.
    
    private static final String DB = "jdbc:postgresql://127.0.0.1:5432/QuimioBank"; //Conexão ao Database.
    private static final String JDBC = "org.postgresql.Driver"; //Driver à ser utilizado.
    
    private static String User, Password;
    public static String TempTableName;
    
    // O resto serão todas as classes que usarei \\
    public static LI_DB logon = new LI_DB();            //Classe para realizar o Login do BD.
    public static LC_To_DB startup = new LC_To_DB();    //Classe para realizar o load do driver e a conexão ao BD.
    public static CT_QB CT = new CT_QB();               //Classe para criação de Tabelas no BD.
    public static SFT_DB SEARCH = new SFT_DB();         //Classe para procurar tabelas existentes no BD.
    public static QB_HUB QuimioBank = new QB_HUB();     //Classe para acessar o HUB de tabelas no BD.
    
    public static String getDB(){ //Como o DB é um valor private, utilizamos uma função para obter seu valor.
        return DB;
    }
    
    public static String getJDBC(){ //O mesmo com o JDBC.
        return JDBC;
    }
    
    public static String getUser(){
        return User;
    }
    
    public static String getPass(){
        return Password;
    }
    
    public static void setUser(String LogUser){
        User = LogUser;
    }
    
    public static void setPass(String Pass){
        Password = Pass;
    }

    public static void main(String[] args) throws InterruptedException{ //Como haverá diversos "Thread.sleep", é necessário declarar um Throw.  
        setUser(logon.USER());                                            //Se o valor retornado for 'True', prossiga, caso contrário,
        setPass(logon.PASS(getUser()));                                    //o programa se encerra com uma mensagem de erro.
        
        if(startup.LC(getJDBC(), getDB(), getUser(), getPass())){}else{
            System.err.format("QB-EXCEPTION: FALHA, SUSPENDENDO PROGRAMA...\n");
            Thread.sleep(1000);
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
                    CT.TO_DB(getDB(), getUser(), getPass());
                    break;
                case 2:
                    TempTableName = SEARCH.Table();
                    if(SEARCH.FindTable(getDB(), getUser(), getPass(), TempTableName)){
                        QuimioBank.HUB(TempTableName);
                     };
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
        
        Thread.sleep(2 + new Random().nextInt(3) * 1000);
    }
    
}
