package modelo.dao;

import java.util.List;
import modelo.Produto;
import modelo.Registro;

/**
 * Interface DAO para operações com produtos, incluindo CRUD,
 * ajustes de preço, atualização de categoria e geração de relatórios.
 */
public interface ProdutoDao {

    /**
     * Busca um produto pelo seu ID.
     * @param id identificador do produto
     * @return produto encontrado ou null
     */
    Produto procurarProdutoPorId(Integer id);

    /**
     * Cadastra um novo produto.
     * @param obj produto a ser cadastrado
     */
    void cadastrarProduto(Produto obj);

    /**
     * Atualiza um produto e registra a operação.
     * @param obj produto a ser atualizado
     * @param reg registro da atualização
     */
    void atualizarProduto(Produto obj, Registro reg);

    /**
     * Atualiza o nome da categoria dos produtos.
     * @param nomeNovo novo nome da categoria
     * @param nomeAntigo nome antigo da categoria
     */
    void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo);

    /**
     * Deleta produto pelo ID.
     * @param objId ID do produto a ser deletado
     */
    void deletarProdutoPorId(int objId);

    /**
     * Retorna a lista de todos os produtos.
     * @return lista de produtos
     */
    List<Produto> resgatarProdutos();

    /**
     * Aumenta o preço de todos os produtos por um percentual.
     * @param percentual valor percentual para aumento
     */
    void aumentarTodosPrecos(double percentual);

    /**
     * Aumenta o preço dos produtos de uma categoria específica.
     * @param percentual percentual para aumento
     * @param categoria nome da categoria
     */
    void aumentarPrecoPorCategoria(double percentual, String categoria);

    /**
     * Diminui o preço de todos os produtos por um percentual.
     * @param percentual valor percentual para diminuição
     */
    void diminuirTodosPrecos(double percentual);

    /**
     * Diminui o preço dos produtos de uma categoria específica.
     * @param percentual percentual para diminuição
     * @param categoria nome da categoria
     */
    void diminuirPrecoPorCategoria(double percentual, String categoria);

    /**
     * Gera relatório de lista de preços em formato Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomeDoArquivo nome do arquivo Excel
     */
    void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    /**
     * Gera relatório de balanço físico-financeiro em Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomePlanilha nome da planilha
     */
    void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima em Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomePlanilha nome da planilha
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima em Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomePlanilha nome da planilha
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos por categoria em Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomePlanilha nome da planilha
     */
    void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de movimentações em Excel.
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo
     * @param nomePlanilha nome da planilha
     */
    void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de lista de preços em formato DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de balanço físico-financeiro em DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima em DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima em DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos por categoria em DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de movimentações em DOC.
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de lista de preços em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de balanço físico-financeiro em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos por categoria em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioListaProdutoPorCategoriaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de movimentações em PDF.
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}