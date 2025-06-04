package modelo.dao;

import java.util.List;
import modelo.Registro;

public interface RegistroDao {
    
    public void AdicionarProdutoRegistro(Registro reg);
    
    public void RemoverProdutoRegistro(Registro reg);
    
    public List<Registro> resgatarRegistros();
}
