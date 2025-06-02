package modelo.dao;

import java.util.List;
import modelo.Produto;
import modelo.Registro;

public interface ProdutoDao {

    public void cadastrarProduto(Produto obj, Registro reg);

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

    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

}
