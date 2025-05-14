/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
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
