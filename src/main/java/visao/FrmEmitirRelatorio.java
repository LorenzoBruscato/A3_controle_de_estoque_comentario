package visao;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;

/**
 *
 * @author Victor
 *
 */
public class FrmEmitirRelatorio extends javax.swing.JFrame {

    // atributos se necessario
    private ProdutoDao produtoDao;
    private String caminhoArquivoSelecionado = null;
    private DaoFactory daoFactory = new DaoFactory();

    /**
     *
     */
    public FrmEmitirRelatorio() {
        initComponents();
        produtoDao = daoFactory.instanciarProdutoDao();
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
        JBSalvarcomo = new javax.swing.JButton();
        JTFCaminhoArquivo = new javax.swing.JTextField();

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

        JBSalvarcomo.setText("Selecione o destino...");
        JBSalvarcomo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSalvarcomoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JTFCaminhoArquivo)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(ComboBoxRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ComboBoxArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JBEmitir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBVoltar)
                        .addGap(49, 49, 49))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(JBSalvarcomo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addGap(47, 47, 47)
                .addComponent(JBSalvarcomo)
                .addGap(18, 18, 18)
                .addComponent(JTFCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
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
        if (caminhoArquivoSelecionado == null) {
            JOptionPane.showMessageDialog(null, "Você precisa selecionar onde salvar o arquivo (clique em 'Salvar como').");
            return;
        }

        int indexSelecionado = ComboBoxRelatorio.getSelectedIndex();
        String tipoFormatacao = (String) ComboBoxArquivo.getSelectedItem();
        String nomeDoArquivo = new File(caminhoArquivoSelecionado).getName().replaceFirst("[.][^.]+$", "");

        switch (indexSelecionado) {
            case 0:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoDoc(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                }
            case 1:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroDOC(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioBalancoFisicoFinanceiroPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                }
            case 2:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                }
            case 3:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                }
            case 4:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaDoc(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioListaProdutoPorCategoriaPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                }
            case 5:
                if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioMovimentacaoExcel(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioMovimentacaoDoc(caminhoArquivoSelecionado, nomeDoArquivo);
                    break;
                } else if("Pdf".equalsIgnoreCase(tipoFormatacao)){
                    produtoDao.gerarRelatorioMovimentacaoPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                }
        }
    }//GEN-LAST:event_JBEmitirActionPerformed

    private void JBSalvarcomoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSalvarcomoActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Salvar como");

        String tipoFormatacao = (String) ComboBoxArquivo.getSelectedItem();
        FileNameExtensionFilter filtro;

        if ("PDF".equalsIgnoreCase(tipoFormatacao)) {
            filtro = new FileNameExtensionFilter("Arquivos PDF", "pdf");
            fileChooser.setFileFilter(filtro);
        } else if ("Excel".equalsIgnoreCase(tipoFormatacao)) {
            filtro = new FileNameExtensionFilter("Arquivos Excel", "xlsx");
            fileChooser.setFileFilter(filtro);
        } else if ("Doc".equalsIgnoreCase(tipoFormatacao)) {
            filtro = new FileNameExtensionFilter("Arquivos DOC", "docx");
            fileChooser.setFileFilter(filtro);
        }

        int resultado = fileChooser.showSaveDialog(null);
        if (resultado == JFileChooser.APPROVE_OPTION) {
            File arquivo = fileChooser.getSelectedFile();
            caminhoArquivoSelecionado = arquivo.getAbsolutePath();

            // Garante a extensão
            if ("PDF".equalsIgnoreCase(tipoFormatacao) && !caminhoArquivoSelecionado.toLowerCase().endsWith(".pdf")) {
                caminhoArquivoSelecionado += ".pdf";
            } else if ("Excel".equalsIgnoreCase(tipoFormatacao) && !caminhoArquivoSelecionado.toLowerCase().endsWith(".xlsx")) {
                caminhoArquivoSelecionado += ".xlsx";
            } else if ("Doc".equalsIgnoreCase(tipoFormatacao) && !caminhoArquivoSelecionado.toLowerCase().endsWith(".docx")) {
                caminhoArquivoSelecionado += ".docx";
            }
            JTFCaminhoArquivo.setText(caminhoArquivoSelecionado);
        }
    }//GEN-LAST:event_JBSalvarcomoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxArquivo;
    private javax.swing.JComboBox<String> ComboBoxRelatorio;
    private javax.swing.JButton JBEmitir;
    private javax.swing.JButton JBSalvarcomo;
    private javax.swing.JButton JBVoltar;
    private javax.swing.JTextField JTFCaminhoArquivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
}
