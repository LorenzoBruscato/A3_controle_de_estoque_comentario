package dao;
import java.util.ArrayList;
import model.Produto;

/**
 *
 * @author Lorenzo
 */
public class ProdutoDAO {

    private static ArrayList<Produto> listaProduto = new ArrayList<>();

    public static ArrayList<Produto> getListaProduto() {
        return listaProduto;
    }

    public static void setListaProduto(ArrayList listaProduto) {
        ProdutoDAO.listaProduto = listaProduto;
    }
}
