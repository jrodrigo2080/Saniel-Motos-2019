package Conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexaoSqlite {

    private static Connection conn = null;

    public static Connection CriarConexao() {

        try {
            Class.forName("org.sqlite.JDBC");
            try {

                conn = DriverManager.getConnection("jdbc:sqlite:\\sanielMotos\\Banco\\saniel.db");
               // conn = DriverManager.getConnection("jdbc:sqlite:C:\\sanielMotos\\Banco\\saniel.db");
                System.out.println("conectou sqlite");
                return conn;
            } catch (SQLException ex) {
                Logger.getLogger(ConexaoSqlite.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (ClassNotFoundException ex) {
            System.out.println("erro na conexao com o banco " + ex.getMessage());
            return null;

        }
        return conn;

    }

    public static void desconectar() {
        if (conn != null) {
            try {
                conn.close();
                System.out.println("fechou");
            } catch (SQLException ex) {
                System.out.println("erro ao fechar a conexao: " + ex);
            }

        }
    }

}
