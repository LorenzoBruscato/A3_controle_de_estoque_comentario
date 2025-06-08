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
     * Busca um produto pelo seu identificador único.
     *
     * @param id o identificador do produto
     * @return o produto correspondente ao id informado, ou null se não
     * encontrado
     */
    public Produto procurarProdutoPorId(Integer id);

    /**
     * Cadastra um novo produto no sistema.
     *
     * @param obj o objeto Produto a ser cadastrado
     */
    public void cadastrarProduto(Produto obj);

    /**
     * Atualiza os dados de um produto e registra uma movimentação associada.
     *
     * @param obj o produto com dados atualizados
     * @param reg o registro da movimentação associada à atualização do produto
     */
    public void atualizarProduto(Produto obj, Registro reg);

    /**
     * Atualiza o nome da categoria de todos os produtos que possuem uma
     * categoria específica.
     *
     * @param nomeNovo novo nome da categoria
     * @param nomeAntigo nome antigo da categoria que será substituído
     */
    public void atualizarProdutoCategoria(String nomeNovo, String nomeAntigo);

    /**
     * Remove um produto do sistema pelo seu identificador.
     *
     * @param objId o identificador do produto a ser removido
     */
    public void deletarProdutoPorId(int objId);

    /**
     * Recupera todos os produtos cadastrados.
     *
     * @return uma lista contendo todos os produtos
     */
    public List<Produto> resgatarProdutos();

    /**
     * Aumenta o preço de todos os produtos por uma porcentagem informada.
     *
     * @param percentual a porcentagem de aumento aplicada a todos os preços
     */
    void aumentarTodosPrecos(double percentual);

    /**
     * Diminui o preço de todos os produtos por uma porcentagem informada.
     *
     * @param percentual a porcentagem de desconto aplicada a todos os preços
     */
    void diminuirTodosPrecos(double percentual);

    /**
     * Aumenta o preço dos produtos pertencentes a uma categoria específica.
     *
     * @param percentual a porcentagem de aumento aplicada
     * @param categoria o nome da categoria cujos produtos terão o preço
     * aumentado
     */
    public void aumentarPrecoPorCategoria(double percentual, String categoria);

    /**
     * Diminui o preço dos produtos pertencentes a uma categoria específica.
     *
     * @param percentual a porcentagem de desconto aplicada
     * @param categoria o nome da categoria cujos produtos terão o preço
     * reduzido
     */
    public void diminuirPrecoPorCategoria(double percentual, String categoria);

    /**
     * Gera relatório de lista de preços no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomeDoArquivo nome do arquivo Excel
     */
    public void gerarRelatorioListaDePrecoExcel(String caminhoArquivoSaidaExcel, String nomeDoArquivo);

    /**
     * Gera relatório de balanço físico e financeiro no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomePlanilha nome da planilha dentro do arquivo Excel
     */
    public void gerarRelatorioBalancoFisicoFinanceiroExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de lista de preços de produtos abaixo da quantidade
     * mínima, no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomePlanilha nome da planilha dentro do arquivo Excel
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de lista de preços de produtos acima da quantidade máxima,
     * no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomePlanilha nome da planilha dentro do arquivo Excel
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de produtos agrupados por categoria, no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomePlanilha nome da planilha dentro do arquivo Excel
     */
    public void gerarRelatorioListaProdutoPorCategoriaExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de movimentações de produtos no formato Excel.
     *
     * @param caminhoArquivoSaidaExcel caminho onde o arquivo Excel será salvo
     * @param nomePlanilha nome da planilha dentro do arquivo Excel
     */
    public void gerarRelatorioMovimentacaoExcel(String caminhoArquivoSaidaExcel, String nomePlanilha);

    /**
     * Gera relatório de lista de preços no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioListaDePrecoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de balanço físico e financeiro no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioBalancoFisicoFinanceiroDOC(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de lista de preços de produtos abaixo da quantidade
     * mínima, no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de lista de preços de produtos acima da quantidade máxima,
     * no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de produtos agrupados por categoria, no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioListaProdutoPorCategoriaDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de movimentações de produtos no formato DOC.
     *
     * @param caminhoArquivoSaidaDoc caminho onde o arquivo DOC será salvo
     * @param nomeArquivoDoc nome do arquivo DOC
     */
    public void gerarRelatorioMovimentacaoDoc(String caminhoArquivoSaidaDoc, String nomeArquivoDoc);

    /**
     * Gera relatório de lista de preços no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioListaDePrecoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de balanço físico e financeiro no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioBalancoFisicoFinanceiroPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de lista de preços de produtos abaixo da quantidade
     * mínima, no formato PDF.
     *
     * @param caminnhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioListaDePrecoAbaixoDaQuantidadeMinimaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de lista de preços de produtos acima da quantidade máxima,
     * no formato PDF.
     *
     * @param caminnhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioListaDePrecoAcimaDaQuantidadeMaximaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de produtos agrupados por categoria, no formato PDF.
     *
     * @param caminnhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioListaProdutoPorCategoriaPDF(String caminnhoArquivoSaidaPDF, String nomeArquivoPDF);

    /**
     * Gera relatório de movimentações de produtos no formato PDF.
     *
     * @param caminhoArquivoSaidaPDF caminho onde o arquivo PDF será salvo
     * @param nomeArquivoPDF nome do arquivo PDF
     */
    public void gerarRelatorioMovimentacaoPDF(String caminhoArquivoSaidaPDF, String nomeArquivoPDF);
}
