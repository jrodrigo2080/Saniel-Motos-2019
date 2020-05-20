package Dao;

import Conexao.*;
import Model.ModelVenda;
import Model.ModelCadastro;
import com.itextpdf.text.DocumentException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
//import telas.Venda;

public class DAOVenda {

    ModelVenda vb = new ModelVenda();

    public void salvar(ModelVenda mb) throws DocumentException {
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();
        String sql = "INSERT INTO venda(valor,data,nome,cpf,telefone,pagamento,placa) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement stmt;
        try {

            stmt = conexao.prepareStatement(sql);
            if (mb.getId() <= 6) {
                stmt.setFloat(1, mb.getValorVenda());
                stmt.setString(2, mb.getDataVenda());
                stmt.setString(3, mb.getNome());
                stmt.setString(4, mb.getCpf());
                stmt.setString(5, mb.getTelefone());
                stmt.setString(6, mb.getPagamento());
                stmt.setString(6, mb.getPagamento());
                stmt.setString(7, mb.getPlaca());
                stmt.executeUpdate();
                //JOptionPane.showMessageDialog(null, "salvo");

                ConexaoSqlite.desconectar();
            } else {

                //JOptionPane.showMessageDialog(null, "Você alcançou o limite de venda");
            }
        } catch (SQLException ex) {
            // JOptionPane.showMessageDialog(null, "Moto Já Foi Vendida");

        }

    }

