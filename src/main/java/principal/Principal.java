package principal;

import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import visao.FrmMenuPrincipal;

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
