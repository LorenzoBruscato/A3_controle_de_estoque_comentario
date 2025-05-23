package visao;

import java.text.Normalizer;
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
        tabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // coluna 0 (ID) não pode ser editada
            }
        };
        JTInformacoesProduto.setModel(tabela);

    }

    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
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
                "ID", "Nome", "Tamanho", "Embalagem"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(JTInformacoesProduto);

        JBNovoGerenciamentoC.setText("Novo");
        JBNovoGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBNovoGerenciamentoCActionPerformed(evt);
            }
        });

        JBAlterarGerenciamentoC.setText("Alterar");
        JBAlterarGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAlterarGerenciamentoCActionPerformed(evt);
            }
        });

        JBExcluirGerenciamentoC.setText("Excluir");

        JBVoltarGerenciamentoC.setText("Voltar");
        JBVoltarGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarGerenciamentoCActionPerformed(evt);
            }
        });

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
        
        String catTamanhoString = removerAcentos(JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString());
        Tamanho catTamanho = Tamanho.valueOf(catTamanhoString.toUpperCase());
        
        String catEmbalagemString = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString());
        Embalagem catEmbalagem = Embalagem.valueOf(catEmbalagemString.toUpperCase());
        
        Categoria cat = new Categoria(null, catNome, catTamanho, catEmbalagem);
        categoriaDao.cadastrarCategoria(cat);

        carregarCategoriasNaTela();


    }//GEN-LAST:event_JBNovoGerenciamentoCActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carregarCategoriasNaTela();
    }//GEN-LAST:event_formWindowOpened

    private void JBVoltarGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarGerenciamentoCActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarGerenciamentoCActionPerformed

    private void JBAlterarGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAlterarGerenciamentoCActionPerformed
        // Alterar categoria de acordo com a linha selecionada da JTInformacoesProduto
        // linha selecionada
        int linhaSelecionada = JTInformacoesProduto.getSelectedRow();

        // Pega os valores da linha selecionada
        int id = (Integer) JTInformacoesProduto.getValueAt(linhaSelecionada, 0);
        //System.out.println("Id: " + id);
        String nome = (String) JTInformacoesProduto.getValueAt(linhaSelecionada, 1);
        //System.out.println("nome: " + nome);
        String tamanhoStr = (String) JTInformacoesProduto.getValueAt(linhaSelecionada, 2);
        //System.out.println("Tamanho: " + tamanhoStr);
        Tamanho tamanho = Tamanho.valueOf(tamanhoStr);
        String embalagemStr = (String) JTInformacoesProduto.getValueAt(linhaSelecionada, 3);
        //System.out.println("Embalagem" + embalagemStr);
        Embalagem embalagem = Embalagem.valueOf(embalagemStr);

        // Instanciar categoria
        Categoria cat = new Categoria(id, nome, tamanho, embalagem);

        // Atualizar categoria no banco de dados
        categoriaDao.atualizarCategoria(cat);

        System.out.println("Botão Atualizar clicado");


    }//GEN-LAST:event_JBAlterarGerenciamentoCActionPerformed


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
