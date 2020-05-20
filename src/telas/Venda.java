/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import Model.ModelVenda;
import Model.ModelCadastro;
import Conexao.ConexaoSqlite;
import Dao.DAOCadastro;
import Dao.DAOVenda;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import static java.awt.image.ImageObserver.ALLBITS;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class Venda extends javax.swing.JDialog {

    private ModelCadastro mb = new ModelCadastro();
    private DAOVenda vd = new DAOVenda();
    private Principal tela;
    NumberFormat formato = NumberFormat.getCurrencyInstance();
    public Venda(java.awt.Frame parent, boolean modal, Principal tela) {
        super(parent, modal);
        initComponents();
        limparCampo();
        this.tela = tela;
        //carregarDados();
        pesquisa(txtPesquisa.getText());
        preencheCampos();
        Date data = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/YYYY");
        txtData.setText(formato.format(data));
        txtObservacao.setText("SEM OBSERVAÇÕES!");
    }

    public LocalDate converteData() {

        String data = txtData.getText();
        String[] dataSeparada = data.split("/");
        LocalDate hoje = LocalDate.of(Integer.parseInt(dataSeparada[2]), Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
        return hoje;
    }

    public void preencheCampos() {

        if (tbVenda.getSelectedRow() != -1) {

            txtPlaca.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 0).toString());
            txtModelo.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 2).toString());
            txtMarca.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 1).toString());
            txtAno.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 3).toString());
            txtCilindrada.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 4).toString());
            txtCor.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(), 5).toString());
            txtValorCompra.setText(tbVenda.getValueAt(tbVenda.getSelectedRow(),6).toString());

        }

    }

    public void excluir() {

        if (tbVenda.getSelectedRow() != -1) {
            ModelCadastro motoBean = new ModelCadastro();
            DAOCadastro dao = new DAOCadastro();

            motoBean.setPlaca((String) tbVenda.getValueAt(tbVenda.getSelectedRow(), 0));

            dao.deletarVenda(motoBean);
            pesquisa(txtPesquisa.getText());

        } else {
            JOptionPane.showMessageDialog(null, "Selecione para apagar");
        }

    }

    public void pesquisa(String placa) {

        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Placa",
                    "Marca",
                    "Modelo",
                    "Ano",
                    "Celindrada",
                    "Cor",
                    "Valor Compra"
                }) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        for (ModelCadastro moto : vd.pesquisarPlaca(placa)) {
            tabela.addRow(new Object[]{
                moto.getPlaca(),
                moto.getMarca(),
                moto.getModelo(),
                moto.getAno(),
                moto.getCelindrada(),
                moto.getCor(),
                moto.getValorCompra()
            });

            tbVenda.setModel(tabela);
        }

    }

    public void carregarDados() {

        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "placa", 
                    "nome", 
                    "Ano",
                   // "Valor Venda",
                    "Valor compra"
                }
        );

        for (ModelVenda mb : vd.listar()) {
            float lucro = (mb.getValorVenda()-mb.getValorCompra());
            tabela.addRow(new Object[]{
                mb.getPlaca(),
                mb.getNome(),
                mb.getTelefone(),
                //mb.getValorVenda(),
                mb.getValorCompra(),
                lucro
            });

            tbVenda.setModel(tabela);
        }

    }

    public void pdfDeVenda() throws DocumentException, FileNotFoundException, BadElementException, IOException {

        Document documneto = new Document();
        Statement stmt;
        Connection conexao;
        ConexaoSqlite sqlite;

        PdfWriter.getInstance(documneto, new FileOutputStream("C:/sanielMotos/documento/Venda.pdf"));//local onde salvar o pdf
        documneto.open();

        //************************ADCIONANDO UMA IMAGEM *******************************
        Image figura = Image.getInstance("C:/sanielMotos/documento/topo.png");//local da imagem
        // figura.setAbsolutePosition(TOP_ALIGNMENT, checkImage(null, rootPane));
        figura.setCompressionLevel(ALLBITS);
        documneto.add(figura);
        documneto.add(new Paragraph("                                                                      "));

        //********************************************************************************************
        PdfPCell cabecalho = new PdfPCell(new Paragraph("Recibo de Compra e Venda"));
        documneto.add(new Paragraph(" "));
        cabecalho.setHorizontalAlignment(Element.ALIGN_CENTER);
        //****************************************************************************************
        documneto.add(new Paragraph("                                                                      "));
        documneto.add(new Paragraph("Recebi do Sr(a). " + txtNome.getText().toUpperCase() + ", portador do CPF: " + txtCpf.getText() + ", referente à venda de uma moto " + txtModelo.getText() + ", Placa " + txtPlaca.getText() + ","
                + " a quantia de R$ " + txtValor.getText() + ","
                + " de minha propriedade, dando-lhe por este recibo a devida quitação.\n"));
        documneto.add(new Paragraph("        "));
        documneto.add(new Paragraph("Observações: " + txtObservacao.getText()));
        documneto.add(new Paragraph("        "));
        documneto.add(new Paragraph("                                          João Pessoa, " + txtData.getText() + "."));
        documneto.add(new Paragraph("        "));
        documneto.add(new Paragraph("Assinatura do Comprador:__________________________________________      "));
        documneto.add(new Paragraph("                                                                         "));
        documneto.add(new Paragraph("Assinatura do Vendedor:___________________________________________       "));
        documneto.add(new Paragraph("                                                                         "));
        documneto.close();
        documneto.setPageSize(PageSize.A4);

        try {
            Desktop.getDesktop().open(new File("C:/sanielMotos/documento/Venda.pdf"));
        } catch (IOException ex) {
            System.err.println("erro ao abrir " + ex);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbVenda = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtPesquisa = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtCilindrada = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtCpf = new javax.swing.JFormattedTextField();
        txtPagamento = new javax.swing.JComboBox<>();
        txtNome = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JFormattedTextField();
        txtObservacao = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtData = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtValorCompra = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));

        tbVenda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tbVenda.setForeground(new java.awt.Color(0, 51, 102));
        tbVenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Placa", "Nome", "Ano", "Valor Venda", "Valor Compra", "Lucro"
            }
        ));
        tbVenda.setSelectionBackground(new java.awt.Color(0, 51, 102));
        tbVenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbVendaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbVenda);

        jPanel5.setBackground(new java.awt.Color(0, 51, 102));
        jPanel5.setLayout(null);

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText(" X");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel5.add(jLabel13);
        jLabel13.setBounds(1050, 0, 20, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("R5 Soft");
        jPanel5.add(jLabel16);
        jLabel16.setBounds(390, 0, 80, 30);

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

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477207-magnifier_78608.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 51, 102));
        jLabel10.setText("Placa:");

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 51, 102));
        jLabel5.setText("Marca:");

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 102));
        jLabel6.setText("Modelo:");

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 51, 102));
        jLabel7.setText("Cilindrada:");

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 51, 102));
        jLabel8.setText("Ano:");

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 51, 102));
        jLabel9.setText("Cor:");

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(0, 51, 102));
        jLabel11.setText("Valor:");

        jLabel17.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 51, 102));
        jLabel17.setText("Data Venda:");

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

        txtCor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCor.setForeground(new java.awt.Color(0, 51, 102));
        txtCor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCilindrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCilindrada.setForeground(new java.awt.Color(0, 51, 102));
        txtCilindrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCilindrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtValor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtValor.setForeground(new java.awt.Color(0, 51, 102));
        txtValor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtValor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValorActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(0, 51, 102));
        jLabel14.setText("Nome:");

        jLabel12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(0, 51, 102));
        jLabel12.setText("Telefone:");

        jLabel15.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 51, 102));
        jLabel15.setText("CPF:");

        jLabel18.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(0, 51, 102));
        jLabel18.setText("Pagamento:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 51, 102));
        jLabel2.setText("Observação:");

        txtCpf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtCpf.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txtCpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtCpf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCpf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCpfActionPerformed(evt);
            }
        });

        txtPagamento.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPagamento.setForeground(new java.awt.Color(0, 51, 102));
        txtPagamento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "Dinheiro", "Cartão", "Cartão e dinheiro" }));
        txtPagamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagamentoActionPerformed(evt);
            }
        });

        txtNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtNome.setForeground(new java.awt.Color(0, 51, 102));
        txtNome.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtNome.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNomeActionPerformed(evt);
            }
        });
        txtNome.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNomeKeyPressed(evt);
            }
        });

        txtTelefone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtTelefone.setForeground(new java.awt.Color(0, 51, 102));
        try {
            txtTelefone.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("(##)#####-####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        txtTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTelefone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTelefoneActionPerformed(evt);
            }
        });

        txtObservacao.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtObservacao.setForeground(new java.awt.Color(0, 51, 102));
        txtObservacao.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtObservacao.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Informações da Moto");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 51, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Informações do Comprador");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(0, 51, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477072-check_78599 (1).png"))); // NOI18N
        jLabel19.setText("Finalizar");
        jLabel19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(0, 51, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477072-check_78599 (1).png"))); // NOI18N
        jLabel20.setText("Cancelar");
        jLabel20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtData.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtData.setForeground(new java.awt.Color(0, 51, 102));
        txtData.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtData.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 102), 1, true));

        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("valor Compra");

        txtValorCompra.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesquisa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel17))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel15))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel11)
                                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 344, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel18)
                                    .addComponent(txtTelefone)))
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 552, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtPagamento, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel14)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(101, 101, 101)
                                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtObservacao))
                        .addGap(0, 50, Short.MAX_VALUE))))
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6))
                            .addComponent(jLabel10))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPlaca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jLabel7)
                            .addComponent(jLabel17)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtData, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtPagamento)
                            .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtObservacao, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel20)
                                .addComponent(txtValorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(14, 14, 14))
                    .addComponent(jScrollPane1))
                .addContainerGap(20, Short.MAX_VALUE))
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

        setSize(new java.awt.Dimension(1084, 596));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        ModelVenda motoBean = new ModelVenda();
        DAOVenda vendaDao = new DAOVenda();
        ModelCadastro mb = new ModelCadastro();
        
        motoBean.setId(WIDTH);
        motoBean.setDataVenda(txtData.getText());
        motoBean.setCpf(txtCpf.getText());
        motoBean.setNome(txtNome.getText());
        motoBean.setPagamento((String) txtPagamento.getSelectedItem());
        motoBean.setPlaca(txtPlaca.getText());
        motoBean.setTelefone(txtTelefone.getText());
        
        //salva as vendas para consultas
        float venda = Float.parseFloat(txtValor.getText());
        float compra = Float.parseFloat(txtValorCompra.getText());
        mb.setId(WIDTH);
        mb.setMarca(txtMarca.getText().toUpperCase());
        mb.setAno(Integer.parseInt(txtAno.getText()));
        mb.setModelo(txtModelo.getText().toUpperCase());
        mb.setCelindrada(txtCilindrada.getText().toUpperCase());
        mb.setValorVenda(Float.parseFloat(txtValor.getText()));
        mb.setCor(txtCor.getText().toUpperCase());
        mb.setPlaca(txtPlaca.getText().toUpperCase());
        mb.setDataCompra(txtData.getText());
        mb.setNome(txtNome.getText().toUpperCase());
        mb.setCpf(txtCpf.getText());
        mb.setTelefone(txtTelefone.getText());
        mb.setValorCompra(Float.parseFloat(txtValorCompra.getText()));
        mb.setLucro(venda-compra);
        mb.setObservacao(txtObservacao.getText());
        
        vendaDao.salvarVendas(mb);
        try {
            vendaDao.salvar(motoBean);
            pdfDeVenda();
        } catch (DocumentException | IOException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
        excluir();
        tela.carregarDados2();
        limparCampo();

    }//GEN-LAST:event_jLabel19MouseClicked

    private void txtPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisaMouseClicked

    }//GEN-LAST:event_txtPesquisaMouseClicked

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed
        pesquisa(txtPesquisa.getText());
    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void tbVendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbVendaMouseClicked
        preencheCampos();
        txtNome.grabFocus();
    }//GEN-LAST:event_tbVendaMouseClicked

    private void txtNomeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNomeKeyPressed
      
    }//GEN-LAST:event_txtNomeKeyPressed

    private void txtNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNomeActionPerformed
       txtNome.transferFocus();
    }//GEN-LAST:event_txtNomeActionPerformed

    private void txtTelefoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTelefoneActionPerformed
        txtTelefone.transferFocus();
    }//GEN-LAST:event_txtTelefoneActionPerformed

    private void txtCpfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCpfActionPerformed
        txtCpf.transferFocus();
    }//GEN-LAST:event_txtCpfActionPerformed

    private void txtValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValorActionPerformed
        txtValor.transferFocus();
    }//GEN-LAST:event_txtValorActionPerformed

    private void txtPagamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagamentoActionPerformed
     txtPagamento.transferFocus();
        
    }//GEN-LAST:event_txtPagamentoActionPerformed

    public void limparCampo() {
        txtMarca.setText("");
        txtModelo.setText("");
        txtCilindrada.setText("");
        txtCpf.setText("");
        txtNome.setText("");
        txtCor.setText("");
        txtAno.setText("");
        txtData.setText("");
        txtPlaca.setText("");
        txtValor.setText("");
        txtObservacao.setText("");
        txtPagamento.setSelectedItem("Selecione");
        txtTelefone.setText("");

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
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Venda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Venda dialog = new Venda(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbVenda;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCilindrada;
    private javax.swing.JTextField txtCor;
    private javax.swing.JFormattedTextField txtCpf;
    private javax.swing.JLabel txtData;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtObservacao;
    private javax.swing.JComboBox<String> txtPagamento;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JFormattedTextField txtTelefone;
    private javax.swing.JTextField txtValor;
    private javax.swing.JLabel txtValorCompra;
    // End of variables declaration//GEN-END:variables

}
