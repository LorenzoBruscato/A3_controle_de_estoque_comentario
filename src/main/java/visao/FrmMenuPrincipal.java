package visao;

/**
 * Classe que representa a tela principal do sistema de controle de estoque.
 * Serve como ponto de entrada para todas as funcionalidades do sistema através
 * de botões e um menu de navegação.
 *
 * @author Victor
 */
public class FrmMenuPrincipal extends javax.swing.JFrame {

    /**
     * Constrói a janela principal do sistema. Inicializa todos os componentes
     * da interface gráfica.
     */
    public FrmMenuPrincipal() {
        initComponents();
    }

    /**
     * Inicializa os componentes da interface gráfica. Método gerado
     * automaticamente pelo NetBeans Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JLMenuPrincipal = new javax.swing.JLabel();
        JBGerenciarCategorias = new javax.swing.JButton();
        JBReajustarPrecos = new javax.swing.JButton();
        JBSair = new javax.swing.JButton();
        JBGerenciarProdutos = new javax.swing.JButton();
        JBEmitirRelatorios = new javax.swing.JButton();
        jBMovimentacao = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuArquivo = new javax.swing.JMenu();
        jMenuGerenciarProdutos = new javax.swing.JMenuItem();
        jMenuGerenciarCategorias = new javax.swing.JMenuItem();
        jMenuReajustarPrecos = new javax.swing.JMenuItem();
        jMenuEmitirRelatorios = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuSobre = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Controle de Estoque - Menu Principal");

        JLMenuPrincipal.setBackground(new java.awt.Color(153, 153, 153));
        JLMenuPrincipal.setFont(new java.awt.Font("Arial", 0, 24)); // NOI18N
        JLMenuPrincipal.setText("Menu Principal");
        JLMenuPrincipal.setAlignmentY(0.0F);

        JBGerenciarCategorias.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBGerenciarCategorias.setText("Gerenciar Categorias");
        JBGerenciarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGerenciarCategoriasActionPerformed(evt);
            }
        });

        JBReajustarPrecos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBReajustarPrecos.setText("Reajustar Preços");
        JBReajustarPrecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBReajustarPrecosActionPerformed(evt);
            }
        });

        JBSair.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBSair.setText("Sair");
        JBSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBSairActionPerformed(evt);
            }
        });

        JBGerenciarProdutos.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBGerenciarProdutos.setText("Gerenciar Produtos");
        JBGerenciarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBGerenciarProdutosActionPerformed(evt);
            }
        });

        JBEmitirRelatorios.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        JBEmitirRelatorios.setText("Emitir Relatórios");
        JBEmitirRelatorios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JBEmitirRelatoriosActionPerformed(evt);
            }
        });

        jBMovimentacao.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        jBMovimentacao.setText("Movimentação");
        jBMovimentacao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBMovimentacaoActionPerformed(evt);
            }
        });

        jMenuBar.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        jMenuArquivo.setText("Arquivo");

        jMenuGerenciarProdutos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, 0));
        jMenuGerenciarProdutos.setText("Gerenciar Produtos");
        jMenuGerenciarProdutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGerenciarProdutosActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuGerenciarProdutos);

        jMenuGerenciarCategorias.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, 0));
        jMenuGerenciarCategorias.setText("Gerenciar Categorias");
        jMenuGerenciarCategorias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGerenciarCategoriasActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuGerenciarCategorias);

        jMenuReajustarPrecos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, 0));
        jMenuReajustarPrecos.setText("Reajustar Preços");
        jMenuReajustarPrecos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuReajustarPrecosActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuReajustarPrecos);

        jMenuEmitirRelatorios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, 0));
        jMenuEmitirRelatorios.setText("Emitir Relatórios");
        jMenuArquivo.add(jMenuEmitirRelatorios);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, 0));
        jMenuItem1.setText("Sair");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenuArquivo.add(jMenuItem1);

        jMenuBar.add(jMenuArquivo);

        jMenuSobre.setText("Sobre");
        jMenuBar.add(jMenuSobre);

        setJMenuBar(jMenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(322, 322, 322)
                        .addComponent(JLMenuPrincipal))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(227, 227, 227)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(JBGerenciarProdutos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBReajustarPrecos, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
                            .addComponent(jBMovimentacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(JBGerenciarCategorias, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JBEmitirRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JBSair, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(225, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(147, 147, 147)
                .addComponent(JLMenuPrincipal)
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBGerenciarProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBGerenciarCategorias, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBReajustarPrecos, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JBEmitirRelatorios, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JBSair, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jBMovimentacao, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(163, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Manipulador de evento para o botão Sair. Encerra a aplicação
     * completamente.
     *
     * @param evt Evento de ação do botão
     */
    private void JBSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_JBSairActionPerformed

