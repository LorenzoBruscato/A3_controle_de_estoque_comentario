package modelo;

import java.util.Date;

public class Registro {

    private Integer id;
    private Date data;
    private Produto tipoDoProduto;
    private Integer quantidade;
    private Movimentacao movimentacao;
    private Status status;

    public enum Movimentacao {
        NENHUM, ENTRADA, SAIDA
    }

    public enum Status {
        ACIMA, ABAIXO, DENTRO, ADICIONADO, NOMEALTERADO, DELETADO, NENHUM, ALQTDMI, ALQTMAX, ALQMAEMI, ALCATEGORIA
    }

    public Registro() {
        this(null, null, null, null, null, null);
    }

    public Registro(Integer id, Date data, Produto tipoDoProduto, Integer quantidade, Movimentacao movimentacao, Status status) {
        this.id = id;
        this.data = data;
        this.tipoDoProduto = tipoDoProduto;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
        this.status = status;
    }

    // Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Produto getTipoDoProduto() {
        return tipoDoProduto;
    }

    public void setTipoDoProduto(Produto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Representação textual do objeto Registro
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