/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunidevs;

/**
 * @author gyanr
 */

public class ComuniDevs {
    
    /* Método que conecta os drivers */
    static String driverJDBC = "org.postgresql.Driver";
    
    public static void conectDriver() {
        try {
            System.out.println("Carregando driver JDBC...");
            Class.forName(driverJDBC);
            System.out.println("Driver carregado!");
        } catch (Exception e) {
            System.out.printf("Falha no carregamento! %s", e);
        }
    }

    public static void main(String[] args) {
        
        
        System.out.println("\n==============================================");
        ComuniDevs.conectDriver();
    }
    
}
