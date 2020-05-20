package telas;

import Model.ModelCadastro;
import Dao.DAOCadastro;
import java.text.NumberFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rodrigo
 */
public class Atualizar extends javax.swing.JDialog {

    NumberFormat formato = NumberFormat.getCurrencyInstance();

    public Atualizar(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        MotosCompradas(txtPesquisa.getText());
        pegarSelecionado();
        txtObservacao.setLineWrap(true);
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
    }

    public void pegarSelecionado() {

        if (motosCompradas.isSelected()) {

            MotosCompradas(txtPesquisa.getText());
            //.setText("Motos Compradas");
            MotosCompradas(txtPesquisa.getText());
            // txtPesquisa.grabFocus();
            lbNome.setText("Nome do Vendedor:");
//            lbApurado.setVisible(false);
            // txtApurado.setVisible(false);
            

        } else if (motosVendidas.isSelected()) {
            pesquisaCalculo(txtPesquisa.getText());
            // lbPesquisa.setText("Motos Vendidas");
            lbNome.setText("Nome do Comprador:");
            // pesquisaCalculo(txtData.getText());
        }

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

        }
        //txtTotal.setText(formato.format(resultado += valor));
        tbMoto.setModel(tabela);

    }

    public void carregarDados(String placa) {
        DAOCadastro dao = new DAOCadastro();
        DefaultTableModel tabela = new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "id",
                    "Placa",
                    "Marca",
                    "modelo",
                    "ano",
                    "cor",
                    "Data",
                    "Valor",
                    "Cilindrada",
                    "Nome",
                    "Telefone",
                    "cpf",
                    "observacao"
                }) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };

        for (ModelCadastro mb : dao.pesquisarPlaca(placa)) {
            tabela.addRow(new Object[]{
                mb.getId(),
                mb.getPlaca(),
                mb.getMarca(),
                mb.getModelo(),
                mb.getAno(),
                mb.getCor(),
                mb.getDataCompra(),
                mb.getValorCompra(),
                mb.getCelindrada(),
                mb.getNome(),
                mb.getTelefone(),
                mb.getCpf(),
                mb.getObservacao()
            });
        }
        lbValor.setText("Valor de Compra:");
        lbNome.setText("nome do Vendedor:");
        tbMoto.setModel(tabela);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
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
        txtPlaca = new javax.swing.JTextField();
        txtMarca = new javax.swing.JTextField();
        txtModelo = new javax.swing.JTextField();
        txtAno = new javax.swing.JTextField();
        txtCilindrada = new javax.swing.JTextField();
        txtCor = new javax.swing.JTextField();
        txtCompra = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        txtVendedor = new javax.swing.JTextField();
        txtCpf = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        btAtualizar = new javax.swing.JLabel();
        lbNome1 = new javax.swing.JLabel();
        motosVendidas = new javax.swing.JRadioButton();
        motosCompradas = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacao = new javax.swing.JTextArea();

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

        txtPlaca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtPlaca.setForeground(new java.awt.Color(0, 51, 102));
        txtPlaca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtPlaca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtMarca.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtMarca.setForeground(new java.awt.Color(0, 51, 102));
        txtMarca.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtMarca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        txtMarca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMarcaActionPerformed(evt);
            }
        });

        txtModelo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtModelo.setForeground(new java.awt.Color(0, 51, 102));
        txtModelo.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtModelo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtAno.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtAno.setForeground(new java.awt.Color(0, 51, 102));
        txtAno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtAno.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCilindrada.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCilindrada.setForeground(new java.awt.Color(0, 51, 102));
        txtCilindrada.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCilindrada.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCor.setForeground(new java.awt.Color(0, 51, 102));
        txtCor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCompra.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCompra.setForeground(new java.awt.Color(0, 51, 102));
        txtCompra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCompra.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtData.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtData.setForeground(new java.awt.Color(0, 51, 102));
        txtData.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtData.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtVendedor.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtVendedor.setForeground(new java.awt.Color(0, 51, 102));
        txtVendedor.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtVendedor.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtCpf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtCpf.setForeground(new java.awt.Color(0, 51, 102));
        txtCpf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtCpf.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        txtTelefone.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        txtTelefone.setForeground(new java.awt.Color(0, 51, 102));
        txtTelefone.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtTelefone.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));

        btAtualizar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btAtualizar.setForeground(new java.awt.Color(0, 51, 102));
        btAtualizar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IconsPequenos/1485477118-rotation-right_78579.png"))); // NOI18N
        btAtualizar.setText("Atualizar");
        btAtualizar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 102)));
        btAtualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btAtualizarMouseClicked(evt);
            }
        });

        lbNome1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        lbNome1.setForeground(new java.awt.Color(0, 51, 102));
        lbNome1.setText("Observação:");

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

        txtObservacao.setColumns(20);
        txtObservacao.setFont(new java.awt.Font("Arial", 0, 20)); // NOI18N
        txtObservacao.setForeground(new java.awt.Color(0, 51, 102));
        txtObservacao.setRows(5);
        jScrollPane2.setViewportView(txtObservacao);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtPesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(motosVendidas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(motosCompradas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btAtualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 28, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(113, 113, 113)
                                    .addComponent(jLabel5)
                                    .addGap(157, 157, 157))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel19)
                                        .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(37, 37, 37)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbValor)
                                        .addComponent(txtCompra)))
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(txtPlaca)
                                    .addGap(29, 29, 29)
                                    .addComponent(txtMarca, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8)))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel11))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel10))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel22)
                                        .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                            .addGap(8, 8, 8)
                                            .addComponent(jLabel21))
                                        .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(txtModelo, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)))
                            .addComponent(lbNome)
                            .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 602, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbNome1)
                                .addGap(91, 91, 91))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap())))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtPesquisa)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(motosVendidas, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(motosCompradas)
                        .addComponent(btAtualizar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(jLabel7))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPlaca)
                                    .addComponent(txtMarca)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(txtModelo)))
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel15)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCilindrada, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(lbValor))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtData))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(jLabel10)
                            .addComponent(jLabel22)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCor, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAno, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCpf, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNome)
                        .addGap(16, 16, 16)
                        .addComponent(txtVendedor, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lbNome1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(51, 51, 51))
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

        setSize(new java.awt.Dimension(921, 683));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel2MouseClicked


    private void tbMotoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbMotoMouseClicked

        if (motosVendidas.isSelected()) {

            if (tbMoto.getSelectedRow() != -1) {

                txtPlaca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 0).toString());
                txtMarca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 1).toString());
                txtModelo.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 2).toString());
                txtAno.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 3).toString());
                txtCor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 4).toString());
                txtData.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 5).toString());
                txtCompra.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 6).toString());
                txtCilindrada.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 7).toString());
                txtVendedor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 8).toString());
                txtCpf.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 10).toString());
                txtTelefone.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 9).toString());
                txtObservacao.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 11).toString());

            }
        } else if (motosCompradas.isSelected()) {
            if (tbMoto.getSelectedRow() != -1) {

            txtPlaca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 0).toString());
            txtMarca.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 1).toString());
            txtModelo.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 2).toString());
            txtAno.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 3).toString());
            txtCor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 4).toString());
            txtData.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 5).toString());
            txtCompra.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(),6).toString());
            txtCilindrada.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 7).toString());
            txtVendedor.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 8).toString());
            txtCpf.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 9).toString());
            txtTelefone.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 10).toString());
            txtObservacao.setText(tbMoto.getValueAt(tbMoto.getSelectedRow(), 11).toString());
            }
        }
    }//GEN-LAST:event_tbMotoMouseClicked

    private void txtPesquisaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtPesquisaMouseClicked

    }//GEN-LAST:event_txtPesquisaMouseClicked

    private void txtPesquisaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPesquisaKeyPressed
        pegarSelecionado();
    }//GEN-LAST:event_txtPesquisaKeyPressed

    private void btAtualizarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btAtualizarMouseClicked
        ModelCadastro mb = new ModelCadastro();
        DAOCadastro dao = new DAOCadastro();

        mb.setPlaca((String) tbMoto.getValueAt(tbMoto.getSelectedRow(), 0));
        mb.setPlaca(txtPlaca.getText().toUpperCase());
        mb.setMarca((txtMarca.getText()));
        mb.setModelo((txtModelo.getText()));
        mb.setAno((Integer.parseInt(txtAno.getText())));
        mb.setDataCompra((txtData.getText()));