    /**
     * Manipulador de evento para o botão Gerenciar Produtos. Abre a janela de
     * gerenciamento de produtos e fecha a janela atual.
     *
     * @param evt Evento de ação do botão
     */
    private void JBGerenciarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGerenciarProdutosActionPerformed
        FrmGerenciarProduto janela = new FrmGerenciarProduto();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBGerenciarProdutosActionPerformed

    /**
     * Manipulador de evento para o botão Emitir Relatórios. Abre a janela de
     * emissão de relatórios e fecha a janela atual.
     *
     * @param evt Evento de ação do botão
     */
    private void JBEmitirRelatoriosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBEmitirRelatoriosActionPerformed
        FrmEmitirRelatorio janela = new FrmEmitirRelatorio();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBEmitirRelatoriosActionPerformed

    /**
     * Manipulador de evento para o botão Gerenciar Categorias. Abre a janela de
     * gerenciamento de categorias e fecha a janela atual.
     *
     * @param evt Evento de ação do botão
     */
    private void JBGerenciarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBGerenciarCategoriasActionPerformed
        FrmGerenciarCategoria janela = new FrmGerenciarCategoria();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBGerenciarCategoriasActionPerformed

    /**
     * Manipulador de evento para o item de menu Gerenciar Categorias. Abre a
     * janela de gerenciamento de categorias e fecha a janela atual.
     *
     * @param evt Evento de ação do menu
     */
    private void jMenuGerenciarCategoriasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGerenciarCategoriasActionPerformed
        FrmGerenciarCategoria janela = new FrmGerenciarCategoria();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuGerenciarCategoriasActionPerformed

    /**
     * Manipulador de evento para o item de menu Gerenciar Produtos. Abre a
     * janela de gerenciamento de produtos e fecha a janela atual.
     *
     * @param evt Evento de ação do menu
     */
    private void jMenuGerenciarProdutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGerenciarProdutosActionPerformed
        // Instancia a tela de cadastro de aluno
        FrmGerenciarProduto objeto = new FrmGerenciarProduto();
        objeto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuGerenciarProdutosActionPerformed

    /**
     * Manipulador de evento para o botão Reajustar Preços. Abre a janela de
     * reajuste de preços e fecha a janela atual.
     *
     * @param evt Evento de ação do botão
     */
    private void JBReajustarPrecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JBReajustarPrecosActionPerformed
        FrmReajustarPreco janela = new FrmReajustarPreco();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_JBReajustarPrecosActionPerformed

    /**
     * Manipulador de evento para o item de menu Sair. Encerra a aplicação
     * completamente.
     *
     * @param evt Evento de ação do menu
     */
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    /**
     * Manipulador de evento para o item de menu Reajustar Preços. Abre a janela
     * de reajuste de preços e fecha a janela atual.
     *
     * @param evt Evento de ação do menu
     */
    private void jMenuReajustarPrecosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuReajustarPrecosActionPerformed
        FrmReajustarPreco janela = new FrmReajustarPreco();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jMenuReajustarPrecosActionPerformed

    /**
     * Manipulador de evento para o botão Movimentação. Abre a janela de
     * movimentação de estoque e fecha a janela atual.
     *
     * @param evt Evento de ação do botão
     */
    private void jBMovimentacaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBMovimentacaoActionPerformed
        FrmMovimentacao janela = new FrmMovimentacao();
        janela.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jBMovimentacaoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton JBEmitirRelatorios;
    private javax.swing.JButton JBGerenciarCategorias;
    private javax.swing.JButton JBGerenciarProdutos;
    private javax.swing.JButton JBReajustarPrecos;
    private javax.swing.JButton JBSair;
    private javax.swing.JLabel JLMenuPrincipal;
    private javax.swing.JButton jBMovimentacao;
    private javax.swing.JMenu jMenuArquivo;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenuItem jMenuEmitirRelatorios;
    private javax.swing.JMenuItem jMenuGerenciarCategorias;
    private javax.swing.JMenuItem jMenuGerenciarProdutos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuReajustarPrecos;
    private javax.swing.JMenu jMenuSobre;
    // End of variables declaration//GEN-END:variables
}
