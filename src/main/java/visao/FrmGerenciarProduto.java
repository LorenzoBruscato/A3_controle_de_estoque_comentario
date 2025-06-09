package visao;

import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Produto;
import modelo.Registro;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import modelo.dao.RegistroDao;
import modelo.dao.db.DbException;

/**
 * Classe que representa a interface gráfica para gerenciamento de produtos.
 * Permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar) em
 * produtos, além de registrar movimentações de entrada e saída no estoque.
 *
 * @author Victor
 */
public class FrmGerenciarProduto extends javax.swing.JFrame {

    /**
     * Registro de movimentação do estoque.
     */
    private Registro registro;

    /**
     * DAO para operações com registros de movimentação.
     */
    private RegistroDao registroDao;

    /**
     * DAO para operações com categorias.
     */
    private CategoriaDao categoriaDao;

    /**
     * DAO para operações com produtos.
     */
    private ProdutoDao produtoDao;

    /**
     * Modelo de tabela para exibição dos produtos.
     */
    private DefaultTableModel tabela;

    /**
     * Dados iniciais da tabela (vazios).
     */
    private Object[][] dados = new Object[0][0];

    /**
     * Nomes das colunas da tabela de produtos. Ordem: ID, Nome, Preço, Unidade,
     * Qtd Estoque, Qtd Minima, Qtd Máxima, Categoria.
     */
    private String[] colunas = {"ID", "Nome", "Preço", "Unidade", "Qtd Estoque", "Qtd Mínima", "Qtd Máxima", "Categoria"};

    /**
     * Factory para criação dos DAOs necessários.
     */
    private DaoFactory daoFactory = new DaoFactory();

