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

    public void aumentarPrecoPorCategoria(double percentual, String categoria);

    public void diminuirPrecoPorCategoria(double percentual, String categoria);

    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

}