//        mb.setValorCompra(Float.parseFloat(txtCompra.getText()));
        mb.setCor((txtCor.getText()));
        mb.setCelindrada((txtCilindrada.getText()));
        mb.setNome(txtVendedor.getText());
        mb.setCpf(txtCpf.getText());
        mb.setTelefone(txtTelefone.getText());
        mb.setObservacao(txtObservacao.getText());

        dao.alterar(mb);

        MotosCompradas(txtPesquisa.getText());

    }//GEN-LAST:event_btAtualizarMouseClicked

    private void txtMarcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMarcaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMarcaActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Atualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Atualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Atualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Atualizar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Atualizar dialog = new Atualizar(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel btAtualizar;
    private javax.swing.ButtonGroup buttonGroup1;
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
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbNome1;
    private javax.swing.JLabel lbValor;
    private javax.swing.JRadioButton motosCompradas;
    private javax.swing.JRadioButton motosVendidas;
    private javax.swing.JTable tbMoto;
    private javax.swing.JTextField txtAno;
    private javax.swing.JTextField txtCilindrada;
    private javax.swing.JTextField txtCompra;
    private javax.swing.JTextField txtCor;
    private javax.swing.JTextField txtCpf;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtMarca;
    private javax.swing.JTextField txtModelo;
    private javax.swing.JTextArea txtObservacao;
    private javax.swing.JTextField txtPesquisa;
    private javax.swing.JTextField txtPlaca;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtVendedor;
    // End of variables declaration//GEN-END:variables
}
