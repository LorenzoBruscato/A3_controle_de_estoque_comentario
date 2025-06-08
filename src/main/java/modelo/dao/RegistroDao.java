package modelo.dao;

import java.util.List;
import modelo.Registro;

/**
 * Interface para operações de acesso a dados relacionadas aos registros de
 * movimentação de produtos.
 */
public interface RegistroDao {

    /**
     * Adiciona um novo registro de movimentação de produto.
     *
     * @param reg o objeto {@link Registro} que representa a movimentação a ser
     * adicionada.
     */
    public void AdicionarProdutoRegistro(Registro reg);

    /**
     * Remove um registro de movimentação de produto existente.
     *
     * @param reg o objeto {@link Registro} que representa a movimentação a ser
     * removida.
     */
    public void RemoverProdutoRegistro(Registro reg);

    /**
     * Recupera a lista de todos os registros de movimentação cadastrados.
     *
     * @return uma lista de objetos {@link Registro}.
     */
    public List<Registro> resgatarRegistros();
}
