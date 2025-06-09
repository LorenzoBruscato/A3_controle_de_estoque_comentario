package visao;

import java.util.List;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import modelo.Registro;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import modelo.dao.RegistroDao;

/**
 * Classe que representa a janela de movimentação de estoque do sistema. Exibe
 * um histórico completo de todas as entradas, saídas e alterações de produtos
 * no estoque em formato tabular.
 *
 * @author Victor
 */
public class FrmMovimentacao extends javax.swing.JFrame {

    /**
     * DAO para operações com produtos.
     */
    private ProdutoDao produtoDao;

    /**
     * DAO para operações com registros de movimentação.
     */
    private RegistroDao registroDao;

    /**
     * Modelo de tabela para exibição dos registros de movimentação.
     */
    private DefaultTableModel tabela;

    /**
     * Modelo de tabela para exibição dos registros de movimentação.
     */
    private Object[][] dados = new Object[0][0];

    /**
     * Nomes das colunas da tabela de movimentação. Ordem das colunas: ID, Data,
     * Tipo, Quantidade, Movimentação, Status Estoque.
     */
    private String[] colunas = {"ID", "Data", "Tipo", "Quntidade", "Movimentação", "Status Estoque"};

    /**
     * Factory para criação dos DAOs necessários.
     */
    private DaoFactory daoFactory = new DaoFactory();

    /**
     * Constrói a janela de movimentação de estoque. Inicializa os componentes
     * da interface, configura os DAOs e carrega os registros de movimentação na
     * tabela.
     */
    public FrmMovimentacao() {
        initComponents();
        registroDao = daoFactory.insinstanciarRegistro();
        produtoDao = daoFactory.instanciarProdutoDao();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID não pode ser editado
            }
        };
        jTableRegistro.setModel(tabela);
        carregarRegistroNaTela();
    }

    /**
     * Carrega os registros de movimentação do banco de dados e os exibe na
     * tabela. Atualiza a interface com todos os registros existentes,
     * formatando adequadamente os dados para exibição.
     */
    private void carregarRegistroNaTela() {
        tabela.setRowCount(0);

        List<Registro> registros = registroDao.resgatarRegistros();

        for (Registro reg : registros) {
            tabela.addRow(new Object[]{
                reg.getId(),
                reg.getData(),
                reg.getTipoDoProduto().getNome(),
                reg.getQuantidade(),
                reg.getMovimentacao(),
                reg.getStatus() != null ? reg.getStatus().name() : "N/A"
            });
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

        JLGerenciamentoProdutos = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableRegistro = new javax.swing.JTable();
        JBVoltarMovimentacao = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Movimentação de Estoque");
        setAlwaysOnTop(true);

        JLGerenciamentoProdutos.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        JLGerenciamentoProdutos.setText("Movimentação de Estoque");

        jTableRegistro.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jTableRegistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Nome", "ID", "Tipo", "Qtd", "Data", "Movimentação", "Status Estoque"
            }
        ));
        jTableRegistro.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableRegistro);

        JBVoltarMovimentacao.setText("Voltar");
        JBVoltarMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarMovimentacaoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(240, 240, 240)
                        .addComponent(JLGerenciamentoProdutos))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(323, 323, 323)
                        .addComponent(JBVoltarMovimentacao)))
                .addContainerGap(63, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(JLGerenciamentoProdutos)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(JBVoltarMovimentacao)
                .addContainerGap(45, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manipulador de evento para o botão Voltar. Fecha a janela atual e retorna
     * ao menu principal do sistema.
     *
     * @param evt Evento de ação do botão
     */
    private void JBVoltarMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarMovimentacaoActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarMovimentacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBVoltarMovimentacao;
    private javax.swing.JLabel JLGerenciamentoProdutos;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableRegistro;
    // End of variables declaration//GEN-END:variables
}
