package principal;

import model.dao.CategoriaDao;
import model.dao.DaoFactory;
import model.dao.ProdutoDao;
import view.FrmMenuPrincipal;

/**
 *
 * @author Andradevh
 */
public class Principal {

    public static void main(String[] args) {
        
            CategoriaDao categoriaDao = DaoFactory.instanciarCategoriaDao();
            ProdutoDao produtoDao = DaoFactory.instanciarProdutoDao();
            FrmMenuPrincipal janela = new FrmMenuPrincipal();
            janela.setVisible(true);
        
    }
}
