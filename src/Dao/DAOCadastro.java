 package Dao;

import Model.ModelCadastro;
import Conexao.ConexaoSqlite;
import static Conexao.Connection.criarConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class DAOCadastro {

    public void salvar(ModelCadastro mb) {
        Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "INSERT INTO cadastro(marca,modelo,celindrada,ano,cor,valor,placa,dataCompra,nome,cpf,telefone,observacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        //implemente o insert com PreparedStatement
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, mb.getMarca());
            stmt.setString(2, mb.getModelo());
            stmt.setString(3, mb.getCelindrada());
            stmt.setInt(4, mb.getAno());
            stmt.setString(5, mb.getCor());
            stmt.setFloat(6, mb.getValorCompra());
            stmt.setString(7, mb.getPlaca());
            stmt.setString(8, mb.getDataCompra());
            stmt.setString(9, mb.getNome());
            stmt.setString(10, mb.getCpf());
            stmt.setString(11, mb.getTelefone());
            stmt.setString(12, mb.getObservacao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "salvo");
            ConexaoSqlite.desconectar();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro ao salvar: " + ex);
        }

    }

    public void Vender(ModelCadastro mb) {
        Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "INSERT INTO vendaSalvas(marca,modelo,celindrada,ano,cor,valor,placa,dataCompra,nome,cpf,telefone,observacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        //implemente o insert com PreparedStatement
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, mb.getMarca());
            stmt.setString(2, mb.getModelo());
            stmt.setString(3, mb.getCelindrada());
            stmt.setInt(4, mb.getAno());
            stmt.setString(5, mb.getCor());
            stmt.setFloat(6, mb.getValorCompra());
            stmt.setString(7, mb.getPlaca());
            stmt.setString(8, mb.getDataCompra());
            stmt.setString(9, mb.getNome());
            stmt.setString(10, mb.getCpf());
            stmt.setString(11, mb.getTelefone());
            stmt.setString(12, mb.getObservacao());
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "salvo");
            ConexaoSqlite.desconectar();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro ao salvar: " + ex);
        }

    }

    public void salvarCadastros(ModelCadastro mb) {
        Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "INSERT INTO cadastroSalvos(marca,modelo,celindrada,ano,cor,valor,placa,dataCompra,nome,cpf,telefone,observacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        //implemente o insert com PreparedStatement
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, mb.getMarca());
            stmt.setString(2, mb.getModelo());
            stmt.setString(3, mb.getCelindrada());
            stmt.setInt(4, mb.getAno());
            stmt.setString(5, mb.getCor());
            stmt.setFloat(6, mb.getValorCompra());
            stmt.setString(7, mb.getPlaca());
            stmt.setString(8, mb.getDataCompra());
            stmt.setString(9, mb.getNome());
            stmt.setString(10, mb.getCpf());
            stmt.setString(11, mb.getTelefone());
            stmt.setString(12, mb.getObservacao());
            stmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "salvo");
            ConexaoSqlite.desconectar();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro ao salvar: " + ex);
        }

    }

    public List<ModelCadastro> listar() {
        String sql = "SELECT * FROM cadastro";

        List<ModelCadastro> motoList = new ArrayList<>();
        Connection conexao = ConexaoSqlite.CriarConexao();
        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelCadastro cb = new ModelCadastro();
                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setMarca(rs.getString("marca"));
                cb.setModelo(rs.getString("modelo"));
                cb.setCelindrada(rs.getString("celindrada"));
                cb.setAno(Integer.parseInt(rs.getString("ano")));
                cb.setCor(rs.getString("cor"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valor")));
                cb.setPlaca(rs.getString("placa"));
                cb.setDataCompra(rs.getString("dataCompra"));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setObservacao(rs.getString("observacao"));
                motoList.add(cb);
                // System.out.println("ta dando certo");
            }

            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.out.println("erro na lista" + ex);
        }
        return motoList;
    }

    public List<ModelCadastro> pesquisar(String placa) {
        String sql = "SELECT * FROM cadastro WHERE placa LIKE ?";

        List<ModelCadastro> motoList = new ArrayList<>();
        Connection conexao = ConexaoSqlite.CriarConexao();
        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelCadastro cb = new ModelCadastro();
                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setMarca(rs.getString("marca"));
                cb.setModelo(rs.getString("modelo"));
                cb.setCelindrada(rs.getString("celindrada"));
                cb.setAno(Integer.parseInt(rs.getString("ano")));
                cb.setCor(rs.getString("cor"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valor")));
                cb.setPlaca(rs.getString("placa"));
                cb.setDataCompra(rs.getString("dataCompra"));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setObservacao(rs.getString("observacao"));
                motoList.add(cb);
                //System.out.println("ta dando certo");
            }

            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.out.println("erro na lista" + ex);
        }
        return motoList;
    }

    public void alterar(ModelCadastro cadastro) {
        Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "UPDATE cadastroSalvos SET "
                + "marca =?,"
                + "modelo=?,"
                + "celindrada=?,"
                + "ano=?,"
                + "cor=?,"
                + "nome=?,"
                + "cpf=?,"
                + "dataCompra=?,"
                + "telefone=?,"
                + "observacao=?"
                + "WHERE placa=?";
        PreparedStatement stmt = null;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cadastro.getMarca());
            stmt.setString(2, cadastro.getModelo());
            stmt.setString(3, cadastro.getCelindrada());
            stmt.setInt(4, cadastro.getAno());
            stmt.setString(5, cadastro.getCor());
            stmt.setString(6, cadastro.getNome());
            stmt.setString(7, cadastro.getCpf());
            stmt.setString(8, cadastro.getDataCompra());
            stmt.setString(9, cadastro.getTelefone());
            stmt.setString(10, cadastro.getObservacao());
            stmt.setString(11, cadastro.getPlaca());

            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            System.out.println("ok");
            JOptionPane.showMessageDialog(null,"Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("erro ao atualizar " + ex);
        }

    }

    public void deletar(ModelCadastro moto) {
        Connection con = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cadastro WHERE placa=?");
            stmt.setString(1, moto.getPlaca());
            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO!");

        } catch (SQLException ex) {
            System.err.println("erro: " + ex);
        }

    }

    public void deletarVenda(ModelCadastro moto) {
        Connection con = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cadastro WHERE placa=?");
            stmt.setString(1, moto.getPlaca());
            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            //JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO!");

        } catch (SQLException ex) {
            System.err.println("erro: " + ex);
        }

    }

    public List<ModelCadastro> pesquisarData(String data) {
        Connection conexao = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt;
        String sql = "SELECT * FROM vendaSalvas WHERE dataCompra LIKE ?";

        List<ModelCadastro> lista = new ArrayList<>();

        //implemente o select com PreparedStatement
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + data + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ModelCadastro cb = new ModelCadastro();

                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setMarca(rs.getString("marca"));
                cb.setModelo(rs.getString("modelo"));
                cb.setCelindrada(rs.getString("celindrada"));
                cb.setAno(Integer.parseInt(rs.getString("ano")));
                cb.setCor(rs.getString("cor"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valorCompra")));
                cb.setValorVenda(Float.parseFloat(rs.getString("valor")));
                cb.setPlaca(rs.getString("placa"));
                cb.setDataCompra(rs.getString("dataCompra"));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setObservacao(rs.getString("observacao"));
                cb.setLucro(Float.parseFloat(rs.getString("lucro")));
                lista.add(cb);
                //System.out.println("ta dando certo");
            }
            ConexaoSqlite.desconectar();
            rs.close();
        } catch (SQLException ex) {
            System.err.println("erro " + ex);
        }
        return lista;
    }

    public List<ModelCadastro> pesquisarPlaca(String placa) {
        String sql = "SELECT * FROM cadastroSalvos WHERE placa LIKE ?";

        List<ModelCadastro> motoList = new ArrayList<>();
        Connection conexao = ConexaoSqlite.CriarConexao();
        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelCadastro cb = new ModelCadastro();
                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setMarca(rs.getString("marca"));
                cb.setModelo(rs.getString("modelo"));
                cb.setCelindrada(rs.getString("celindrada"));
                cb.setAno(Integer.parseInt(rs.getString("ano")));
                cb.setCor(rs.getString("cor"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valor")));
                cb.setPlaca(rs.getString("placa"));
                cb.setDataCompra(rs.getString("dataCompra"));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setObservacao(rs.getString("observacao"));
                motoList.add(cb);
                //System.out.println("ta dando certo");
            }

            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.out.println("erro na lista" + ex);
        }
        return motoList;
    }

    public List<ModelCadastro> pesquisarVendas(String placa) {
        String sql = "SELECT * FROM vendaSalvas WHERE placa LIKE ?";

        List<ModelCadastro> motoList = new ArrayList<>();
        Connection conexao = ConexaoSqlite.CriarConexao();
        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelCadastro cb = new ModelCadastro();
                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setMarca(rs.getString("marca"));
                cb.setModelo(rs.getString("modelo"));
                cb.setCelindrada(rs.getString("celindrada"));
                cb.setAno(Integer.parseInt(rs.getString("ano")));
                cb.setCor(rs.getString("cor"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valorCompra")));
                cb.setValorVenda(Float.parseFloat(rs.getString("valor")));
                cb.setPlaca(rs.getString("placa"));
                cb.setDataCompra(rs.getString("dataCompra"));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setObservacao(rs.getString("observacao"));
                cb.setLucro(Float.parseFloat(rs.getString("lucro")));
                motoList.add(cb);
                //System.out.println("ta dando certo");
            }

            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.out.println("erro na lista" + ex);
        }
        return motoList;
    }

}
