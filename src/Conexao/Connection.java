/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author root
 */
public class Connection {
    
    
    private static final String URL_JDBC = "jdbc:h2:";
    private static final String PATH_BD_FILE = "C://home//rodrigo//saniel";
    private static final String EXTENSAO_BD = ".mv.db";

    private Connection() {
        //escondendo o contrutor da classe já que não 
        //desejamos que a mesma possa ser instanciada.
    }

    public static java.sql.Connection criarConexao() {
        try {
            return DriverManager.getConnection(URL_JDBC.concat(PATH_BD_FILE));
        } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"erro "+ e);
        }
        return null;
    }

    private static void criarBD() {
        try (java.sql.Connection conexao = criarConexao()) {
           //criando o banco
            String sql = "CREATE TABLE cadastro (id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "marca VARCHAR(255) NOT NULL,"
                    + "modelo VARCHAR(255) NOT NULL,"
                    + "celindrada VARCHAR(255) NOT NULL,"
                    + "ano INT(10) NOT NULL,"
                    + "cor VARCHAR(255) NOT NULL,"
                    + "valor VARCHAR(255) NOT NULL,"
                    + "placa VARCHAR(15) NOT NULL,"
                    + "nome VARCHAR(255) NOT NULL,"
                    + "cpf VARCHAR(15) NOT NULL,"
                    + "telefone VARCHAR(15) NOT NULL,"
                    + "dataCompra VARCHAR(255) NOT NULL);";
            String sql1 = "CREATE TABLE venda("
                    + "id BIGINT AUTO_INCREMENT PRIMARY KEY,"
                    + "nome VARCHAR(MAX) NOT NULL,"
                    + "cpf VARCHAR(MAX) NOT NULL,"
                    + "telefone VARCHAR(MAX) NOT NULL,"
                    + "data VARCHAR(MAX) NOT NULL,"
                    + "pagamento VARCHAR(MAX) NOT NULL,"
                    + "placa VARCHAR(8) NOT NULL,"
                    + "valor VARCHAR(MAX) NOT NULL);";

            conexao.prepareStatement(sql).executeUpdate();
            conexao.prepareStatement(sql1).executeUpdate();
        } catch (Exception e) {
            System.err.println("Falha ao criar banco de dados: " + e.getMessage());
            JOptionPane.showMessageDialog(null,"erro " + e);
        }
    }

    static {
        File bdFile = new File(PATH_BD_FILE.concat(EXTENSAO_BD));
        if (!bdFile.exists()) {
            criarBD();
            System.out.println("Banco de dados criado com sucesso.");
            JOptionPane.showMessageDialog(null, "banco criando");
        }
    }

    
}
