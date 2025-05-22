package visao;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Categoria.Embalagem;
import modelo.Categoria.Tamanho;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;

public class FrmGerenciarCategoria extends javax.swing.JFrame {

    private CategoriaDao categoriaDao;
    private DefaultTableModel tabela;
    private Object[][] dados = new Object[0][0];
    private String[] colunas = {"id", "Nome", "Tamanho", "Embalagem"};

    public FrmGerenciarCategoria() {
        initComponents();
        categoriaDao = DaoFactory.instanciarCategoriaDao();
        tabela = new DefaultTableModel(dados, colunas);
        JTInformacoesProduto.setModel(tabela);
        

    }

    private void carregarCategoriasNaTela() {
        tabela.setRowCount(0);

        List<Categoria> categorias = categoriaDao.resgatarCategorias();

        for (Categoria cat : categorias) {
            tabela.addRow(new Object[]{
                cat.getId(),
                cat.getNome(),
                cat.getTamanho().name(),
                cat.getEmbalagem().name()
            });
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLGerenciamentoProdutos = new javax.swing.JLabel();
        JLNomeGerenciamentoC = new javax.swing.JLabel();
        JLTamanhoGerenciamentoC = new javax.swing.JLabel();
        JLEmbalagemGerenciamentoC = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTInformacoesProduto = new javax.swing.JTable();
        JBNovoGerenciamentoC = new javax.swing.JButton();
        JBAlterarGerenciamentoC = new javax.swing.JButton();
        JBExcluirGerenciamentoC = new javax.swing.JButton();
        JBVoltarGerenciamentoC = new javax.swing.JButton();
        JTFNomeDeCategoria = new javax.swing.JTextField();
        JCBTipoTamanhoGerenciamentoC = new javax.swing.JComboBox<>();
        JCBTipoEmbalagemGerenciamentoC = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Categoria");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        JLGerenciamentoProdutos.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        JLGerenciamentoProdutos.setText("Gerenciamento de Categorias");

        JLNomeGerenciamentoC.setText("Nome da Categoria");

        JLTamanhoGerenciamentoC.setText("Tamanho");

        JLEmbalagemGerenciamentoC.setText("Embalagem");

        JTInformacoesProduto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "id", "Nome", "Tamanho", "Embalagem"
            }
        ));
        jScrollPane1.setViewportView(JTInformacoesProduto);

        JBNovoGerenciamentoC.setText("Novo");
        JBNovoGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBNovoGerenciamentoCActionPerformed(evt);
            }
        });

        JBAlterarGerenciamentoC.setText("Alterar");

        JBExcluirGerenciamentoC.setText("Excluir");

        JBVoltarGerenciamentoC.setText("Voltar");

        JTFNomeDeCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JTFNomeDeCategoriaActionPerformed(evt);
            }
        });

        JCBTipoTamanhoGerenciamentoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));
        JCBTipoTamanhoGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCBTipoTamanhoGerenciamentoCActionPerformed(evt);
            }
        });

        JCBTipoEmbalagemGerenciamentoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lata", "Vidro", "Plástico" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(JLGerenciamentoProdutos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JLNomeGerenciamentoC)
                                    .addComponent(JLTamanhoGerenciamentoC)
                                    .addComponent(JLEmbalagemGerenciamentoC))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(JTFNomeDeCategoria)
                                    .addComponent(JCBTipoTamanhoGerenciamentoC, 0, 231, Short.MAX_VALUE)
                                    .addComponent(JCBTipoEmbalagemGerenciamentoC, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(169, 169, 169)
                        .addComponent(JBNovoGerenciamentoC)
                        .addGap(50, 50, 50)
                        .addComponent(JBAlterarGerenciamentoC)
                        .addGap(50, 50, 50)
                        .addComponent(JBExcluirGerenciamentoC)
                        .addGap(50, 50, 50)
                        .addComponent(JBVoltarGerenciamentoC)))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(JLGerenciamentoProdutos)
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLNomeGerenciamentoC)
                    .addComponent(JTFNomeDeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JLTamanhoGerenciamentoC)
                    .addComponent(JCBTipoTamanhoGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JLEmbalagemGerenciamentoC)
                    .addComponent(JCBTipoEmbalagemGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBNovoGerenciamentoC)
                    .addComponent(JBAlterarGerenciamentoC)
                    .addComponent(JBExcluirGerenciamentoC)
                    .addComponent(JBVoltarGerenciamentoC))
                .addGap(30, 30, 30))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JTFNomeDeCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFNomeDeCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFNomeDeCategoriaActionPerformed

    private void JCBTipoTamanhoGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBTipoTamanhoGerenciamentoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCBTipoTamanhoGerenciamentoCActionPerformed

    private void JBNovoGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBNovoGerenciamentoCActionPerformed
        String catNome = JTFNomeDeCategoria.getText().trim();
        String catTamanhoString = JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString();
        Tamanho catTamanho = Tamanho.valueOf(catTamanhoString.toUpperCase());
        String catEmbalagemString = JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString();
        Embalagem catEmbalagem = Embalagem.valueOf(catEmbalagemString.toUpperCase());
        Categoria cat = new Categoria(null, catNome, catTamanho, catEmbalagem);
        categoriaDao.cadastrarCategoria(cat);


    }//GEN-LAST:event_JBNovoGerenciamentoCActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carregarCategoriasNaTela();
    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(FrmGerenciarCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmGerenciarCategoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmGerenciarCategoria().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAlterarGerenciamentoC;
    private javax.swing.JButton JBExcluirGerenciamentoC;
    private javax.swing.JButton JBNovoGerenciamentoC;
    private javax.swing.JButton JBVoltarGerenciamentoC;
    private javax.swing.JComboBox<String> JCBTipoEmbalagemGerenciamentoC;
    private javax.swing.JComboBox<String> JCBTipoTamanhoGerenciamentoC;
    private javax.swing.JLabel JLEmbalagemGerenciamentoC;
    private javax.swing.JLabel JLGerenciamentoProdutos;
    private javax.swing.JLabel JLNomeGerenciamentoC;
    private javax.swing.JLabel JLTamanhoGerenciamentoC;
    private javax.swing.JTextField JTFNomeDeCategoria;
    private javax.swing.JTable JTInformacoesProduto;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
