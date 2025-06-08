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
import modelo.dao.ProdutoDao;
import modelo.dao.db.DbException;

/**
 *
 *
 */
public class FrmGerenciarCategoria extends javax.swing.JFrame {

    private DaoFactory daoFactory = new DaoFactory();
    private CategoriaDao categoriaDao;
    private ProdutoDao produtoDao;
    private DefaultTableModel tabela;
    private Object[][] dados = new Object[0][0];
    private String[] colunas = {"ID", "Nome", "Tamanho", "Embalagem"};

    /**
     *
     */
    public FrmGerenciarCategoria() {
        initComponents();
        categoriaDao = daoFactory.instanciarCategoriaDao();
        produtoDao = daoFactory.instanciarProdutoDao();
        tabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // coluna 0 (ID) não pode ser editada
            }
        };
        JTableCategoria.setModel(tabela);
        carregarCategoriasNaTela();
    }

    /**
     *
     * @param texto
     * @return
     */
    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    private void carregarCategoriasNaTela() {
        try {
            // Busca todas as categorias atualizadas do banco
            List<Categoria> categorias = categoriaDao.resgatarCategorias();

            DefaultTableModel modelo = (DefaultTableModel) JTableCategoria.getModel();

            modelo.setRowCount(0); // limpa todas as linhas da tabela

            // Adiciona as linhas atualizadas
            for (Categoria c : categorias) {
                modelo.addRow(new Object[]{
                    c.getId(),
                    c.getNome(),
                    c.getTamanho().toString(),
                    c.getEmbalagem().toString()
                });
            }
        } catch (Exception e) {
            System.err.println("Erro ao atualizar tabela de categorias:");
            e.printStackTrace();
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
        String catNomeNormalizado = removerAcentos(catNome).trim().toUpperCase();

        String catTamanhoString = removerAcentos(JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString());
        Tamanho catTamanho = Tamanho.valueOf(catTamanhoString.toUpperCase());

        String catEmbalagemString = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString());
        Embalagem catEmbalagem = Embalagem.valueOf(catEmbalagemString.toUpperCase());

        Categoria cat = new Categoria(null, catNome, catTamanho, catEmbalagem);

        boolean jaExiste = false;
        for (Categoria c : categoriaDao.resgatarCategorias()) {
            String nomeExistenteNormalizado = removerAcentos(c.getNome()).trim().toUpperCase();
            System.out.println("Comparando: [" + nomeExistenteNormalizado + "] com [" + catNomeNormalizado + "]");
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
        try {
            int linhaSelecionada = JTableCategoria.getSelectedRow();
            if (linhaSelecionada == -1) {
                System.out.println("Nenhuma linha selecionada para alterar.");
                return;
            }
            // Pega o id da linha selecionada
            int id = (Integer) JTableCategoria.getValueAt(linhaSelecionada, 0);
            // Pegamos o nome antigo da categoria diretamente da tabela
            String nomeAntigo = (String) JTableCategoria.getValueAt(linhaSelecionada, 1);
            // Pega o novo nome da categoria a partir do campo de texto
            String nomeNovo = JTFNomeDeCategoria.getText().trim();
            // Pega os enums a partir dos combos
            String tamanhoStr = removerAcentos(JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString());
            Tamanho tamanho = Tamanho.valueOf(tamanhoStr.toUpperCase());
            String embalagemStr = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString());
            Embalagem embalagem = Embalagem.valueOf(embalagemStr.toUpperCase());
            // Cria o objeto Categoria com os dados atualizados
            Categoria cat = new Categoria(id, nomeNovo, tamanho, embalagem);
            // Atualiza os produtos que tinham a categoria com o nome antigo
            produtoDao.atualizarProdutoCategoria(nomeNovo, nomeAntigo);
            // Atualiza a própria categoria na tabela de categorias
            categoriaDao.atualizarCategoria(cat);
            carregarCategoriasNaTela();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a categoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JBAlterarGerenciamentoCActionPerformed

    private void JTableCategoriaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableCategoriaMouseClicked
        int linhaSelecionada = JTableCategoria.getSelectedRow();

        if (linhaSelecionada != -1) {
            DefaultTableModel tabela = (DefaultTableModel) JTableCategoria.getModel();

            String nome = tabela.getValueAt(linhaSelecionada, 1).toString();
            String tamanhoSelecionado = removerAcentos(tabela.getValueAt(linhaSelecionada, 2).toString().trim());
            String embalagemSelecionada = removerAcentos(tabela.getValueAt(linhaSelecionada, 3).toString().trim());

            // Seta o nome no campo de texto
            JTFNomeDeCategoria.setText(nome);

            // Seta o tamanho no combo box
            for (int i = 0; i < JCBTipoTamanhoGerenciamentoC.getItemCount(); i++) {
                String item = removerAcentos(JCBTipoTamanhoGerenciamentoC.getItemAt(i).toString().trim());
                if (item.equalsIgnoreCase(tamanhoSelecionado)) {
                    JCBTipoTamanhoGerenciamentoC.setSelectedIndex(i);
                    break;
                }
            }

            // Seta a embalagem no combo box
            for (int i = 0; i < JCBTipoEmbalagemGerenciamentoC.getItemCount(); i++) {
                String item = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getItemAt(i).toString().trim());
                if (item.equalsIgnoreCase(embalagemSelecionada)) {
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
