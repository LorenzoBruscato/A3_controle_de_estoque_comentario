package principal;

import visao.FrmMenuPrincipal;

/**
 * Classe principal responsável por iniciar a execução da aplicação de controle
 * de estoque.
 *
 * <p>
 * Ao ser executada, esta classe instancia e exibe a janela principal do
 * sistema, representada por {@link FrmMenuPrincipal}.</p>
 *
 * <p>
 * É o ponto de entrada da aplicação Java.</p>
 *
 * @author Victor
 */
public class Principal {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
    }
}
