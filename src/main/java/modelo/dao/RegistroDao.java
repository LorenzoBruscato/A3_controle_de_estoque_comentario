package modelo.dao;

import java.util.List;
import modelo.Registro;

public interface RegistroDao {

    void AdicionarProdutoRegistro(Registro reg);

    void RemoverProdutoRegistro(Registro reg);

    void AtualizarProdutoRegistro(Registro reg);

    List<Registro> resgatarRegistros();
}
