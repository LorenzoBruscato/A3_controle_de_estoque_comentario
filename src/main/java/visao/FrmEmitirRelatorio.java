package visao;

import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;

public class FrmEmitirRelatorio extends javax.swing.JFrame {

    // atributos se necessario
    private ProdutoDao produtoDao;

    public FrmEmitirRelatorio() {
        initComponents();
        produtoDao = DaoFactory.instanciarProdutoDao();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ComboBoxRelatorio = new javax.swing.JComboBox<>();
        ComboBoxArquivo = new javax.swing.JComboBox<>();
        JBVoltar = new javax.swing.JButton();
        JBEmitir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        JTFCaminhoArquivo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        JTFNomeDoArquivo = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emitir Relatórios");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Emitir Relatórios");

        ComboBoxRelatorio.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ComboBoxRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lista de preço", "Balanço físico/financeiro", "Relatório de produtos abaixo da quantidade mínima", "Relatório de produtos abaixo da quantidade máxima", "Relatório da quantidade de produtos por categoria", "Relatório de movimentação" }));
        ComboBoxRelatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboBoxRelatorioActionPerformed(evt);
            }
        });

        ComboBoxArquivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        ComboBoxArquivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Excel", "Doc", "PDF" }));

        JBVoltar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBVoltar.setText("Voltar");
        JBVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarActionPerformed(evt);
            }
        });

        JBEmitir.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBEmitir.setText("Emitir");
        JBEmitir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBEmitirActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel2.setText("Tipo de relatório");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel3.setText("Tipo de arquivo");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jLabel4.setText("Caminho do Arquivo");

        JTFCaminhoArquivo.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JTFCaminhoArquivo.setText("C:\\");

            jLabel5.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
            jLabel5.setText("Nome do arquivo");

            JTFNomeDoArquivo.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    JTFNomeDoArquivoActionPerformed(evt);
                }
            });

            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
            getContentPane().setLayout(layout);
            layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(180, 180, 180))
                .addGroup(layout.createSequentialGroup()
                    .addGap(73, 73, 73)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(ComboBoxRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(ComboBoxArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(17, 17, 17))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel3)))
                            .addGap(52, 52, 52))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(JTFNomeDoArquivo, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(JTFCaminhoArquivo)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                    .addComponent(JBEmitir)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(JBVoltar)))
                            .addGap(49, 49, 49))
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel4))
                            .addGap(0, 0, Short.MAX_VALUE))))
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addGap(18, 18, 18)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(jLabel3))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ComboBoxRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ComboBoxArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                    .addComponent(jLabel5)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JTFNomeDoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jLabel4)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(JTFCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(33, 33, 33)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(JBVoltar)
                        .addComponent(JBEmitir))
                    .addGap(25, 25, 25))
            );

            pack();
            setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

    private void JBVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();    }//GEN-LAST:event_JBVoltarActionPerformed

    private void ComboBoxRelatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboBoxRelatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ComboBoxRelatorioActionPerformed

    private void JBEmitirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBEmitirActionPerformed
        String caminhoArquivo = JTFCaminhoArquivo.getText();
        String nomeDoArquivo = JTFNomeDoArquivo.getText();
        int indexSelecionado = ComboBoxRelatorio.getSelectedIndex();
        String tipoFormatacao = (String) ComboBoxArquivo.getSelectedItem();

        switch (indexSelecionado) {
            case 0:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoDoc(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoPDF(caminhoArquivo, nomeDoArquivo);
                    break;
                }
            case 1:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroDOC(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroPDF(caminhoArquivo, nomeDoArquivo);
                    break;
                }
            case 2:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(caminhoArquivo, nomeDoArquivo);
                    break;
                }
            case 3:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaDoc(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaPDF(caminhoArquivo, nomeDoArquivo);
                    break;
                }
            case 4:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaDoc(caminhoArquivo, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaPDF(caminhoArquivo, nomeDoArquivo);
                    break;
                }
            case 5:
                if("Excel".equalsIgnoreCase(tipoFormatacao)){
                    produtoDao.gerarRelatorioMovimentacaoExcel(caminhoArquivo, nomeDoArquivo);
                    break;
                }else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioMovimentacaoDoc(caminhoArquivo, nomeDoArquivo);
                    break;
                }
        }
    }//GEN-LAST:event_JBEmitirActionPerformed

    private void JTFNomeDoArquivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFNomeDoArquivoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFNomeDoArquivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxArquivo;
    private javax.swing.JComboBox<String> ComboBoxRelatorio;
    private javax.swing.JButton JBEmitir;
    private javax.swing.JButton JBVoltar;
    private javax.swing.JTextField JTFCaminhoArquivo;
    private javax.swing.JTextField JTFNomeDoArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
