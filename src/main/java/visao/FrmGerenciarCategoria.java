package visao;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Categoria;
import modelo.Categoria.Embalagem;
import modelo.Categoria.Tamanho;
import modelo.Produto;
import modelo.Registro;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import modelo.dao.RegistroDao;

/**
 * Classe que representa a interface gráfica para gerenciamento de categorias de
 * produtos. Permite realizar operações CRUD (Criar, Ler, Atualizar e Deletar)
 * em categorias, além de visualizar as categorias existentes em uma tabela.
 *
 * @author Victor
 */
public class FrmGerenciarCategoria extends javax.swing.JFrame {

    /**
     * Factory para criação dos DAOs necessários.
     */
    private DaoFactory daoFactory = new DaoFactory();

    /**
     * DAO para operações com registros de movimentação.
     */
    private RegistroDao resgistroDao;

    /**
     * DAO para operações com categorias.
     */
    private CategoriaDao categoriaDao;

    /**
     * DAO para operações com produtos.
     */
    private ProdutoDao produtoDao;

    /**
     * Modelo de tabela para exibição das categorias.
     */
    private DefaultTableModel tabela;

    /**
     * Dados iniciais da tabela (vazios).
     */
    private Object[][] dados = new Object[0][0];

    /**
     * Nomes das colunas da tabela.
     */
    private String[] colunas = {"ID", "Nome", "Tamanho", "Embalagem"};

    /**
     * Constrói a janela de gerenciamento de categorias. Inicializa os
     * componentes da interface e configura os DAOs necessários.
     */
    public FrmGerenciarCategoria() {
        initComponents();
        resgistroDao = daoFactory.insinstanciarRegistro();
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
     * Remove acentos e caracteres especiais de uma string.
     *
     * @param texto O texto a ser normalizado
     * @return O texto sem acentos e caracteres especiais
     */
    public static String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }

    /**
     * Carrega as categorias do banco de dados e as exibe na tabela.
     */
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
        }
    }

    /**
     * Manipulador de evento para o botão Novo. Cria uma nova categoria com os
     * dados informados nos campos.
     *
     * @param evt Evento de ação do botão
     */
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
        JCBTipoEmbalagemGerenciamentoC = new javax.swing.JComboBox<>();
        JTFNomeDeCategoria = new javax.swing.JTextField();
        JCBTipoTamanhoGerenciamentoC = new javax.swing.JComboBox<>();

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

        JCBTipoEmbalagemGerenciamentoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Lata", "Vidro", "Plástico" }));

        JCBTipoTamanhoGerenciamentoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pequeno", "Médio", "Grande" }));

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
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBNovoGerenciamentoC)
                                        .addGap(18, 18, 18)
                                        .addComponent(JBAlterarGerenciamentoC))
                                    .addComponent(JTFNomeDeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JBExcluirGerenciamentoC)
                                        .addGap(18, 18, 18)
                                        .addComponent(JBVoltarCategoria))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(JCBTipoTamanhoGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
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
                    .addComponent(JLNomeGerenciamentoC)
                    .addComponent(JTFNomeDeCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JCBTipoTamanhoGerenciamentoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manipulador de evento para o botão Novo. Cria uma nova categoria com os
     * dados informados nos campos.
     *
     * @param evt Evento de ação do botão
     */
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

    /**
     * Método chamado quando a janela é aberta. Carrega as categorias na tabela.
     *
     * @param evt Evento de abertura da janela
     */
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        carregarCategoriasNaTela();
    }//GEN-LAST:event_formWindowOpened

    /**
     * Manipulador de evento para o botão Voltar. Fecha a janela atual e retorna
     * ao menu principal.
     *
     * @param evt Evento de ação do botão
     */
    private void JBVoltarCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBVoltarCategoriaActionPerformed
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBVoltarCategoriaActionPerformed

    /**
     * Manipulador de evento para o botão Alterar. Atualiza os dados da
     * categoria selecionada na tabela.
     *
     * @param evt Evento de ação do botão
     */
    private void JBAlterarGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBAlterarGerenciamentoCActionPerformed
        try {
            int linhaSelecionada = JTableCategoria.getSelectedRow();
            if (linhaSelecionada == -1) {
                System.out.println("Nenhuma linha selecionada para alterar.");
                return;
            }

            // Pega dados da categoria selecionada
            int id = (Integer) JTableCategoria.getValueAt(linhaSelecionada, 0);
            String nomeAntigo = (String) JTableCategoria.getValueAt(linhaSelecionada, 1);
            String nomeNovo = JTFNomeDeCategoria.getText().trim();

            // Enum Tamanho e Embalagem
            String tamanhoStr = removerAcentos(JCBTipoTamanhoGerenciamentoC.getSelectedItem().toString());
            Tamanho tamanho = Tamanho.valueOf(tamanhoStr.toUpperCase());
            String embalagemStr = removerAcentos(JCBTipoEmbalagemGerenciamentoC.getSelectedItem().toString());
            Embalagem embalagem = Embalagem.valueOf(embalagemStr.toUpperCase());

            List<Produto> produtosAfetados = produtoDao.resgatarProdutos().stream()
                    .filter(p -> p.getCategoria().getNome().equals(nomeAntigo))
                    .collect(Collectors.toList());

            Categoria cat = new Categoria(id, nomeNovo, tamanho, embalagem);
            categoriaDao.atualizarCategoria(cat);

            produtoDao.atualizarProdutoCategoria(nomeNovo, nomeAntigo);

            for (Produto produto : produtosAfetados) {
                Registro reg = new Registro();
                reg.setData(new Date());
                reg.setTipoDoProduto(produto);
                reg.setQuantidade(produto.getQuantidade());
                reg.setMovimentacao(Registro.Movimentacao.ENTRADA); // Ou criar um novo tipo, como .ALTERACAO
                reg.setStatus(Registro.Status.ALCATEGORIA);
                resgistroDao.AdicionarProdutoRegistro(reg);
            }

            carregarCategoriasNaTela();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a categoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_JBAlterarGerenciamentoCActionPerformed

    /**
     * Manipulador de evento para clique na tabela. Carrega os dados da
     * categoria selecionada nos campos de edição.
     *
     * @param evt Evento de clique do mouse
     */
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

    /**
     * Manipulador de evento para o botão Excluir. Remove a categoria
     * selecionada e seus produtos associados.
     *
     * @param evt Evento de ação do botão
     */
    private void JBExcluirGerenciamentoCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBExcluirGerenciamentoCActionPerformed
        int linhaSelecionada = JTableCategoria.getSelectedRow();

    if (linhaSelecionada == -1) {
        JOptionPane.showMessageDialog(this, "Selecione uma categoria para excluir.");
        return;
    }

    DefaultTableModel tabela = (DefaultTableModel) JTableCategoria.getModel();
    int idCategoria = (int) tabela.getValueAt(linhaSelecionada, 0);
    String nomeCategoria = (String) tabela.getValueAt(linhaSelecionada, 1);

    try {
        // 1. Buscar os produtos da categoria
        List<Produto> produtos = produtoDao.buscarProdutosPorNomeCategoria(nomeCategoria);

        // 2. Para cada produto, criar registro de movimentação "SAIDA" com status "DELETADO"
        for (Produto p : produtos) {
            Registro r = new Registro();
            r.setTipoDoProduto(p);
            r.setData(new Date()); // ou new Date()
            r.setMovimentacao(Registro.Movimentacao.SAIDA);
            r.setStatus(Registro.Status.DELETADO);
            r.setQuantidade(p.getQuantidade());

            resgistroDao.AdicionarProdutoRegistro(r); // Assumindo que esse método já existe
        }

        // 3. Remover os produtos da categoria
        produtoDao.removerPorNomeCategoria(nomeCategoria);

        // 4. Remover a categoria
        categoriaDao.deletarCategoriaPorId(idCategoria);

        // 5. Limpar e atualizar a interface
        carregarCategoriasNaTela();
        this.JTFNomeDeCategoria.setText("");

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Erro ao excluir: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
