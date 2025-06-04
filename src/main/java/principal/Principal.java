package principal;

import java.io.FileOutputStream;
import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import visao.FrmMenuPrincipal;

public class Principal {

    public static void main(String[] args) {

        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);

    }
}
