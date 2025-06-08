package modelo.dao;

import java.util.List;
import modelo.Produto;
import modelo.Registro;

/**
 * Interface DAO para operações de acesso e manipulação dos dados da entidade
 * Produto. Define os métodos para CRUD, ajustes de preços, atualização de
 * categorias e geração de relatórios.
 */
public interface ProdutoDao {

    /**
     *
     * @param id
     * @return
     */
    public Produto procurarProdutoPorId(Integer id);

    /**
     *
     * @param obj
     */
    public void cadastrarProduto(Produto obj);

    /**
     *
     * @param obj
     * @param reg
     */
    public void atualizarProduto(Produto obj, Registro reg);

    /**
     *
     * @param nomeNovo
     * @param nomeAntigo
     */
    public void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo);

    /**
     *
     * @param objId
     */
    public void deletarProdutoPorId(int objId);

    /**
     *
     * @return
     */
    public List<Produto> resgatarProdutos();

    /**
     *
     * @param percentual
     */
    void aumentarTodosPrecos(double percentual);

    /**
     *
     * @param percentual
     * @param categoria
     */
    public void aumentarPrecoPorCategoria(double percentual, String categoria);

    /**
     *
     * @param percentual
     */
    void diminuirTodosPrecos(double percentual);

    /**
     *
     * @param percentual
     * @param categoria
     */
    public void diminuirPrecoPorCategoria(double percentual, String categoria);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomeDoArquivo
     */
    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomePlanilha
     */
    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomePlanilha
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomePlanilha
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomePlanilha
     */
    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     *
     * @param caminhoArquivoSaidaExcel
     * @param nomePlanilha
     */
    public void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaDoc
     * @param nomeArquivoDoc
     */
    public void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     *
     * @param caminhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     *
     * @param caminhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     *
     * @param caminnhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     *
     * @param caminnhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     *
     * @param caminnhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioListaProdutoPorCategoriaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     *
     * @param caminhoArquivoSaidaPDF
     * @param nomeArquivoPDF
     */
    public void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}
