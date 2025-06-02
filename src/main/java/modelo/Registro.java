package modelo;

import java.util.Date;

/**
 *
 * @author Diiego
 */
public class Registro {

    private Integer id;
    private Date data;
    private Produto tipoDoProduto;
    private Integer quantidade;

    public Registro() {
    }

    public Registro(Integer id, Date data, Produto tipoDoProduto, Integer quantidade) {
        this.id = id;
        this.data = data;
        this.tipoDoProduto = tipoDoProduto;
        this.quantidade = quantidade;
    }

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

}
