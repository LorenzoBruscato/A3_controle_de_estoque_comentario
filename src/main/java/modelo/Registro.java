package modelo;

import java.util.Date;

/**
 * Representa um registro de movimentação de produto no estoque, contendo
 * informações sobre data, tipo de movimentação, quantidade, status e o produto
 * envolvido.
 *
 *
 */
public class Registro {

    /**
     * Identificador único do registro.
     */
    private Integer id;

    /**
     * Data da movimentação do produto.
     */
    private Date data;

    /**
     * Produto que está sendo movimentado.
     */
    private Produto tipoDoProduto;

    /**
     * Quantidade movimentada no estoque.
     */
    private Integer quantidade;

    /**
     * Tipo da movimentação realizada no estoque.
     */
    private Movimentacao movimentacao;

    /**
     * Status referente à movimentação ou situação do produto.
     */
    private Status status;

    /**
     * Enum que define os tipos de movimentação possíveis.
     */
    public enum Movimentacao {
        /**
         * Nenhuma movimentação realizada.
         */
        NENHUM,
        /**
         * Entrada de produto no estoque.
         */
        ENTRADA,
        /**
         * Saída de produto do estoque.
         */
        SAIDA
    }

    /**
     * Enum que define os status possíveis relacionados a um registro.
     */
    public enum Status {
        /**
         * Quantidade acima do limite máximo definido.
         */
        ACIMA,
        /**
         * Quantidade abaixo do limite mínimo definido.
         */
        ABAIXO,
        /**
         * Quantidade dentro dos limites mínimos e máximos.
         */
        DENTRO,
        /**
         * Produto adicionado ao sistema.
         */
        ADICIONADO,
        /**
         * Nome do produto foi alterado.
         */
        NOMEALTERADO,
        /**
         * Produto deletado do sistema.
         */
        DELETADO,
        /**
         * Nenhum status especial.
         */
        NENHUM,
        /**
         * Alteração na quantidade mínima do produto.
         */
        ALQTDMI,
        /**
         * Alteração na quantidade máxima do produto.
         */
        ALQTMAX,
        /**
         * Alteração na quantidade mínima e máxima do produto.
         */
        ALQMAEMI,
        /**
         * Alteração na categoria do produto.
         */
        ALCATEGORIA
    }

    /**
     * Construtor padrão que cria um registro vazio.
     */
    public Registro() {
        this(null, null, null, null, null, null);
    }

    /**
     * Construtor que inicializa o registro com todos os atributos.
     *
     * @param id Identificador do registro.
     * @param data Data da movimentação.
     * @param tipoDoProduto Produto relacionado ao registro.
     * @param quantidade Quantidade movimentada.
     * @param movimentacao Tipo de movimentação realizada.
     * @param status Status associado à movimentação.
     */
    public Registro(Integer id, Date data, Produto tipoDoProduto, Integer quantidade, Movimentacao movimentacao, Status status) {
        this.id = id;
        this.data = data;
        this.tipoDoProduto = tipoDoProduto;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
        this.status = status;
    }

    // Getters e Setters
    /**
     * Retorna o identificador do registro.
     *
     * @return id do registro.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do registro.
     *
     * @param id id a ser definido.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna a data da movimentação.
     *
     * @return data da movimentação.
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da movimentação.
     *
     * @param data data a ser definida.
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o produto associado à movimentação.
     *
     * @return produto movimentado.
     */
    public Produto getTipoDoProduto() {
        return tipoDoProduto;
    }

    /**
     * Define o produto associado à movimentação.
     *
     * @param tipoDoProduto produto a ser definido.
     */
    public void setTipoDoProduto(Produto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    /**
     * Retorna a quantidade movimentada.
     *
     * @return quantidade movimentada.
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade movimentada.
     *
     * @param quantidade quantidade a ser definida.
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o tipo de movimentação.
     *
     * @return tipo da movimentação.
     */
    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    /**
     * Define o tipo de movimentação.
     *
     * @param movimentacao tipo de movimentação a ser definido.
     */
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     * Retorna o status da movimentação.
     *
     * @return status associado.
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Define o status da movimentação.
     *
     * @param status status a ser definido.
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Retorna a representação textual do registro, mostrando os principais
     * atributos.
     *
     * @return string representando o registro.
     */
    @Override
    public String toString() {
        return "Registro{"
                + "id=" + id
                + ", data=" + data
                + ", tipoDoProduto=" + (tipoDoProduto != null ? tipoDoProduto.getNome() : null)
                + ", quantidade=" + quantidade
                + ", movimentacao=" + movimentacao
                + ", status=" + status
                + '}';
    }
}
