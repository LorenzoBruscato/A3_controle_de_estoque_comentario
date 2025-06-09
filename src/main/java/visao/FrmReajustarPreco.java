package visao;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import modelo.Categoria;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import modelo.dao.db.DbException;

/**
 * Classe que representa a interface gráfica para reajuste de preços de
 * produtos. Permite aumentar ou diminuir preços de todos os produtos ou por
 * categoria específica, com um percentual definido pelo usuário.
 *
 * @author Victor
 */
public class FrmReajustarPreco extends javax.swing.JFrame {

    /**
     * DAO para operações com categorias.
     */
    private final CategoriaDao categoriaDao;

    /**
     * DAO para operações com produtos.
     */
    private ProdutoDao produtoDao;

    /**
     * Factory para criação dos DAOs necessários.
     */
    private DaoFactory daoFactory = new DaoFactory();

    /**
     * Constrói a janela de reajuste de preços. Inicializa os componentes da
     * interface, configura os DAOs e carrega as categorias disponíveis no
     * ComboBox.
     */
    public FrmReajustarPreco() {
        initComponents();
        categoriaDao = daoFactory.instanciarCategoriaDao();
        produtoDao = daoFactory.instanciarProdutoDao();
        carregarCategoriasNoComboBox();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Carrega todas as categorias existentes no ComboBox de seleção. Adiciona
     * também a opção "Todos Produtos" para reajuste geral.
     */
    private void carregarCategoriasNoComboBox() {
        ComboBoxReajuste.removeAllItems();
        ComboBoxReajuste.addItem("Todos Produtos");

        List<Categoria> categorias = categoriaDao.resgatarCategorias();
        for (Categoria cat : categorias) {
            ComboBoxReajuste.addItem(cat.getNome());
        }
    }

    /**
     * Método gerado automaticamente pelo NetBeans para inicialização dos
     * componentes. AVISO: Não modifique este código. O conteúdo deste método é
     * sempre regenerado pelo Editor de Formulários.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLAumentarPreço = new javax.swing.JLabel();
        JLReajuste = new javax.swing.JLabel();
        JBVoltar = new javax.swing.JButton();
        jBAumentarPreco = new javax.swing.JButton();
        jBDiminuirPreco = new javax.swing.JButton();
        ComboBoxReajuste = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        JTFAjustePorcentagem = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reajustar Preços");

        JLAumentarPreço.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        JLAumentarPreço.setText("Ajustar os preços em porcentagem");

        JLReajuste.setFont(new java.awt.Font("Arial", 0, 36)); // NOI18N
        JLReajuste.setText("Reajuste de Preços");

        JBVoltar.setText("Voltar");
        JBVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarActionPerformed(evt);
            }
        });

        jBAumentarPreco.setText("Aumentar");
        jBAumentarPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBAumentarPrecoActionPerformed(evt);
            }
        });

        jBDiminuirPreco.setText("Diminuir");
        jBDiminuirPreco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBDiminuirPrecoActionPerformed(evt);
            }
        });

        ComboBoxReajuste.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos Produtos", "Carne", "Frios" }));

        jLabel5.setText("%");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jBAumentarPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(jBDiminuirPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addComponent(JBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(ComboBoxReajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(JTFAjustePorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)))
                .addGap(0, 111, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JLReajuste)
                        .addGap(170, 170, 170))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(JLAumentarPreço)
                        .addGap(189, 189, 189))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(JLReajuste)
                .addGap(29, 29, 29)
                .addComponent(JLAumentarPreço)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ComboBoxReajuste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JTFAjustePorcentagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 96, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBVoltar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBAumentarPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBDiminuirPreco, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manipulador de evento para o botão Voltar. Fecha a janela atual e retorna
     * ao menu principal.
     *
     * @param evt Evento de ação do botão
     */
    private void JBVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarActionPerformed

    /**
     * Manipulador de evento para o botão Aumentar. Aplica um aumento percentual
     * nos preços dos produtos selecionados. Valida o valor informado antes de
     * realizar a operação.
     *
     * @param evt Evento de ação do botão
     */
    private void jBAumentarPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBAumentarPrecoActionPerformed
        try {
            double percentual = Double.parseDouble(JTFAjustePorcentagem.getText().replace(",", "."));
            if (percentual <= 0) {
                JTFAjustePorcentagem.setText("");
                JOptionPane.showMessageDialog(this, "Informe um valor maior que zero.");
                return;
            }

            String categoriaSelecionada = ComboBoxReajuste.getSelectedItem().toString();

            if (categoriaSelecionada.equalsIgnoreCase("Todos Produtos")) {
                produtoDao.aumentarTodosPrecos(percentual);
            } else {
                produtoDao.aumentarPrecoPorCategoria(percentual, categoriaSelecionada);
            }

            JOptionPane.showMessageDialog(this, "Preços aumentados  com sucesso!");

        } catch (NumberFormatException e) {
            JTFAjustePorcentagem.setText("");
            JOptionPane.showMessageDialog(this, "Informe um valor numérico válido para a porcentagem.");
        } catch (DbException e) {
            JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
     }//GEN-LAST:event_jBAumentarPrecoActionPerformed

    /**
     * Manipulador de evento para o botão Diminuir. Aplica uma redução
     * percentual nos preços dos produtos selecionados. Valida o valor informado
     * antes de realizar a operação.
     *
     * @param evt Evento de ação do botão
     */
    private void jBDiminuirPrecoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBDiminuirPrecoActionPerformed
        try {
            double percentual = Double.parseDouble(JTFAjustePorcentagem.getText().replace(",", "."));
            if (percentual <= 0) {
                JTFAjustePorcentagem.setText("");
                JOptionPane.showMessageDialog(this, "Informe um valor maior que zero.");
                return;
            }

            String categoriaSelecionada = ComboBoxReajuste.getSelectedItem().toString();

            if (categoriaSelecionada.equalsIgnoreCase("Todos Produtos")) {
                produtoDao.diminuirTodosPrecos(percentual);
            } else {
                produtoDao.diminuirPrecoPorCategoria(percentual, categoriaSelecionada);
            }

            JOptionPane.showMessageDialog(this, "Preços diminuídos com sucesso!");

        } catch (NumberFormatException e) {
            JTFAjustePorcentagem.setText("");
            JOptionPane.showMessageDialog(this, "Informe um valor numérico válido para a porcentagem.");
        } catch (DbException e) {
            JOptionPane.showMessageDialog(this, "Erro ao acessar o banco de dados: " + e.getMessage());
        }
    }//GEN-LAST:event_jBDiminuirPrecoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxReajuste;
    private javax.swing.JButton JBVoltar;
    private javax.swing.JLabel JLAumentarPreço;
    private javax.swing.JLabel JLReajuste;
    private javax.swing.JTextField JTFAjustePorcentagem;
    private javax.swing.JButton jBAumentarPreco;
    private javax.swing.JButton jBDiminuirPreco;
    private javax.swing.JLabel jLabel5;
    // End of variables declaration//GEN-END:variables
}
