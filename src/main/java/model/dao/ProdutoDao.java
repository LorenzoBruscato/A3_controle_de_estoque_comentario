package model.dao;

import java.util.List;
import model.Produto;

/**
 *
 * @author Diiego
 */
public interface ProdutoDao {
    
    public void cadastrarProduto(Produto obj);
    
    public void atualizarProduto(Produto obj);
    
    public void deletarProdutoPorId(int objId);
    
    public List<Produto> resgatarProdutos();
    
    
}
