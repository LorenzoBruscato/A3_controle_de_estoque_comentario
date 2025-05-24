package principal;

import java.util.List;
import modelo.Categoria;
import modelo.Produto;
import modelo.dao.CategoriaDao;
import modelo.dao.DaoFactory;
import modelo.dao.ProdutoDao;
import visao.FrmMenuPrincipal;

public class Principal {

    public static void main(String[] args) {

        CategoriaDao categoriaDao = DaoFactory.instanciarCategoriaDao();
        ProdutoDao produtoDao = DaoFactory.instanciarProdutoDao();

        FrmMenuPrincipal janela = new FrmMenuPrincipal();
        janela.setVisible(true);
        //Categoria c1 = new Categoria(null, "Eletronico", Categoria.Tamanho.MEDIO, Categoria.Embalagem.LATA);
        // Categoria c2 = new Categoria(null, "Alimentos", Categoria.Tamanho.GRANDE, Categoria.Embalagem.LATA);
        // Categoria c3 = new Categoria(null, "Brinquedos", Categoria.Tamanho.PEQUENO, Categoria.Embalagem.PLASTICO);
        //Categoria c4 = new Categoria(null, "Higiene", Categoria.Tamanho.MEDIO, Categoria.Embalagem.PLASTICO);
        //  Categoria c5 = new Categoria(null, "Vestuário", Categoria.Tamanho.GRANDE, Categoria.Embalagem.VIDRO);
        //Categoria c6 = new Categoria(null, "Papelaria", Categoria.Tamanho.PEQUENO, Categoria.Embalagem.LATA);
        // categoriaDao.cadastrarCategoria(c1);
        // categoriaDao.cadastrarCategoria(c2);
        // categoriaDao.cadastrarCategoria(c3);
        // categoriaDao.cadastrarCategoria(c4);
        //  categoriaDao.cadastrarCategoria(c5);
        //  categoriaDao.cadastrarCategoria(c6);

//        Categoria categoria = null;
//        List<Categoria> categorias = categoriaDao.resgatarCategorias();
//
//        for (Categoria c : categorias) {
//            if (c.getNome().equalsIgnoreCase("Refrigerante")) {
//                categoria = c;
//                break;
//            }
//        }
//        if (categoria == null) {
//            System.out.println("Categoria 'Refrigerante' não encontrada.");
//        } else {
//        Produto pro = new Produto();
//        pro.setNome("Coca_Cola");
//        pro.setPreco(10);
//        pro.setUnidade("Litro");
//        pro.setQuantidade(20);
//        pro.setQuantidadeMinima(100);
//        pro.setQuantidadeMaxima(200);
//        pro.setCategoria(categoria);
//        produtoDao.cadastrarProduto(pro);
//        }
    }
}
