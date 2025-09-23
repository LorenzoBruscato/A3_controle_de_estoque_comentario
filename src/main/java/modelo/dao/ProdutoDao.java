package modelo.dao;

import java.util.List;
import modelo.Categoria;
import modelo.Produto;

public interface ProdutoDao {

    Produto procurarProdutoPorId(Integer id);

    void cadastrarProduto(Produto obj);

    void atualizarProduto(Produto obj);

    void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo);

    void deletarProdutoPorId(int objId);

    List<Produto> resgatarProdutos();

    List<Produto> buscarProdutosPorNomeCategoria(String nomeCategoria);

    public Produto procurarProdutoPorNomeCategoriaUnidade(String nome, Categoria categoria, String unidade);

    void removerPorNomeCategoria(String nomeCategoria);

    void aumentarTodosPrecos(double percentual);

    void aumentarPrecoPorCategoria(double percentual, String categoria);

    void diminuirTodosPrecos(double percentual);

    void diminuirPrecoPorCategoria(double percentual, String categoria);

    void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    void gerarRelatorioListaProdutoPorCategoriaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}