    /**
     * Constrói a janela de gerenciamento de produtos. Inicializa os componentes
     * da interface, configura os DAOs, carrega as categorias no ComboBox e
     * preenche a tabela de produtos.
     */
    public FrmGerenciarProduto() {
        initComponents();
        registroDao = daoFactory.insinstanciarRegistro();
        categoriaDao = daoFactory.instanciarCategoriaDao();
        produtoDao = daoFactory.instanciarProdutoDao();
        carregarCategoriasNoComboBox();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        tabela = new DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID não pode ser editado
            }
        };
        JTableProdutos.setModel(tabela);
        carregarProdutosNaTela();
    }

    /**
     * Carrega todas as categorias existentes no ComboBox de seleção.
     */
    private void carregarCategoriasNoComboBox() {
        ComboBoxCategoria.removeAllItems();

        List<Categoria> categorias = categoriaDao.resgatarCategorias();
        for (Categoria cat : categorias) {
            ComboBoxCategoria.addItem(cat.getNome()); // Adiciona o nome da categoria
        }
    }

    /**
     * Carrega todos os produtos do banco de dados na tabela de exibição.
     */
    private void carregarProdutosNaTela() {
        tabela.setRowCount(0);
        List<Produto> produtos = produtoDao.resgatarProdutos();
        for (Produto p : produtos) {
            tabela.addRow(new Object[]{
                p.getId(),
                p.getNome(),
                p.getPreco(),
                p.getUnidade(),
                p.getQuantidade(),
                p.getQuantidadeMinima(),
                p.getQuantidadeMaxima(),
                p.getCategoria().getNome()
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

        jScrollPane1 = new javax.swing.JScrollPane();
        JTableProdutos = new javax.swing.JTable();
        JLNome = new javax.swing.JLabel();
        JLPrecoUnitario = new javax.swing.JLabel();
        JLUnidade = new javax.swing.JLabel();
        JLQtdEstoque = new javax.swing.JLabel();
        JLQtdMinima = new javax.swing.JLabel();
        JLQtdMaxima = new javax.swing.JLabel();
        JLGerenciamentoProdutos = new javax.swing.JLabel();
        JTFQtdEstoque = new javax.swing.JTextField();
        JTFQtdMinima = new javax.swing.JTextField();
        JTFQtdMaxima = new javax.swing.JTextField();
        JBNovoProduto = new javax.swing.JButton();
        JBAlterarProduto = new javax.swing.JButton();
        JBVoltarProduto = new javax.swing.JButton();
        ComboBoxCategoria = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        JBExcluirProduto = new javax.swing.JButton();
        jBEntrada = new javax.swing.JButton();
        jBSaida = new javax.swing.JButton();
        jTEntradaSaida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        JTFNomeProduto = new javax.swing.JTextField();
        JTFPrecoUnitario = new javax.swing.JTextField();
        ComboBoxUnidade = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Gerenciar Produtos");

        JTableProdutos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Nome", "Preço", "Quantidade", "QtdMinima", "QtdMaxima", "Categoria"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JTableProdutos.setColumnSelectionAllowed(true);
        JTableProdutos.getTableHeader().setReorderingAllowed(false);
        JTableProdutos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTableProdutosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(JTableProdutos);
        JTableProdutos.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JLNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLNome.setText("Nome");

        JLPrecoUnitario.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLPrecoUnitario.setText("Preço Unitário");

        JLUnidade.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLUnidade.setText("Unidade");

        JLQtdEstoque.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLQtdEstoque.setText("Qtd. em Estoque");

        JLQtdMinima.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLQtdMinima.setText("Qtd. Mínima");

        JLQtdMaxima.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JLQtdMaxima.setText("Qtd. Máxima");

        JLGerenciamentoProdutos.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        JLGerenciamentoProdutos.setText("Gerenciamento de Produtos");

        JBNovoProduto.setText("Novo");
        JBNovoProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBNovoProdutoActionPerformed(evt);
            }
        });

        JBAlterarProduto.setText("Alterar");
        JBAlterarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBAlterarProdutoActionPerformed(evt);
            }
        });

        JBVoltarProduto.setText("Voltar");
        JBVoltarProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBVoltarProdutoActionPerformed(evt);
            }
        });

        ComboBoxCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setText("Categoria:");

        JBExcluirProduto.setText("Excluir");
        JBExcluirProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBExcluirProdutoActionPerformed(evt);
            }
        });

        jBEntrada.setText("Entrada");
        jBEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBEntradaActionPerformed(evt);
            }
        });

        jBSaida.setText("Saida");
        jBSaida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBSaidaActionPerformed(evt);
            }
        });

        jLabel2.setText("Adição e subtração de produto");

        JTFNomeProduto.setVerifyInputWhenFocusTarget(false);

        ComboBoxUnidade.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "KG", "g", "L", "ml" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JLGerenciamentoProdutos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JLPrecoUnitario)
                    .addComponent(JLNome)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(JLUnidade)
                            .addComponent(JLQtdMinima)
                            .addComponent(JLQtdMaxima)
                            .addComponent(JLQtdEstoque))
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(JTFQtdEstoque, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTFQtdMinima, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JTFQtdMaxima)
                    .addComponent(ComboBoxCategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(JTFNomeProduto)
                    .addComponent(JTFPrecoUnitario, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ComboBoxUnidade, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jTEntradaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jBEntrada, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                            .addComponent(jBSaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(32, 32, 32))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(JBNovoProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(JBAlterarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(JBExcluirProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(JBVoltarProduto, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {JLNome, JLPrecoUnitario, JLQtdEstoque, JLQtdMaxima, JLQtdMinima, JLUnidade});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JLGerenciamentoProdutos)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JLNome)
                            .addComponent(JTFNomeProduto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JLPrecoUnitario)
                                    .addComponent(JTFPrecoUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(JLUnidade)
                                    .addComponent(ComboBoxUnidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jTEntradaSaida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JTFQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JLQtdEstoque)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBEntrada)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jBSaida)))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLQtdMinima))
                .addGap(5, 5, 5)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JTFQtdMaxima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JLQtdMaxima))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ComboBoxCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBNovoProduto)
                    .addComponent(JBAlterarProduto)
                    .addComponent(JBExcluirProduto)
                    .addComponent(JBVoltarProduto))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    private void JBVoltarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarProdutoActionPerformed
        // TODO add your handling code here:
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarProdutoActionPerformed

    /**
     * Manipulador de evento para o botão Novo Produto. Cadastra um novo produto
     * com os dados informados nos campos.
     *
     * @param evt Evento de ação do botão
     */
    private void JBNovoProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBNovoProdutoActionPerformed
        try {
            String proNome = JTFNomeProduto.getText().trim();
            String proPreco = JTFPrecoUnitario.getText().trim().replace(",", ".");
            String nomeUnidade = ComboBoxUnidade.getSelectedItem().toString();
            String proQtdEstoque = JTFQtdEstoque.getText().trim();
            String proQtdMIN = JTFQtdMinima.getText().trim();
            String proQtdMAX = JTFQtdMaxima.getText().trim();

            double preco = Double.parseDouble(proPreco);
            int qtdEstoque = Integer.parseInt(proQtdEstoque);
            int qtdMinima = Integer.parseInt(proQtdMIN);
            int qtdMaxima = Integer.parseInt(proQtdMAX);

            if (preco < 0 || qtdEstoque < 0 || qtdMinima < 0 || qtdMaxima < 0) {
                JOptionPane.showMessageDialog(this, "Nenhum valor pode ser negativo!\nErro de cadastro.", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomeCategoria = (String) ComboBoxCategoria.getSelectedItem();
            Categoria categoriaExistente = categoriaDao.CategoriabuscarPorNome(nomeCategoria);

            Produto produtoExistente = produtoDao.procurarProdutoPorNomeCategoriaUnidade(proNome, categoriaExistente, nomeUnidade);

            if (produtoExistente != null) {
                JOptionPane.showMessageDialog(this,
                        "Já existe um produto com esse nome, categoria e unidade.\nModifique um dos campos.",
                        "Erro",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            if (qtdMinima > qtdMaxima) {
                JOptionPane.showMessageDialog(this, "A quantidade mínima não pode ser maior que a quantidade máxima.", "Erro", JOptionPane.WARNING_MESSAGE);
                return;
            }

            Produto pro = new Produto();
            pro.setNome(proNome);
            pro.setPreco(preco);
            pro.setUnidade(nomeUnidade);
            pro.setQuantidade(qtdEstoque);
            pro.setQuantidadeMinima(qtdMinima);
            pro.setQuantidadeMaxima(qtdMaxima);
            pro.setCategoria(categoriaExistente);

            Registro registro = new Registro();
            registro.setData(new java.util.Date());
            registro.setTipoDoProduto(pro);
            registro.setQuantidade(qtdEstoque);
            registro.setMovimentacao(Registro.Movimentacao.ENTRADA);
            registro.setStatus(Registro.Status.ADICIONADO);

            produtoDao.cadastrarProduto(pro);
            registroDao.AdicionarProdutoRegistro(registro);

            carregarProdutosNaTela();

            JTFNomeProduto.setText("");
            JTFPrecoUnitario.setText("");
            JTFQtdEstoque.setText("");
            JTFQtdMinima.setText("");
            JTFQtdMaxima.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Erro ao converter número. Verifique os campos numéricos.",
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }//GEN-LAST:event_JBNovoProdutoActionPerformed

    /**
     * Manipulador de evento para clique na tabela de produtos. Carrega os dados
     * do produto selecionado nos campos de edição.
     *
     * @param evt Evento de clique do mouse
     */
    private void JTableProdutosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTableProdutosMouseClicked
        int linhaSelecionada = JTableProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            JTFNomeProduto.setText(tabela.getValueAt(linhaSelecionada, 1).toString());
            JTFPrecoUnitario.setText(tabela.getValueAt(linhaSelecionada, 2).toString());

            String unidadeUnidade = tabela.getValueAt(linhaSelecionada, 3).toString();
            for (int i = 0; i < ComboBoxUnidade.getItemCount(); i++) {
                if (ComboBoxUnidade.getItemAt(i).equals(unidadeUnidade));
                {
                    ComboBoxUnidade.setSelectedIndex(i);
                    break;
                }
            }
            JTFQtdEstoque.setText(tabela.getValueAt(linhaSelecionada, 4).toString());
            JTFQtdMinima.setText(tabela.getValueAt(linhaSelecionada, 5).toString());
            JTFQtdMaxima.setText(tabela.getValueAt(linhaSelecionada, 6).toString());

            String categoriaNome = tabela.getValueAt(linhaSelecionada, 7).toString();
            for (int i = 0; i < ComboBoxCategoria.getItemCount(); i++) {
                if (ComboBoxCategoria.getItemAt(i).equals(categoriaNome)) {
                    ComboBoxCategoria.setSelectedIndex(i);
                    break;
                }

            }
        }
    }//GEN-LAST:event_JTableProdutosMouseClicked

    /**
     * Manipulador de evento para o botão Alterar Produto. Atualiza os dados do
     * produto selecionado na tabela.
     *
     * @param evt Evento de ação do botão
     */
    private void JBAlterarProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAlterarProdutoActionPerformed
        // Alterar produto de acordo com a linha selecionada da tabela
        int linhaSelecionada = JTableProdutos.getSelectedRow();
        if (linhaSelecionada != -1) {
            try {
                // Pega os valores da linha selecionada da tabela
                int id = (Integer) JTableProdutos.getValueAt(linhaSelecionada, 0);
                Produto produtoAtual = produtoDao.procurarProdutoPorId(id);
                int qtdEstoqueAntiga = produtoAtual.getQuantidade();
                int qtdMinimaAntiga = produtoAtual.getQuantidadeMinima();
                int qtdMaximaAntiga = produtoAtual.getQuantidadeMaxima();
                String nomeAntigo = produtoAtual.getNome();
                Categoria categoriaAntiga = produtoAtual.getCategoria();

                String nome = JTFNomeProduto.getText().trim();
                double preco = Double.parseDouble(JTFPrecoUnitario.getText().trim().replace(",", "."));
                String unidade = ComboBoxUnidade.getSelectedItem().toString();
                int qtdEstoque = Integer.parseInt(JTFQtdEstoque.getText().trim());
                int qtdMinima = Integer.parseInt(JTFQtdMinima.getText().trim());
                int qtdMaxima = Integer.parseInt(JTFQtdMaxima.getText().trim());
                String nomeCategoria = (String) ComboBoxCategoria.getSelectedItem();
                Categoria categoria = categoriaDao.CategoriabuscarPorNome(nomeCategoria);

                if (categoria == null) {
                    throw new DbException("Categoria não encontrada: " + nomeCategoria);
                }

                if (preco < 0 || qtdMinima < 0 || qtdMaxima < 0) {
                    JOptionPane.showMessageDialog(this,
                            "Nenhum valor pode ser negativo!\nErro de cadastro.",
                            "Erro",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }

                if (!nome.equalsIgnoreCase(nomeAntigo)
                        || !categoria.equals(categoriaAntiga)
                        || !unidade.equalsIgnoreCase(produtoAtual.getUnidade())) {

                    Produto produtoExistente = produtoDao.procurarProdutoPorNomeCategoriaUnidade(nome, categoria, unidade);

                    // Se encontrou um produto com mesmo nome, categoria e unidade — e não é o próprio produto sendo editado
                    if (produtoExistente != null && produtoExistente.getId() != produtoAtual.getId()) {
                        JOptionPane.showMessageDialog(this,
                                "Já existe um produto com esse nome, categoria e unidade.\nModifique um dos campos.",
                                "Erro",
                                JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                }

                if (qtdMinima > qtdMaxima) {
                    JOptionPane.showMessageDialog(this, "A quantidade mínima não pode ser maior que a quantidade máxima.", "Erro", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // Criar e preencher o objeto Produto
                Produto produto = new Produto();
                produto.setId(id);
                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setUnidade(unidade);
                produto.setQuantidade(qtdEstoque);
                produto.setQuantidadeMinima(qtdMinima);
                produto.setQuantidadeMaxima(qtdMaxima);
                produto.setCategoria(categoria);

                Registro reg = new Registro();
                reg.setId(id);
                reg.setData(new Date());
                reg.setTipoDoProduto(produto);

                // Determinar tipo de movimentação (entrada, saída ou nenhum)
                if (qtdEstoque > qtdEstoqueAntiga) {
                    reg.setMovimentacao(Registro.Movimentacao.ENTRADA);
                    reg.setQuantidade(qtdEstoque - qtdEstoqueAntiga);
                } else if (qtdEstoque < qtdEstoqueAntiga) {
                    reg.setMovimentacao(Registro.Movimentacao.SAIDA);
                    reg.setQuantidade(qtdEstoqueAntiga - qtdEstoque);
                } else {
                    reg.setMovimentacao(Registro.Movimentacao.NENHUM);
                    reg.setQuantidade(0);
                }

                // Determinar status
                if (!categoriaAntiga.getNome().equals(categoria.getNome())
                        && nome.equals(nomeAntigo)
                        && qtdEstoque == qtdEstoqueAntiga
                        && qtdMinima == qtdMinimaAntiga
                        && qtdMaxima == qtdMaximaAntiga) {
                    reg.setStatus(Registro.Status.ALCATEGORIA);
                } else if (qtdEstoque < qtdMinima) {
                    reg.setStatus(Registro.Status.ABAIXO);
                } else if (qtdEstoque > qtdMaxima) {
                    reg.setStatus(Registro.Status.ACIMA);
                } else if (!nome.equals(nomeAntigo)) {
                    reg.setStatus(Registro.Status.NOMEALTERADO);
                } else if (qtdMinima != qtdMinimaAntiga && qtdMaxima != qtdMaximaAntiga) {
                    reg.setStatus(Registro.Status.ALQMAEMI);
                } else if (qtdMinima != qtdMinimaAntiga) {
                    reg.setStatus(Registro.Status.ALQTDMI);
                } else if (qtdMaxima != qtdMaximaAntiga) {
                    reg.setStatus(Registro.Status.ALQTMAX);
                } else if (qtdEstoque >= qtdMinima && qtdEstoque <= qtdMaxima) {
                    reg.setStatus(Registro.Status.DENTRO);
                } else {
                    reg.setStatus(Registro.Status.NENHUM);
                }

                // Atualizar produto no banco de dados
                registroDao.AtualizarProdutoRegistro(reg);
                produtoDao.atualizarProduto(produto);
                System.out.println("Produto atualizado com sucesso!");

                // Recarregar a tabela
                carregarProdutosNaTela();

                // Limpar os campos
                JTFNomeProduto.setText("");
                JTFPrecoUnitario.setText("");
                JTFQtdEstoque.setText("");
                JTFQtdMinima.setText("");
                JTFQtdMaxima.setText("");

            } catch (NumberFormatException e) {
                throw new DbException("Erro ao converter número: " + e.getMessage());
            } catch (Exception e) {
                throw new DbException("Erro ao atualizar produto: " + e.getMessage());
            }
        }
    }//GEN-LAST:event_JBAlterarProdutoActionPerformed

    /**
     * Manipulador de evento para o botão Excluir Produto. Remove o produto
     * selecionado da tabela e do banco de dados.
     *
     * @param evt Evento de ação do botão
     */
    private void JBExcluirProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBExcluirProdutoActionPerformed
        int linhaSelecionada = JTableProdutos.getSelectedRow();

        if (linhaSelecionada == -1) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para excluir.");
            return;
        }

        int idParaDeletarProduto = (int) tabela.getValueAt(linhaSelecionada, 0);

        try {
            // Obtemos o produto antes de excluir para registrar a exclusão
            Produto produto = produtoDao.procurarProdutoPorId(idParaDeletarProduto);
            if (produto == null) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.");
                return;
            }

            // Exclui o produto e registra a movimentação como EXCLUIDO
            produtoDao.deletarProdutoPorId(idParaDeletarProduto);

            // Atualiza a tabela
            carregarProdutosNaTela();

            // Limpa os campos
            JTFNomeProduto.setText("");
            JTFPrecoUnitario.setText("");
            JTFQtdEstoque.setText("");
            JTFQtdMinima.setText("");
            JTFQtdMaxima.setText("");

            JOptionPane.showMessageDialog(this, "Produto excluído com sucesso.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao excluir produto: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_JBExcluirProdutoActionPerformed

    /**
     * Manipulador de evento para o botão Entrada. Registra uma entrada de
     * quantidade no estoque para o produto selecionado.
     *
     * @param evt Evento de ação do botão
     */
    private void jBEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBEntradaActionPerformed
        try {
            int linhaSelecionada = JTableProdutos.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto na tabela antes de registrar a entrada.");
                return;
            }

            int id = (int) JTableProdutos.getValueAt(linhaSelecionada, 0);
            Produto produtoAtual = produtoDao.procurarProdutoPorId(id);

            int entrada = Integer.parseInt(jTEntradaSaida.getText().trim());
            if (entrada <= 0) {
                JOptionPane.showMessageDialog(this,
                        "A quantidade de entrada deve ser maior que zero.",
                        "Entrada inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int qtdEstoqueNova = produtoAtual.getQuantidade() + entrada;

            // Atualiza apenas o campo de quantidade
            produtoAtual.setQuantidade(qtdEstoqueNova);

            // Prepara o registro da movimentação
            Registro reg = new Registro();
            reg.setId(id);
            reg.setData(new Date());
            reg.setTipoDoProduto(produtoAtual);
            reg.setMovimentacao(Registro.Movimentacao.ENTRADA);
            reg.setQuantidade(entrada);

            // Define o status com base nos limites
            if (qtdEstoqueNova < produtoAtual.getQuantidadeMinima()) {
                reg.setStatus(Registro.Status.ABAIXO);
            } else if (qtdEstoqueNova > produtoAtual.getQuantidadeMaxima()) {
                reg.setStatus(Registro.Status.ACIMA);
            } else {
                reg.setStatus(Registro.Status.DENTRO);
            }

            // Atualiza o produto no banco
            registroDao.AtualizarProdutoRegistro(reg);
            produtoDao.atualizarProduto(produtoAtual);

            JOptionPane.showMessageDialog(this, "Entrada registrada com sucesso!");
            carregarProdutosNaTela();

            // Limpar os campos
            jTEntradaSaida.setText("");
            JTFNomeProduto.setText("");
            JTFPrecoUnitario.setText("");
            JTFQtdEstoque.setText("");
            JTFQtdMinima.setText("");
            JTFQtdMaxima.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Digite um número válido para a quantidade de entrada.",
                    "Erro de entrada",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new DbException("Erro ao registrar entrada: " + e.getMessage());
        }
    }//GEN-LAST:event_jBEntradaActionPerformed

    /**
     * Manipulador de evento para o botão Saída. Registra uma saída de
     * quantidade no estoque para o produto selecionado.
     *
     * @param evt Evento de ação do botão
     */
    private void jBSaidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBSaidaActionPerformed
        try {
            int linhaSelecionada = JTableProdutos.getSelectedRow();
            if (linhaSelecionada == -1) {
                JOptionPane.showMessageDialog(this, "Selecione um produto na tabela antes de registrar a saída.");
                return;
            }

            int id = (int) JTableProdutos.getValueAt(linhaSelecionada, 0);
            Produto produtoAtual = produtoDao.procurarProdutoPorId(id);

            int qtdEstoqueAntiga = produtoAtual.getQuantidade();
            int saida = Integer.parseInt(jTEntradaSaida.getText().trim());

            if (saida <= 0) {
                JOptionPane.showMessageDialog(this,
                        "A quantidade de saída deve ser maior que zero.",
                        "Saída inválida",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }

            int qtdEstoqueNova = qtdEstoqueAntiga - saida;

            if (qtdEstoqueNova < 0) {
                int opcao = JOptionPane.showConfirmDialog(
                        this,
                        "Atenção: o estoque ficará negativo!\nDeseja continuar mesmo assim?",
                        "Aviso",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

                if (opcao != JOptionPane.YES_OPTION) {
                    return; // Cancela a operação
                }
            }

            // Atualiza apenas a quantidade do produto
            produtoAtual.setQuantidade(qtdEstoqueNova);

            // Criar registro da saída
            Registro reg = new Registro();
            reg.setId(id);
            reg.setData(new Date());
            reg.setTipoDoProduto(produtoAtual);
            reg.setMovimentacao(Registro.Movimentacao.SAIDA);
            reg.setQuantidade(saida);

            // Define status com base nos limites
            if (qtdEstoqueNova < produtoAtual.getQuantidadeMinima()) {
                reg.setStatus(Registro.Status.ABAIXO);
            } else if (qtdEstoqueNova > produtoAtual.getQuantidadeMaxima()) {
                reg.setStatus(Registro.Status.ACIMA);
            } else {
                reg.setStatus(Registro.Status.DENTRO);
            }

            registroDao.AtualizarProdutoRegistro(reg);
            produtoDao.atualizarProduto(produtoAtual);

            JOptionPane.showMessageDialog(this, "Saída registrada com sucesso!");

            carregarProdutosNaTela();

            // Limpar os campos
            jTEntradaSaida.setText("");
            JTFNomeProduto.setText("");
            JTFPrecoUnitario.setText("");
            JTFQtdEstoque.setText("");
            JTFQtdMinima.setText("");
            JTFQtdMaxima.setText("");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this,
                    "Digite um número válido para a quantidade de saída.",
                    "Erro de saída",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            throw new DbException("Erro ao registrar saída: " + e.getMessage());
        }

    }//GEN-LAST:event_jBSaidaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBoxCategoria;
    private javax.swing.JComboBox<String> ComboBoxUnidade;
    private javax.swing.JButton JBAlterarProduto;
    private javax.swing.JButton JBExcluirProduto;
    private javax.swing.JButton JBNovoProduto;
    private javax.swing.JButton JBVoltarProduto;
    private javax.swing.JLabel JLGerenciamentoProdutos;
    private javax.swing.JLabel JLNome;
    private javax.swing.JLabel JLPrecoUnitario;
    private javax.swing.JLabel JLQtdEstoque;
    private javax.swing.JLabel JLQtdMaxima;
    private javax.swing.JLabel JLQtdMinima;
    private javax.swing.JLabel JLUnidade;
    private javax.swing.JTextField JTFNomeProduto;
    private javax.swing.JTextField JTFPrecoUnitario;
    private javax.swing.JTextField JTFQtdEstoque;
    private javax.swing.JTextField JTFQtdMaxima;
    private javax.swing.JTextField JTFQtdMinima;
    private javax.swing.JTable JTableProdutos;
    private javax.swing.JButton jBEntrada;
    private javax.swing.JButton jBSaida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTEntradaSaida;
    // End of variables declaration//GEN-END:variables
}
