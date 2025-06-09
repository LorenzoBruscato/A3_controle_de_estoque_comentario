package visao;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;

/**
 * Classe que representa uma janela Swing para emissão de relatórios de
 * produtos. Permite a seleção de diferentes tipos de relatórios e formatos de
 * saída (Excel, DOC, PDF).
 *
 * @author Victor
 */
public class FrmEmitirRelatorio extends javax.swing.JFrame {

    /**
     * DAO para operações com produtos e geração de relatórios.
     */
    private ProdutoDao produtoDao;

    /**
     * Caminho do arquivo selecionado para salvar o relatório.
     */
    private String caminhoArquivoSelecionado = null;

    /**
     * Factory para criação do DAO de produtos.
     */
    private DaoFactory daoFactory = new DaoFactory();

    /**
     * Constrói uma nova janela de emissão de relatórios. Inicializa os
     * componentes da interface e instancia o DAO de produtos.
     */
    public FrmEmitirRelatorio() {
        initComponents();
        produtoDao = daoFactory.instanciarProdutoDao();
    }

    /**
     * Inicializa os componentes da interface gráfica. Método gerado
     * automaticamente pelo NetBeans.
     */
    @SuppressWarnings("unchecked")

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        ComboBoxArquivo = new javax.swing.JComboBox<>();
        JBVoltar = new javax.swing.JButton();
        JBEmitir = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        JBSalvarcomo = new javax.swing.JButton();
        JTFCaminhoArquivo = new javax.swing.JTextField();
        ComboBoxRelatorio = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Emitir Relatórios");

        jLabel1.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        jLabel1.setText("Emitir Relatórios");

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

        ComboBoxRelatorio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lista de preço", "Balanço físico/financeiro", "Relatório de produtos abaixo da quantidade mínima", "Relatório de produtos acima da quantidade máxima", "Relatório da quantidade de produtos por categoria", "Relatório de movimentação" }));
        ComboBoxRelatorio.setPreferredSize(new java.awt.Dimension(323, 24));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(180, 180, 180))
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(JBSalvarcomo, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(ComboBoxRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ComboBoxArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(17, 17, 17)))
                        .addGap(52, 52, 52))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JBEmitir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(JBVoltar)
                        .addGap(49, 49, 49))))
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
                    .addComponent(ComboBoxArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ComboBoxRelatorio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addComponent(JBSalvarcomo)
                .addGap(18, 18, 18)
                .addComponent(JTFCaminhoArquivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBVoltar)
                    .addComponent(JBEmitir))
                .addGap(25, 25, 25))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manipulador de evento para o botão Voltar. Fecha a janela atual e abre o
     * menu principal.
     *
     * @param evt Evento de ação do botão
     */
    private void JBVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();    }//GEN-LAST:event_JBVoltarActionPerformed

    /**
     * Manipulador de evento para o botão Emitir. Gera o relatório no formato e
     * local selecionados.
     *
     * @param evt Evento de ação do botão
     */
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
                } else if ("Pdf".equalsIgnoreCase(tipoFormatacao)) {
                    produtoDao.gerarRelatorioMovimentacaoPDF(caminhoArquivoSelecionado, nomeDoArquivo);
                }
        }
    }//GEN-LAST:event_JBEmitirActionPerformed

    /**
     * Manipulador de evento para o botão Salvar Como. Abre um diálogo para
     * seleção do local e nome do arquivo de saída.
     *
     * @param evt Evento de ação do botão
     */
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
