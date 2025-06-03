package visao;

import java.text.Normalizer;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Categoria.Embalagem;
import modelo.Categoria.Tamanho;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.db.DbException;

public class FrmGerenciarCategoria extends javax.swing.JFrame {

    private CategoriaDao categoriaDao;
    private DefaultTableModel tabela;
    private Object[][] dados = new Object[0][0];
    private String[] colunas = {"ID", "Nome", "Tamanho", "Embalagem"};

    public FrmGerenciarCategoria() {
        initComponents();
        categoriaDao = DaoFactory.instanciarCategoriaDao();
        tabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // coluna 0 (ID) não pode ser editada
            }
        };
        JTableCategoria.setModel(tabela);

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
        jScrollPane1 = new javax.swing.JScrollPane();
        JTableCategoria = new javax.swing.JTable();
        JBNovoGerenciamentoC = new javax.swing.JButton();
        JBAlterarGerenciamentoC = new javax.swing.JButton();
        JBExcluirGerenciamentoC = new javax.swing.JButton();
        JBVoltarCategoria = new javax.swing.JButton();
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

        JLNomeGerenciamentoC.setText("Nome da Categoria:");

        JTableCategoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Tamanho", "Embalagem"
            }
        ));
        JTableCategoria.getTableHeader().setReorderingAllowed(false);
        JTableCategoria.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableCategoriaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableCategoria);

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
        JBExcluirGerenciamentoC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBExcluirGerenciamentoCActionPerformed(evt);
            }
        });

        JBVoltarCategoria.setText("Voltar");
        JBVoltarCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarCategoriaActionPerformed(evt);
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
                .addGap(96, 96, 96)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(JLNomeGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JLGerenciamentoProdutos)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(JTFNomeDeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBNovoGerenciamentoC)
                                        .addGap(18, 18, 18)
                                        .addComponent(JBAlterarGerenciamentoC)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBExcluirGerenciamentoC)
                                        .addGap(18, 18, 18)
                                        .addComponent(JBVoltarCategoria))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JCBTipoTamanhoGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(JCBTipoEmbalagemGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(96, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(JLGerenciamentoProdutos)
                .addGap(146, 146, 146)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBNovoGerenciamentoC)
                    .addComponent(JBAlterarGerenciamentoC)
                    .addComponent(JBExcluirGerenciamentoC)
                    .addComponent(JBVoltarCategoria))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCBTipoEmbalagemGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBTipoTamanhoGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFNomeDeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLNomeGerenciamentoC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void JTFNomeDeCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JTFNomeDeCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JTFNomeDeCategoriaActionPerformed

    private void JCBTipoTamanhoGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCBTipoTamanhoGerenciamentoCActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_JCBTipoTamanhoGerenciamentoCActionPerformed

    private void JBNovoGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBNovoGerenciamentoCActionPerformed
        String catNome = JTFNomeDeCategoria.getText().trim();
        String catNomeNormalizado = removerAcentos(catNome).toUpperCase();

        String catTamanhoString = removerAcentos(JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString());
        Tamanho catTamanho = Tamanho.valueOf(catTamanhoString.toUpperCase());

        String catEmbalagemString = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString());
        Embalagem catEmbalagem = Embalagem.valueOf(catEmbalagemString.toUpperCase());

        Categoria cat = new Categoria(null, catNome, catTamanho, catEmbalagem);

        // Verificar se já existe uma categoria com o mesmo nome (ignorando acentos e caixa)
        boolean jaExiste = false;
        for (Categoria c : categoriaDao.resgatarCategorias()) {
            String nomeExistenteNormalizado = removerAcentos(c.getNome()).toUpperCase();
            if (nomeExistenteNormalizado.equals(catNomeNormalizado)) {
                jaExiste = true;
                break;
            }
        }

        if (jaExiste) {
            JOptionPane.showMessageDialog(this,
                    "Já existe uma categoria com este nome!",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            categoriaDao.cadastrarCategoria(cat);
            this.JTFNomeDeCategoria.setText("");
            carregarCategoriasNaTela();
        }


    }//GEN-LAST:event_JBNovoGerenciamentoCActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carregarCategoriasNaTela();
    }//GEN-LAST:event_formWindowOpened

    private void JBVoltarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarCategoriaActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarCategoriaActionPerformed

    private void JBAlterarGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAlterarGerenciamentoCActionPerformed
        // Alterar categoria de acordo com a linha selecionada da JTInformacoesProduto
        // linha selecionada
        int linhaSelecionada = JTableCategoria.getSelectedRow();

        // Pega os valores da linha selecionada
        int id = (Integer) JTableCategoria.getValueAt(linhaSelecionada, 0);
        //System.out.println("Id: " + id);
        String nome = (String) JTableCategoria.getValueAt(linhaSelecionada, 1);
        //System.out.println("nome: " + nome);
        String tamanhoStr = (String) JTableCategoria.getValueAt(linhaSelecionada, 2);
        //System.out.println("Tamanho: " + tamanhoStr);
        Tamanho tamanho = Tamanho.valueOf(tamanhoStr);
        String embalagemStr = (String) JTableCategoria.getValueAt(linhaSelecionada, 3);
        //System.out.println("Embalagem" + embalagemStr);
        Embalagem embalagem = Embalagem.valueOf(embalagemStr);

        // Instanciar categoria
        Categoria cat = new Categoria(id, nome, tamanho, embalagem);

        // Atualizar categoria no banco de dados
        categoriaDao.atualizarCategoria(cat);

        System.out.println("Botão Atualizar clicado");


    }//GEN-LAST:event_JBAlterarGerenciamentoCActionPerformed

    private void JTableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableCategoriaMouseClicked
        if (this.JTableCategoria.getSelectedRow() != -1) {
            String nome = this.JTableCategoria.getValueAt(this.JTableCategoria.getSelectedRow(), 1).toString();
            String tamanho = this.JTableCategoria.getValueAt(this.JTableCategoria.getSelectedRow(), 2).toString();
            String embalagem = this.JTableCategoria.getValueAt(this.JTableCategoria.getSelectedRow(), 3).toString();

            JTFNomeDeCategoria.setText(nome);

            // ComboBox de Tamanho
            String tamanhoTabela = removerAcentos(tamanho).toLowerCase();
            for (int i = 0; i < JCBTipoTamanhoGerenciamentoC.getItemCount(); i++) {
                String itemCombo = removerAcentos(JCBTipoTamanhoGerenciamentoC.getItemAt(i).toString()).toLowerCase();
                if (itemCombo.equals(tamanhoTabela)) {
                    JCBTipoTamanhoGerenciamentoC.setSelectedIndex(i);
                    break;
                }
            }

            // ComboBox de Embalagem
            String embalagemTabela = removerAcentos(embalagem).toLowerCase();
            for (int i = 0; i < JCBTipoEmbalagemGerenciamentoC.getItemCount(); i++) {
                String itemCombo = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getItemAt(i).toString()).toLowerCase();
                if (itemCombo.equals(embalagemTabela)) {
                    JCBTipoEmbalagemGerenciamentoC.setSelectedIndex(i);
                    break;
                }
            }
        }
    }//GEN-LAST:event_JTableCategoriaMouseClicked

    private void JBExcluirGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBExcluirGerenciamentoCActionPerformed
        int linhaSelecionada = JTableCategoria.getSelectedRow();

        if (linhaSelecionada == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }

        int idParaDeletarProduto = (int) tabela.getValueAt(linhaSelecionada, 0);

        try {
            categoriaDao.deletarCategoriaPorId(idParaDeletarProduto);
            carregarCategoriasNaTela();
            this.JTFNomeDeCategoria.setText("");

        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }//GEN-LAST:event_JBExcluirGerenciamentoCActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBAlterarGerenciamentoC;
    private javax.swing.JButton JBExcluirGerenciamentoC;
    private javax.swing.JButton JBNovoGerenciamentoC;
    private javax.swing.JButton JBVoltarCategoria;
    private javax.swing.JComboBox<String> JCBTipoEmbalagemGerenciamentoC;
    private javax.swing.JComboBox<String> JCBTipoTamanhoGerenciamentoC;
    private javax.swing.JLabel JLGerenciamentoProdutos;
    private javax.swing.JLabel JLNomeGerenciamentoC;
    private javax.swing.JTextField JTFNomeDeCategoria;
    private javax.swing.JTable JTableCategoria;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
