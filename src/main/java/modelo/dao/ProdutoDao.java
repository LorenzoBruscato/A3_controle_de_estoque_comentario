package modelo.dao;

import java.util.List;
import modelo.Produto;
import modelo.Registro;

public interface ProdutoDao {

    public Produto procurarProdutoPorId(Integer id);

    public void cadastrarProduto(Produto obj);

    public void atualizarProduto(Produto obj, Registro reg);

    public void deletarProdutoPorId(int objId);

    public List<Produto> resgatarProdutos();

    public List<Registro> resgatarRegistros();

    void aumentarTodosPrecos(double percentual);

    void diminuirTodosPrecos(double percentual);

    public void aumentarPrecoPorCategoria(double percentual, String categoria);

    public void diminuirPrecoPorCategoria(double percentual, String categoria);

    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);    

    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);
    
    public void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    public void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    public void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);
    
     public void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    public void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    public void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    public void gerarRelatorioListaProdutoPorCategoriaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);
    
    public void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}
