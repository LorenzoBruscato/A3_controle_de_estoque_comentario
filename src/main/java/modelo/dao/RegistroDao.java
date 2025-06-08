package modelo.dao;

import java.util.List;
import modelo.Registro;

/**
 *
 *
 */
public interface RegistroDao {

    /**
     *
     * @param reg
     */
    public void AdicionarProdutoRegistro(Registro reg);

    /**
     *
     * @param reg
     */
    public void RemoverProdutoRegistro(Registro reg);

    /**
     *
     * @return
     */
    public List<Registro> resgatarRegistros();
}
