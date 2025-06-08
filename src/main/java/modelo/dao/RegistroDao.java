package modelo.dao;

import java.util.List;
import modelo.Registro;

/**
 * Interface DAO para operações de registro de movimentações de produtos.
 */
public interface RegistroDao {

    /**
     * Adiciona um registro de movimentação de produto.
     * @param reg registro a ser adicionado
     */
    void AdicionarProdutoRegistro(Registro reg);

    /**
     * Remove um registro de movimentação de produto.
     * @param reg registro a ser removido
     */
    void RemoverProdutoRegistro(Registro reg);

    /**
     * Retorna a lista de todos os registros.
     * @return lista de registros
     */
    List<Registro> resgatarRegistros();
}