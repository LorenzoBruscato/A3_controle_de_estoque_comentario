package modelo.dao;

import java.util.List;
import modelo.Registro;

/**
 * Interface DAO para operações relacionadas ao registro de movimentações de
 * produtos no sistema.
 *
 * @author Diego
 */
public interface RegistroDao {

    /**
     * Adiciona um registro de movimentação de produto no banco de dados.
     *
     * @param reg registro de movimentação a ser adicionado
     */
    void AdicionarProdutoRegistro(Registro reg);

    /**
     * Remove um registro de movimentação de produto do banco de dados.
     *
     * @param reg registro de movimentação a ser removido
     */
    void RemoverProdutoRegistro(Registro reg);

    /**
     * Atualiza um registro de movimentação de produto no banco de dados.
     *
     * @param reg registro de movimentação com dados atualizados
     */
    void AtualizarProdutoRegistro(Registro reg);

    /**
     * Recupera a lista de todos os registros de movimentação de produtos.
     *
     * @return lista contendo todos os registros encontrados
     */
    List<Registro> resgatarRegistros();
}
