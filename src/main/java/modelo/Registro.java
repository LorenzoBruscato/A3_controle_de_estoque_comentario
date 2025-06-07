package modelo;

import java.util.Date;

/**
 * Representa um registro de movimentação de produto em um sistema de controle de estoque.
 * <p>
 * Um registro contém informações como data da movimentação, tipo de produto,
 * quantidade e tipo de movimentação (entrada ou saída).
 * </p>
 */
public class Registro {

    /** Identificador único do registro. */
    private Integer id;

    /** Data em que o registro foi efetuado. */
    private Date data;

    /** Produto associado a este registro. */
    private Produto tipoDoProduto;

    /** Quantidade movimentada do produto. */
    private Integer quantidade;

    /** Tipo de movimentação realizada: ENTRADA ou SAIDA. */
    private Movimentacao movimentacao;

    /**
     * Enumeração que define os tipos de movimentação possíveis em um registro.
     */
    public enum Movimentacao {
        /** Movimentação de entrada (adicionar itens ao estoque). */
        ENTRADA,

        /** Movimentação de saída (remover itens do estoque). */
        SAIDA
    }

    /**
     * Construtor padrão.
     * <p>
     * Inicializa todos os campos com valores nulos.
     * </p>
     */
    public Registro() {
        this(null, null, null, null, null);
    }

    /**
     * Construtor completo que inicializa todos os campos do registro.
     *
     * @param id            identificador do registro
     * @param data          data da movimentação
     * @param tipoDoProduto produto movimentado
     * @param quantidade    quantidade movimentada
     * @param movimentacao  tipo da movimentação (ENTRADA ou SAIDA)
     */
    public Registro(Integer id, Date data, Produto tipoDoProduto, Integer quantidade, Movimentacao movimentacao) {
        this.id = id;
        this.data = data;
        this.tipoDoProduto = tipoDoProduto;
        this.quantidade = quantidade;
        this.movimentacao = movimentacao;
    }

    /**
     * Retorna o identificador do registro.
     *
     * @return o ID do registro
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do registro.
     *
     * @param id novo ID do registro
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna a data da movimentação.
     *
     * @return a data do registro
     */
    public Date getData() {
        return data;
    }

    /**
     * Define a data da movimentação.
     *
     * @param data nova data do registro
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * Retorna o produto associado ao registro.
     *
     * @return o produto movimentado
     */
    public Produto getTipoDoProduto() {
        return tipoDoProduto;
    }

    /**
     * Define o produto associado ao registro.
     *
     * @param tipoDoProduto novo produto movimentado
     */
    public void setTipoDoProduto(Produto tipoDoProduto) {
        this.tipoDoProduto = tipoDoProduto;
    }

    /**
     * Retorna a quantidade movimentada.
     *
     * @return a quantidade
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade movimentada.
     *
     * @param quantidade nova quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna o tipo de movimentação (ENTRADA ou SAIDA).
     *
     * @return o tipo de movimentação
     */
    public Movimentacao getMovimentacao() {
        return movimentacao;
    }

    /**
     * Define o tipo de movimentação (ENTRADA ou SAIDA).
     *
     * @param movimentacao novo tipo de movimentação
     */
    public void setMovimentacao(Movimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    /**
     * Retorna uma representação textual do registro.
     *
     * @return string contendo os dados do registro formatados
     */
    @Override
    public String toString() {
        return "Registro{"
                + "id=" + id
                + ", data=" + data
                + ", tipoDoProduto=" + (tipoDoProduto != null ? tipoDoProduto.getNome() : null)
                + ", quantidade=" + quantidade
                + ", movimentacao=" + movimentacao
                + '}';
    }
}
