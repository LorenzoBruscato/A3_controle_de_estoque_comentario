package dao;

import java.util.ArrayList;

import model.Categoria;

/**
 *
 * @author Lorenzo
 */
public class CategoriaDAO {

    public CategoriaDAO() {
    }

    private static ArrayList<Categoria> listaCategoria = new ArrayList<>();

    public static ArrayList getListaCategoria() {
        return listaCategoria;
    }

    public static void setListaCategoria(ArrayList<Categoria> listaCategoria) {
        CategoriaDAO.listaCategoria = listaCategoria;
    }
}
