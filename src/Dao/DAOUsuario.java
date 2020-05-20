package Dao;

import Model.ModelUsuario;
import Conexao.ConexaoSqlite;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOUsuario {

    public void atualizar(ModelUsuario usuario) {
        Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "UPDATE usuario SET"
                + " login=?,"
                + "senha=? "
                + "WHERE nome=? ";

        PreparedStatement stmt = null;
        try {

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getLogin());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getNome());

            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("erro " + ex);

        }

    }
     public boolean validar(String login, String senha) {

        Connection con = Conexao.ConexaoSqlite.CriarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean verifica = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login=? AND senha=? ");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                verifica = true;
            }
           ConexaoSqlite.desconectar(); 
        } catch (SQLException ex) {
            System.out.println("erro de validação: " + ex);
        }
        return verifica;
    }

    public void salvar(ModelUsuario usuario) {

        Connection conexao = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt;
        String sql = "INSERT INTO grupo (usuario,login,senha) VALUES (?,?,?)";

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getUsuario());
            stmt.setString(2, usuario.getLogin());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso");
            System.out.println("Salvo: " + usuario.getUsuario());
        } catch (SQLException ex) {
            System.err.println("erro: " + ex);

        }

    }
   
    



    public List<ModelUsuario> listar() {
        String sql = "SELECT * FROM usuario";

        List<ModelUsuario>usuariolista = new ArrayList<>();
        Connection conexao = ConexaoSqlite.CriarConexao();
        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelUsuario usuario = new ModelUsuario();
                usuario.setId(Integer.parseInt(rs.getString("id")));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
               usuariolista.add(usuario);
                
                // System.out.println("ta dando certo");
            }

            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.out.println("erro na lista" + ex);
        }
        return usuariolista;
    }

    public boolean validarSenha(String login, String senha) {

        Connection con = Conexao.ConexaoSqlite.CriarConexao();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean verifica = false;

        try {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE login=? AND senha=? ");
            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                verifica = true;
            }
           ConexaoSqlite.desconectar(); 
        } catch (SQLException ex) {
            System.out.println("erro de validação: " + ex);
        }
        return verifica;
    }
    

}
