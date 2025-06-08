package modelo;

import java.util.Date;

/**
 *
 *
 */
public class Registro {

    private Integer id;
    private Date data;
    private Produto tipoDoProduto;
    private Integer quantidade;
    private Movimentacao movimentacao;
    private Status status;

    /**
     *
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
     *
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
     *
     */
    public Registro() {
        this(null, null, null, null, null, null);
    }

    /**
     *
     * @param id
     * @param data
     * @param tipoDoProduto
     * @param quantidade
     * @param movimentacao
     * @param status
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
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public Date getData() {
        return data;
    }

    /**
     *
     * @param data
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     *
     * @return
     */
    public Produto getTipoDoProduto() {
        return tipoDoProduto;
    }

    /**
     *
     * @param tipoDoProduto
     */
    public void setTipoDoProduto(Produto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    /**
     *
     * @return
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     *
     * @param quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     *
     * @return
     */
    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    /**
     *
     * @param movimentacao
     */
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     *
     * @return
     */
    public Status getStatus() {
        return status;
    }

    /**
     *
     * @param status
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     *
     * @return
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