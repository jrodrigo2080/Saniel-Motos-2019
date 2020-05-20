/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import Model.ModelCadastro;
import Dao.DAOCadastro;
import Dao.DAOVenda;
import Model.ModelVenda;
import java.text.NumberFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class Consultar extends javax.swing.JDialog {
    
    private DAOVenda vd = new DAOVenda();
    private DAOCadastro cadastro = new DAOCadastro();
    NumberFormat formato = NumberFormat.getCurrencyInstance();
    float resultado = 0;
    
    public Consultar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        MotosCompradas(txtPesquisa.getText());
        pegarSelecionado();
        lbLucro.setVisible(false);
        txtLucro.setVisible(false);
        motosCompradas.isSelected();
        lbAtualizar.setVisible(false);
        txtCalLucro.setVisible(false);
        txtObservacao.setLineWrap(true);
        motosCompradas.isSelected();
        btExcluir.setVisible(false);
        
    }
    
    public Consultar() {
    }
    
    public void pegarSelecionado() {
        
        if (motosCompradas.isSelected()) {
            
            MotosCompradas(txtPesquisa.getText());
            lbPesquisa.setText("Motos Compradas");
            MotosCompradas(txtPesquisa.getText());
            // txtPesquisa.grabFocus();
            lbNome.setText("Nome do Vendedor:");
//            lbApurado.setVisible(false);
            // txtApurado.setVisible(false);
            lbAtualizar.setVisible(false);
            txtCalLucro.setVisible(false);
            
        } else if (motosVendidas.isSelected()) {
            //MotosVendidas(txtPesquisa.getText());
            lbPesquisa.setText("Motos Vendidas");
            lbNome.setText("Nome do Comprador:");
            pesquisaCalculo(txtPesquisa.getText());
            lbAtualizar.setVisible(true);
            txtCalLucro.setVisible(true);
            
        }
        
    }
    
    public void pesquisa(String placa) {
        
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Placa",
                    "Marca", "modelo",
                    "ano", "cor",
                    "Data", "Valor",
                    "Cilindrada", "Nome",
                    "Telefone", "cpf",}) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for (ModelCadastro mb : cadastro.pesquisarPlaca(placa)) {
            tabela.addRow(new Object[]{
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                formato.format(mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getCpf(),
                mb.getTelefone(),});
            
            tbMoto.setModel(tabela);
        }
        
    }
    
    public void pesquisaData(String placa) {
        
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Placa",
                    "Marca",
                    "modelo",
                    "ano",
                    "cor",
                    "Data",
                    "Valor Compra",
                    "Cilindrada",
                    "Nome",
                    "Telefone",
                    "cpf",
                    "observacao",
                    "valor Venda",
                    "lucro"
                }) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for (ModelCadastro mb : md.pesquisarVendas(placa)) {
            float lucro = mb.getValorVenda() - mb.getValorCompra();
            tabela.addRow(new Object[]{
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                formato.format(mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getTelefone(),
                mb.getCpf(),
                mb.getObservacao(),
                formato.format(mb.getValorVenda()),
                formato.format(mb.getLucro())
            
            });
            txtCalLucro.setText(formato.format(resultado += mb.getLucro()));
            
        }
        //txtTotal.setText(formato.format(resultado += valor));
        resultado = 0;
        tbMoto.setModel(tabela);
        
    }
    
    DAOCadastro md = new DAOCadastro();
    
    public void MotosCompradas(String placa) {
        
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Placa",
                    "Marca", "modelo",
                    "ano", "cor",
                    "Data", "Valor",
                    "Cilindrada", "Nome",
                    "Telefone", "cpf", "Observacao"}) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for (ModelCadastro mb : md.pesquisarPlaca(placa)) {
            tabela.addRow(new Object[]{
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                formato.format(mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getCpf(),
                mb.getTelefone(),
                mb.getObservacao()
            });
        }
        lbValor.setText("Valor de Compra:");
        lbNome.setText("nome do Vendedor:");
        tbMoto.setModel(tabela);
        btExcluir.setVisible(false);
        
    }
    
    public void MotosVendidas(String placa) {
        
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Placa",
                    "Marca",
                    "modelo",
                    "ano",
                    "cor",
                    "Data",
                    "Valor Compra",
                    "Cilindrada",
                    "Nome",
                    "Telefone",
                    "cpf",
                    "observacao",
                    "valor Venda",
                    "lucro"
                }) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for (ModelCadastro mb : md.pesquisarVendas(placa)) {
            // float lucro = mb.getValorVenda() - mb.getValorCompra();
            tabela.addRow(new Object[]{
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                (mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getTelefone(),
                mb.getCpf(),
                mb.getObservacao(),
                (mb.getValorVenda()),
                (mb.getLucro())
            
            });
        }
        lbValor.setText("Valor de Venda:");
        lbNome.setText("nome do Comprador:");
        tbMoto.setModel(tabela);
    }
    
    public void pesquisaCalculo(String data) {
        
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    // "ID",
                    "Placa",
                    "Marca",
                    "modelo",
                    "ano",
                    "cor",
                    "Data",
                    "Valor Compra",
                    "Cilindrada",
                    "Nome",
                    "Telefone",
                    "cpf",
                    "observacao",
                    "valor Venda",
                    "lucro"
                
                }) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        DAOCadastro dao = new DAOCadastro();
        for (ModelCadastro mb : dao.pesquisarVendas(data)) {
            
            tabela.addRow(new Object[]{
                // mb.getId(),
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                formato.format(mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getTelefone(),
                mb.getCpf(),
                mb.getObservacao(),
                formato.format(mb.getValorVenda()),
                formato.format(mb.getLucro()),});
            txtCalLucro.setText("Lucro: " + formato.format(resultado += mb.getLucro()));
            
        }
        //txtTotal.setText(formato.format(resultado += valor));
        resultado = 0;
        tbMoto.setModel(tabela);
        btExcluir.setVisible(true);
        
    }
    
    DAOCadastro cad = new DAOCadastro();
    
    public void pesquisaPlaca(String placa) {
        int n = 1;
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "id", "Placa",
                    "Marca", "modelo",
                    "ano", "cor",
                    "Data", "Valor",
                    "Cilindrada", "Nome",
                    "Telefone", "cpf", "Observacao"}) {
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        
        for (ModelCadastro mb : cad.pesquisarPlaca(placa)) {
            
            tabela.addRow(new Object[]{
                mb.getId(),
                mb.getPlaca(),
                mb.getMarca(), 
                mb.getModelo(),
                mb.getAno(), 
                mb.getCor(),
                mb.getDataCompra(),
                formato.format(mb.getValorCompra()),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getCpf(),
                mb.getTelefone(),
                mb.getObservacao()
            });
            
        }
        
        tbMoto.setModel(tabela);
    }
    
    public void preencheCampos() {
        
        if (tbMoto.getSelectedRow() != -1) {
            
            if (motosVendidas.isSelected()) {
                lbLucro.setVisible(true);
                txtLucro.setVisible(true);
                txtPlaca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 0).toString());
                txtMarca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 1).toString());
                txtModelo.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 2).toString());
                txtAno.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 3).toString());
                txtCor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 4).toString());
                txtData.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 5).toString());
                txtValor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 6).toString());
                txtCilindrada.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 7).toString());
                txtNome.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 8).toString());
                txtTelefone.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 9).toString());
                txtCPF.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 10).toString());
                txtObservacao.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 11).toString());
                txtVenda.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 12).toString());
                txtLucro.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 13).toString());
                
            } else if (motosCompradas.isSelected()) {
                lbLucro.setVisible(false);
                txtLucro.setVisible(false);
                txtPlaca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 0).toString());
                txtMarca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 1).toString());
                txtModelo.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 2).toString());
                txtAno.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 3).toString());
                txtCor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 4).toString());
                txtData.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 5).toString());
                txtValor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 6).toString());
                txtCilindrada.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 7).toString());
                txtNome.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 8).toString());
                txtCPF.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 9).toString());
                txtTelefone.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 10).toString());
                txtObservacao.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 11).toString());
            }
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbMoto = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        lbValor = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lbNome = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        lbPesquisa = new javax.swing.JLabel();
        motosVendidas = new javax.swing.JRadioButton();
        motosCompradas = new javax.swing.JRadioButton();
        lbNome1 = new javax.swing.JLabel();
        lbLucro = new javax.swing.JLabel();
        lbValor1 = new javax.swing.JLabel();
        txtCalLucro = new javax.swing.JLabel();
        lbAtualizar = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtVenda = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtCilindrada = new javax.swing.JTextField();
        txtLucro = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        txtCPF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();
        btExcluir = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("R5 Soft");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("x");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        tbMoto.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbMoto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbMoto.setRowHeight(20);
        tbMoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbMotoMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbMoto);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setText("Placa:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Marca:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("Modelo:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Ano:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Cor:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 102));
        jLabel15.setText("Cilindrada:");

        lbValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbValor.setForeground(new java.awt.Color(0, 51, 102));
        lbValor.setText("Valor Compra:");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 102));
        jLabel19.setText("Data:");

        txtPesquisa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPesquisa.setForeground(new java.awt.Color(0, 51, 102));
        txtPesquisa.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPesquisa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtPesquisa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtPesquisaMouseClicked(evt);
            }
        });
        txtPesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPesquisaKeyPressed(evt);
            }
        });

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477207-magnifier_78608.png"))); // NOI18N
        jLabel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        lbNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome.setForeground(new java.awt.Color(0, 51, 102));
        lbNome.setText("Nome do Vendedor:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 51, 102));
        jLabel21.setText("CPF:");

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 51, 102));
        jLabel22.setText("Telefone:");

        lbPesquisa.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbPesquisa.setForeground(new java.awt.Color(0, 51, 102));
        lbPesquisa.setText("Motos Compradas");

        motosVendidas.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(motosVendidas);
        motosVendidas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        motosVendidas.setForeground(new java.awt.Color(0, 51, 102));
        motosVendidas.setText("Motos Vendidas");

        motosCompradas.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(motosCompradas);
        motosCompradas.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        motosCompradas.setForeground(new java.awt.Color(0, 51, 102));
        motosCompradas.setText("Motos Compradas");

        lbNome1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome1.setForeground(new java.awt.Color(0, 51, 102));
        lbNome1.setText("Observação:");

        lbLucro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbLucro.setForeground(new java.awt.Color(0, 51, 102));
        lbLucro.setText("Lucro:");

        lbValor1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbValor1.setForeground(new java.awt.Color(0, 51, 102));
        lbValor1.setText("Valor Venda:");

        txtCalLucro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCalLucro.setForeground(new java.awt.Color(0, 51, 102));

        lbAtualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbAtualizar.setForeground(new java.awt.Color(0, 51, 102));
        lbAtualizar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477118-rotation-right_78579.png"))); // NOI18N
        lbAtualizar.setText("Atualizar");
        lbAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        lbAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbAtualizarMouseClicked(evt);
            }
        });

        txtPlaca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlaca.setForeground(new java.awt.Color(0, 51, 102));
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(0, 51, 102));
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtModelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtModelo.setForeground(new java.awt.Color(0, 51, 102));
        txtModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtAno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAno.setForeground(new java.awt.Color(0, 51, 102));
        txtAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTelefone.setForeground(new java.awt.Color(0, 51, 102));
        txtTelefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtData.setForeground(new java.awt.Color(0, 51, 102));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValor.setForeground(new java.awt.Color(0, 51, 102));
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtValor.setEnabled(false);

        txtVenda.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVenda.setForeground(new java.awt.Color(0, 51, 102));
        txtVenda.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVenda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtVenda.setEnabled(false);

        txtCor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCor.setForeground(new java.awt.Color(0, 51, 102));
        txtCor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCilindrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCilindrada.setForeground(new java.awt.Color(0, 51, 102));
        txtCilindrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCilindrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtLucro.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtLucro.setForeground(new java.awt.Color(0, 51, 102));
        txtLucro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtLucro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtLucro.setEnabled(false);

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNome.setForeground(new java.awt.Color(0, 51, 102));
        txtNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCPF.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCPF.setForeground(new java.awt.Color(0, 51, 102));
        txtCPF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCPF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtObservacao.setForeground(new java.awt.Color(0, 51, 102));
        txtObservacao.setRows(3);
        txtObservacao.setTabSize(4);
        txtObservacao.setBorder(null);
        jScrollPane2.setViewportView(txtObservacao);

        btExcluir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btExcluir.setForeground(new java.awt.Color(0, 51, 102));
        btExcluir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477104-basket_78591_1.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btExcluirMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbPesquisa)
                        .addGap(15, 15, 15)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(motosVendidas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(motosCompradas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCalLucro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lbNome)
                                .addGap(168, 168, 168)
                                .addComponent(jLabel21))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel19)
                                                .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(28, 28, 28)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbValor))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(lbValor1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(lbLucro)
                                                .addComponent(txtLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel3)
                                                .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel5))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel7)
                                                .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGap(18, 18, 18)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel10)
                                                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel15))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel22)
                                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel11)
                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(116, 116, 116)
                                .addComponent(lbNome1)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 441, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(44, 44, 44)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbPesquisa))
                                .addComponent(jLabel4)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(motosVendidas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(motosCompradas)
                                .addComponent(lbAtualizar)
                                .addComponent(btExcluir))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCalLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel7))
                                .addGap(5, 5, 5)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(lbValor1)
                                            .addComponent(lbValor)
                                            .addComponent(lbLucro)
                                            .addComponent(jLabel11))
                                        .addGap(36, 36, 36))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel19)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtVenda, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel10)
                                .addComponent(jLabel15)
                                .addComponent(lbNome1)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lbNome)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCPF, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setSize(new java.awt.Dimension(1203, 704));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void tbMotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMotoMouseClicked
        preencheCampos();
    }//GEN-LAST:event_tbMotoMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void txtPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisaMouseClicked

    }//GEN-LAST:event_txtPesquisaMouseClicked

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed
        pegarSelecionado();

    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void lbAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbAtualizarMouseClicked
        ModelVenda modelVenda = new ModelVenda();
        DAOVenda vendaDao = new DAOVenda();
        
        modelVenda.setPlaca((String) tbMoto.getValueAt(tbMoto.getSelectedRow(), 0));
        // modelVenda.setPlaca(txtPlaca.getText().toUpperCase());
        modelVenda.setMarca((txtMarca.getText()));
        modelVenda.setModelo((txtModelo.getText()));
        modelVenda.setAno((Integer.parseInt(txtAno.getText())));
        modelVenda.setDataCompra((txtData.getText()));
       // modelVenda.setValorCompra(Float.parseFloat((formato.format(txtValor.getText()))));
        modelVenda.setCor((txtCor.getText()));
        modelVenda.setCelindrada((txtCilindrada.getText()));
        modelVenda.setNome(txtNome.getText());
        modelVenda.setCpf(txtCPF.getText());
        modelVenda.setTelefone(txtTelefone.getText());
        modelVenda.setObservacao(txtObservacao.getText());
        
        int resposta = 0;
        resposta = JOptionPane.showConfirmDialog(null, "Deseja realmente alterar as informações?");
        if (resposta == 0) {
            vendaDao.Alterar(modelVenda);
            limpar();
            
        }
        pesquisaCalculo(txtPesquisa.getText());

    }//GEN-LAST:event_lbAtualizarMouseClicked

    private void btExcluirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMouseClicked
        excluir();
    }//GEN-LAST:event_btExcluirMouseClicked
    
     public void excluir() {

        if (tbMoto.getSelectedRow() != -1) {
            ModelVenda modelVenda = new ModelVenda();
            DAOVenda dao = new DAOVenda();

            modelVenda.setPlaca((String) tbMoto.getValueAt(tbMoto.getSelectedRow(), 0));

            dao.deletar(modelVenda);
           // carregarDados();
            MotosVendidas(txtPesquisa.getText());
            //pesquisaNome(txtPesquisa.getText());
            //estoque();
            JOptionPane.showMessageDialog(null, "Excluido com sucesso");

        } else {
            JOptionPane.showMessageDialog(null, "Selecione para apagar");
        }

    }
    
    public void limpar() {
        txtPlaca.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtAno.setText("");
        txtTelefone.setText("");
        txtData.setText("");
        txtValor.setText("");
        txtVenda.setText("");
        txtNome.setText("");
        txtObservacao.setText("");
        txtCilindrada.setText("");
        txtLucro.setText("");
        txtCor.setText("");
        txtCPF.setText("");
        
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Consultar dialog = new Consultar(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btExcluir;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbAtualizar;
    private javax.swing.JLabel lbLucro;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNome1;
    private javax.swing.JLabel lbPesquisa;
    private javax.swing.JLabel lbValor;
    private javax.swing.JLabel lbValor1;
    private javax.swing.JRadioButton motosCompradas;
    private javax.swing.JRadioButton motosVendidas;
    private javax.swing.JTable tbMoto;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCPF;
    private javax.swing.JLabel txtCalLucro;
    private javax.swing.JTextField txtCilindrada;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtLucro;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtValor;
    private javax.swing.JTextField txtVenda;
    // End of variables declaration//GEN-END:variables
}
