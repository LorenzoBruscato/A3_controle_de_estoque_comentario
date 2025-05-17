package principal;

import db.Database;
import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import view.FrmMenuPrincipal;

/**
 *
 * @author Andradevh
 */
public class Principal {

    public static void main(String[] args) {
        try {
            Database.getConnection();
            ProdutoDao produtoDao = DaoFactory.instanciarProdutoDao();
            FrmMenuPrincipal janela = new FrmMenuPrincipal();
            janela.setVisible(true);
        } catch (Exception e) {
        } finally {
            Database.closeConnection(); // Fecha a conexão ao finalizar
        }
    }
}
