package principal;

import visao.FrmMenuPrincipal;

/**
 * Classe principal que inicia a aplicação.
 *
 */
public class Principal {

    /**
     * Método principal que executa a aplicação.
     *
     * @param args argumentos da linha de comando (não utilizados).
     */
    public static void main(String[] args) {
        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
    }
}
