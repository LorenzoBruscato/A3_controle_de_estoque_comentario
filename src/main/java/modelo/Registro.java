package modelo;

import java.util.Date;

/**
 * Representa um registro de movimentação de produto no estoque, contendo data,
 * tipo do produto, quantidade movimentada, tipo de movimentação e status
 * associado.
 *
 * @author Henrique
 */
public class Registro {

    private Integer id;
    private Date data;
    private Produto tipoDoProduto;
    private Integer quantidade;
    private Movimentacao movimentacao;
    private Status status;

    /**
     * Enumeração para os tipos de movimentação que um registro pode ter.
     */
    public enum Movimentacao {

        /**
         *
         */
        NENHUM,
        /**
         *
         */
        ENTRADA,
        /**
         *
         */
        SAIDA
    }

    /**
     * Enumeração para os diferentes status que um registro pode ter.
     */
    public enum Status {

        /**
         *
         */
        ACIMA,
        /**
         *
         */
        ABAIXO,
        /**
         *
         */
        DENTRO,
        /**
         *
         */
        ADICIONADO,
        /**
         *
         */
        NOMEALTERADO,
        /**
         *
         */
        DELETADO,
        /**
         *
         */
        NENHUM,
        /**
         *
         */
        ALQTDMI,
        /**
         *
         */
        ALQTMAX,
        /**
         *
         */
        ALQMAEMI,
        /**
         *
         */
        ALCATEGORIA
    }

    /**
     * Construtor padrão que inicializa os campos como nulos.
     */
    public Registro() {
        this(null, null, null, null, null, null);
    }

    /**
     * Construtor completo que inicializa todos os atributos.
     *
     * @param id identificador do registro
     * @param data data da movimentação
     * @param tipoDoProduto produto associado ao registro
     * @param quantidade quantidade movimentada
     * @param movimentacao tipo de movimentação (entrada/saída)
     * @param status status do registro
     */
    public Registro(Integer id, Date data, Produto tipoDoProduto, Integer quantidade, Movimentacao movimentacao, Status status) {
        this.id = id;
        this.data = data;
        this.tipoDoProduto = tipoDoProduto;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
        this.status = status;
    }

    /**
     * Retorna o identificador do registro.
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do registro.
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna a data da movimentação registrada.
     *
     * @return
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da movimentação registrada.
     *
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o produto associado ao registro.
     *
     * @return
     */
    public Produto getTipoDoProduto() {
        return tipoDoProduto;
    }

    /**
     * Define o produto associado ao registro.
     *
     * @param tipoDoProduto
     */
    public void setTipoDoProduto(Produto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    /**
     * Retorna a quantidade movimentada no registro.
     *
     * @return
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade movimentada no registro.
     *
     * @param quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o tipo de movimentação (entrada/saída).
     *
     * @return
     */
    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    /**
     * Define o tipo de movimentação (entrada/saída).
     *
     * @param movimentacao
     */
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     * Retorna o status do registro.
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     * Define o status do registro.
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Representação textual do registro, mostrando suas informações principais.
     *
     * @return string com dados do registro
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
