package bdprojeto;

/**
 *
 * @author Andrey
 */

public class CarregarDrive {   
    public static boolean Drive(String driverJDBC){
        try{
            System.out.println("Carregando Driver JDBC...");
            Class.forName(driverJDBC);
            System.out.println("Driver carregado.");
            return true;
        }catch (Exception e){
            System.out.printf("Falha ao carregar: %s", e);
        }
        return false;
    }
}
