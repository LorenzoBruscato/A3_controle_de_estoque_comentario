package modelo.dao;

import java.util.List;
import modelo.Categoria;
import modelo.Produto;

/**
 * Interface DAO para operações com produtos, incluindo CRUD, ajustes de preço,
 * atualização de categoria e geração de relatórios em diversos formatos.
 *
 * @author Diego
 */
public interface ProdutoDao {

    /**
     * Busca um produto pelo seu ID.
     *
     * @param id identificador do produto
     * @return produto encontrado ou null se não existir
     */
    Produto procurarProdutoPorId(Integer id);

    /**
     * Cadastra um novo produto no banco.
     *
     * @param obj produto a ser cadastrado
     */
    void cadastrarProduto(Produto obj);

    /**
     * Atualiza um produto existente e registra a operação.
     *
     * @param obj produto a ser atualizado
     */
    void atualizarProduto(Produto obj);

    /**
     * Atualiza o nome da categoria dos produtos que possuem o nome antigo.
     *
     * @param nomeNovo novo nome da categoria
     * @param nomeAntigo nome antigo da categoria
     */
    void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo);

    /**
     * Deleta um produto pelo seu ID.
     *
     * @param objId ID do produto a ser deletado
     */
    void deletarProdutoPorId(int objId);

    /**
     * Retorna a lista de todos os produtos cadastrados.
     *
     * @return lista contendo todos os produtos
     */
    List<Produto> resgatarProdutos();

    /**
     * Busca e retorna uma lista de produtos associados a uma categoria com o
     * nome especificado.
     *
     * @param nomeCategoria o nome da categoria utilizada como critério de
     * busca.
     * @return uma lista de objetos Produto que pertencem à categoria
     * especificada.
     */
    List<Produto> buscarProdutosPorNomeCategoria(String nomeCategoria);

    /**
     * Busca um produto pelo nome, categoria e unidade.
     *
     * @param nome nome do produto
     * @param categoria categoria do produto
     * @param unidade unidade do produto
     * @return produto encontrado ou null se não existir
     */
    public Produto procurarProdutoPorNomeCategoriaUnidade(String nome, Categoria categoria, String unidade);

    /**
     * Remove todos os produtos associados a uma determinada categoria.
     *
     * @param nomeCategoria nome da categoria para remoção dos produtos
     */
    void removerPorNomeCategoria(String nomeCategoria);

    /**
     * Aumenta o preço de todos os produtos por um percentual informado.
     *
     * @param percentual valor percentual para aumento
     */
    void aumentarTodosPrecos(double percentual);

    /**
     * Aumenta o preço dos produtos de uma categoria específica por um
     * percentual.
     *
     * @param percentual percentual para aumento
     * @param categoria nome da categoria alvo
     */
    void aumentarPrecoPorCategoria(double percentual, String categoria);

    /**
     * Diminui o preço de todos os produtos por um percentual informado.
     *
     * @param percentual valor percentual para diminuição
     */
    void diminuirTodosPrecos(double percentual);

    /**
     * Diminui o preço dos produtos de uma categoria específica por um
     * percentual.
     *
     * @param percentual percentual para diminuição
     * @param categoria nome da categoria alvo
     */
    void diminuirPrecoPorCategoria(double percentual, String categoria);

    /**
     * Gera relatório da lista de preços no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomeDoArquivo nome do arquivo Excel gerado
     */
    void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    /**
     * Gera relatório de balanço físico-financeiro no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomePlanilha nome da planilha no Excel
     */
    void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima no
     * formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomePlanilha nome da planilha no Excel
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima no
     * formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomePlanilha nome da planilha no Excel
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos por categoria no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomePlanilha nome da planilha no Excel
     */
    void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de movimentações no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho para salvar o arquivo Excel
     * @param nomePlanilha nome da planilha no Excel
     */
    void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório da lista de preços no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de balanço físico-financeiro no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima no
     * formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima no
     * formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos por categoria no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de movimentações no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho para salvar o arquivo DOC
     * @param nomeArquivoDoc nome do arquivo DOC gerado
     */
    void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório da lista de preços no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de balanço físico-financeiro no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos com preço abaixo da quantidade mínima no
     * formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos com preço acima da quantidade máxima no
     * formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos por categoria no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioListaProdutoPorCategoriaPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de movimentações no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho para salvar o arquivo PDF
     * @param nomeArquivoPDF nome do arquivo PDF gerado
     */
    void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}
