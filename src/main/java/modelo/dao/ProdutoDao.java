package modelo.dao;

import java.util.List;
import modelo.Produto;

public interface ProdutoDao {

    public void cadastrarProduto(Produto obj);

    public void atualizarProduto(Produto obj);

    public void deletarProdutoPorId(int objId);

    public List<Produto> resgatarProdutos();

    void aumentarTodosPrecos(double percentual);

    void diminuirTodosPrecos(double percentual);
}
