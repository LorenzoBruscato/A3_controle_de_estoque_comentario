package principal;

import view.FrmMenuPrincipal;
import db.Database; // Importe a classe Database aqui

/**
 *
 * @author Andradevh
 */
public class Principal {

public static void main(String[] args) {
       try {
            Database.getConnection(); // Obtém a conexão com o banco de dados
            FrmMenuPrincipal janela = new FrmMenuPrincipal();
            janela.setVisible(true);
        } catch (Exception e) {            
        } finally {
            Database.closeConnection(); // Fecha a conexão ao finalizar
        }
}
}