    public void Alterar(ModelVenda modelVenda) {

        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "UPDATE vendaSalvas SET "
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
            stmt.setString(1, modelVenda.getMarca());
            stmt.setString(2, modelVenda.getModelo());
            stmt.setString(3, modelVenda.getCelindrada());
            stmt.setInt(4, modelVenda.getAno());
            stmt.setString(5, modelVenda.getCor());
            stmt.setString(6, modelVenda.getNome());
            stmt.setString(7, modelVenda.getCpf());
            stmt.setString(8, modelVenda.getDataCompra());
            stmt.setString(9, modelVenda.getTelefone());
            stmt.setString(10, modelVenda.getObservacao());
            stmt.setString(11, modelVenda.getPlaca());

            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            System.out.println("ok");
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("erro ao atualizar " + ex);
        }

    }

    public List<ModelVenda> listar() {
        String sql = "SELECT * FROM venda";
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();
        List<ModelVenda> vendaList = new ArrayList<>();

        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelVenda cb = new ModelVenda();
                ModelCadastro mb = new ModelCadastro();

                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setDataVenda(rs.getString("data"));
                cb.setValorCompra(Float.parseFloat(rs.getString("valor")));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setPagamento(rs.getString("pagamento"));
                cb.setPlaca(rs.getString("placa"));

                vendaList.add(cb);
                //System.out.println("ta dando certo");
            }
            ConexaoSqlite.desconectar();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro na lista entre em contato com o suporte!");
        }
        return vendaList;
    }

    public List<ModelVenda> pesquisar(String placa) {
        String sql = "SELECT * FROM venda WHERE placa LIKE ?";
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();
        List<ModelVenda> vendaList = new ArrayList<>();

        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelVenda cb = new ModelVenda();
                ModelCadastro mb = new ModelCadastro();

                cb.setId(Integer.parseInt(rs.getString("id")));
                cb.setDataVenda(rs.getString("data"));
                cb.setValorVenda(Float.parseFloat(rs.getString("valor")));
                cb.setNome(rs.getString("nome"));
                cb.setCpf(rs.getString("cpf"));
                cb.setTelefone(rs.getString("telefone"));
                cb.setPagamento(rs.getString("pagamento"));
                cb.setPlaca(rs.getString("placa"));

                vendaList.add(cb);
                //System.out.println("ta dando certo");

            }
            ConexaoSqlite.desconectar();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro na lista entre em contato com o suporte!");
        }
        return vendaList;
    }

     public void deletar(ModelVenda moto) {
        java.sql.Connection con = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM vendaSalvas WHERE placa=?");
            stmt.setString(1, moto.getPlaca());
            stmt.executeUpdate();
            ConexaoSqlite.desconectar();
            JOptionPane.showMessageDialog(null, "EXCLUIDO COM SUCESSO!");

        } catch (SQLException ex) {
            System.err.println("erro: " + ex);
        }

    }

    public List<ModelCadastro> pesquisarPlaca(String placa) {
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();
        PreparedStatement stmt;
        String sql = "SELECT * FROM cadastro WHERE placa LIKE ?";

        List<ModelCadastro> motoList = new ArrayList<>();

        //implemente o select com PreparedStatement
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                ModelCadastro moto = new ModelCadastro();

                moto.setId(Integer.parseInt(rs.getString("id")));
                moto.setPlaca(rs.getString("placa"));
                moto.setCelindrada(rs.getString("celindrada"));
                moto.setMarca(rs.getString("marca"));
                moto.setModelo(rs.getString("modelo"));
                moto.setValorCompra(Float.parseFloat(rs.getString("valor")));
                moto.setAno(rs.getInt("ano"));
                moto.setCor(rs.getString("cor"));

                motoList.add(moto);
                //System.out.println("ta dando certo");
            }
            rs.close();
            ConexaoSqlite.desconectar();

        } catch (SQLException ex) {
            System.err.println("erro " + ex);
        }
        return motoList;
    }

    public void salvarVendas(ModelCadastro mb) {
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();

        String sql = "INSERT INTO vendaSalvas(marca,modelo,celindrada,ano,cor,valor,placa,dataCompra,nome,cpf,telefone,valorCompra,lucro,observacao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        //implemente o insert com PreparedStatement
        PreparedStatement stmt;
        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, mb.getMarca());
            stmt.setString(2, mb.getModelo());
            stmt.setString(3, mb.getCelindrada());
            stmt.setInt(4, mb.getAno());
            stmt.setString(5, mb.getCor());
            stmt.setFloat(6, mb.getValorVenda());
            stmt.setString(7, mb.getPlaca());
            stmt.setString(8, mb.getDataCompra());
            stmt.setString(9, mb.getNome());
            stmt.setString(10, mb.getCpf());
            stmt.setString(11, mb.getTelefone());
            stmt.setFloat(12, mb.getValorCompra());
            stmt.setFloat(13, mb.getLucro());
            stmt.setString(14, mb.getObservacao());
            stmt.executeUpdate();
            //JOptionPane.showMessageDialog(null, "salvo");
            ConexaoSqlite.desconectar();
            stmt.close();
        } catch (SQLException ex) {
            System.out.println("erro ao salvar: " + ex);
        }

    }

    public List<ModelVenda> pesquisarVenda(String placa) {
        String sql = "SELECT * FROM vendaSalvas WHERE placa LIKE ?";
        java.sql.Connection conexao = ConexaoSqlite.CriarConexao();
        List<ModelVenda> vendaList = new ArrayList<>();

        //implemente o select com PreparedStatement
        PreparedStatement stmt;

        try {
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + placa + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ModelVenda modelVenda = new ModelVenda();
                ModelCadastro modelCadastro = new ModelCadastro();

                modelVenda.setId(Integer.parseInt(rs.getString("id")));
                modelVenda.setMarca(rs.getString("marca"));
                modelVenda.setModelo(rs.getString("modelo"));
                modelVenda.setCelindrada(rs.getString("celindrada"));
                modelVenda.setAno(Integer.parseInt(rs.getString("ano")));
                modelVenda.setCor(rs.getString("cor"));
                modelVenda.setValorVenda(Float.parseFloat(rs.getString("valor")));
                modelVenda.setPlaca(rs.getString("placa"));
                modelVenda.setNome(rs.getString("nome"));
                modelVenda.setCpf(rs.getString("cpf"));
                modelVenda.setDataCompra(rs.getString("dataCompra"));
                modelVenda.setTelefone(rs.getString("telefone"));
                modelVenda.setObservacao(rs.getString("observacao"));
                modelVenda.setValorCompra(Float.parseFloat(rs.getString("valorCompra")));

                vendaList.add(modelVenda);

                //System.out.println("ta dando certo");
            }
            ConexaoSqlite.desconectar();
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "erro na lista entre em contato com o suporte!");
        }
        return vendaList;
    }

}
