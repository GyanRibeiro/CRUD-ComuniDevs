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
import java.sql.ResultSet;

import java.util.Scanner;

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
    
    /*Método que insere os dados na tabela*/
    public static void inserindo() {
        Scanner ler = new Scanner(System.in);
        
        System.out.println("\n==============================================");
        System.out.println("Digite seu nome:");
        String nome = ler.nextLine();
        
        System.out.println("------------------------------------------------");
        System.out.println("Digite seu CPF:");
        int cpf = ler.nextInt();
        ler.nextLine();
        
        System.out.println("------------------------------------------------");
        int idade;
                
        do {
            System.out.println("Digite sua idade:");
            idade = ler.nextInt();
            ler.nextLine();
            
            if (idade < 18) {
                System.out.println("A idade deve ser maior que 18. Digite novamente.");
                System.out.println("Deseja digitar sua idade novamente? 1 - Sim, 2 - Não");
                int opcaoIdade = ler.nextInt();
                ler.nextLine();
        
                if (opcaoIdade == 2) {
                    return; // Encerra o programa
                }
            }
            
        } while(idade < 18);
        
        System.out.println("------------------------------------------------");
        System.out.println("Selecione sua area de atuacao:\n");
        System.out.println("1. Front-end");
        System.out.println("2. Back-end");
        System.out.println("3. Full stack");
        System.out.println("4. Mobile");
        System.out.println("5. Estudante");
        
        int opcaoArea = ler.nextInt();
        String area = "";
        
        if (opcaoArea == 1) {
            area = "Front-end";
        }
        else if (opcaoArea == 2) {
            area = "Back-end";
        }
        else if (opcaoArea == 3) {
            area = "Full stack";
        }
        else if (opcaoArea == 4) {
            area = "Mobile";
        }
        else if (opcaoArea == 5) {
            area = "Estudante";
        } else {
            System.out.println("Opção inválida.");
            return;
        }
        
        
        System.out.println("\n==============================================");
        
        String SQLinserirDados = "INSERT INTO desenvolvedores (cpf, nome, idade, area) VALUES (" + cpf + ", '" + nome + "', '" + idade + "', '" + area + "')";

        
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosDesenvolvedores";
        Statement st = null;
         try (Connection conn = DriverManager.getConnection(
                driver, "postgres", "2912")) {
            System.out.println("Inserindo dados na tabela...");
            st = conn.createStatement();
            st.executeUpdate(SQLinserirDados);
            System.out.println("Dados inseridos");
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%S", e.getSQLState(), e.getMessage());
        }
    }
    
    public static void consultDados() {
        Scanner ler = new Scanner(System.in);

        System.out.println("\n==============================================");
        System.out.println("Digite (1) para consultar um dado especifico");
        System.out.println("Digite (2) para consultar todos os dados");

        int opcao = ler.nextInt();
        String SQLconsultarDados = "";
    
        if (opcao == 1) { /* Diferencial pedido*/
            System.out.println("------------------------------------------------");
            System.out.println("Digite o tipo de consulta: ");
            System.out.println("------------------------------------------------");

            System.out.println("(1) Consulta por CPF");
            System.out.println("(2) Consulta por area");

            int opcaoConsult = ler.nextInt();

            if (opcaoConsult == 1) {
                System.out.println("Digite o CPF para buscar os dados:");
                int cpf = ler.nextInt();
                SQLconsultarDados = "SELECT * FROM desenvolvedores WHERE cpf = " + cpf;
            }
            else if (opcaoConsult == 2) {
                System.out.println("Selecione a area que deseja consultar: ");
                System.out.println("1. Front-end");
                System.out.println("2. Back-end");
                System.out.println("3. Full stack");
                System.out.println("4. Mobile");
                System.out.println("5. Estudante");

                int opcaoAreaConsult = ler.nextInt();

                String areaAtuacao = "";

                if (opcaoAreaConsult == 1) {
                    areaAtuacao = "Front-end";
                }
                else if (opcaoAreaConsult == 2) {
                    areaAtuacao = "Back-end";
                }
                else if (opcaoAreaConsult == 3) {
                    areaAtuacao = "Full stack";
                }
                else if (opcaoAreaConsult == 4) {
                    areaAtuacao = "Mobile";
                }
                else if (opcaoAreaConsult == 5) {
                    areaAtuacao = "Estudante";
                } else {
                    System.out.println("Opcao inválida!!");
                    return;
                }
                SQLconsultarDados = "SELECT * FROM desenvolvedores WHERE area = '" + areaAtuacao + "'";
            } else {
                System.out.println("Opcao Invalida");
                return;
            }  
        } else if (opcao == 2) {
            SQLconsultarDados = "SELECT * FROM desenvolvedores";
        } else {
            System.out.println("Opcao invalida");
            return;
        }
    
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosDesenvolvedores";
    
        try (Connection conn = DriverManager.getConnection(driver, "postgres", "2912")) {

            System.out.println("Consultando dados na tabela...");
            Statement st = conn.createStatement();
            ResultSet result = st.executeQuery(SQLconsultarDados);

            while (result.next()) {
                System.out.println("\n==============================================");
                System.out.println("Nome: " + result.getString("nome"));
                System.out.println("CPF: " + result.getInt("cpf"));
                System.out.println("Idade: " + result.getString("idade"));
                System.out.println("Area de Atuacao: " + result.getString("area"));
            }

            result.close();
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%S", e.getSQLState(), e.getMessage());
        }
    }
    
    public static void atualizandoBD() {
        Scanner ler = new Scanner(System.in);

        System.out.println("\n==============================================");
        System.out.println("Digite seu CPF:");
        int cpf = ler.nextInt();

        System.out.println("\n==============================================");
        System.out.println("Quais campos você deseja alterar?");
        System.out.println("1. Usuário");
        System.out.println("2. Área de Atuação");

        int opcao = ler.nextInt();

        System.out.println("------------------------------------------------");
    
        ler.nextLine();

        String campo;
        String novoValor;

        switch (opcao) {
            case 1:
                campo = "nome";
                System.out.println("Digite o novo nome:");
                novoValor = ler.nextLine();
                break;
            case 2:
                campo = "area";
                System.out.println("Digite a nova área de atuação:");
                novoValor = ler.nextLine();
                break;
            default:
                System.out.println("Opção inválida.");
                return;
        }
    
        System.out.println("\n==============================================");

        String SQLatualizarDados = "UPDATE desenvolvedores SET " + campo + " = '" + novoValor + "' WHERE cpf = " + cpf;

        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosDesenvolvedores";
        Statement st = null;
        try (Connection conn = DriverManager.getConnection(driver, "postgres", "2912")) {
            System.out.println("Atualizando dados na tabela...");
            st = conn.createStatement();
            st.executeUpdate(SQLatualizarDados);
            System.out.println("Dados atualizados!");
            st.close();
            conn.close();
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%S", e.getSQLState(), e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n==============================================");
        ComuniDevs.conectDriver();
        ComuniDevs.createBD();
        ComuniDevs.createTable();
        
        System.out.println("\n==============================================");
        System.out.println("Escolha uma opçao:\n");
        System.out.println("1. Inserir Dados");
        System.out.println("2. Consultar dados");
        System.out.println("3. Atualizar Dados");
        System.out.println("4. Excluir dados");
        
        int opcao = scanner.nextInt();
          
        if (opcao == 1) {
            ComuniDevs.inserindo();
        }
        else if (opcao == 2) {
            ComuniDevs.consultDados();
        }
        else if (opcao == 3) {
            ComuniDevs.atualizandoBD();
        }
    }
    
}
