/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package comunidevs;

/**
 * @author gyanr
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
    
    /*Método que conecta/"cria" o BD*/
    public static void createBD() {
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosDesenvolvedores";
        try (Connection conn = DriverManager.getConnection(driver, "postgres", "2912")) {
            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
        }
    } catch (SQLException e) {
        System.err.format("SQL State: %S\n%s", e.getSQLState(), e.getMessage());
    }
    }
    
    /*Método que cria a tabela*/
    public static void createTable() {
        String SQLcriarTabela = "CREATE TABLE IF NOT EXISTS desenvolvedores ("+
                "nome VARCHAR(60), cpf int, idade int, area VARCHAR(60))";
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosDesenvolvedores";
        Statement st = null;
        try (Connection conn = DriverManager.getConnection(
                driver, "postgres", "2912")) {
            if (conn != null) {
                System.out.println("Connected to the Database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            System.out.println("Criando tabela, aguarde...");
            st = conn.createStatement();
            st.executeUpdate(SQLcriarTabela);
            System.out.println("Tabela criada com sucesso!");
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%S", e.getSQLState(), e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
        System.out.println("\n==============================================");
        ComuniDevs.conectDriver();
        ComuniDevs.createBD();
        ComuniDevs.createTable();
        
    }
    
}
