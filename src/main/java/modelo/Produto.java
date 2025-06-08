package modelo;

/**
 *
 *
 */
public class Produto {
    private Integer id;
    private String nome;
    private Double preco;
    private String unidade;
    private Categoria categoria;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;

    /**
     *
     */
    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     *
     * @param id
     * @param nome
     * @param preco
     * @param unidade
     * @param categoria
     * @param quantidade
     * @param quantidadeMinima
     * @param quantidadeMaxima
     */
    public Produto(Integer id, String nome, Double preco, String unidade, Categoria categoria,
                   Integer quantidade, Integer quantidadeMinima, Integer quantidadeMaxima) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.unidade = unidade;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.quantidadeMinima = quantidadeMinima;
        this.quantidadeMaxima = quantidadeMaxima;
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
    public String getNome() {
        return nome;
    }

    /**
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     *
     * @return
     */
    public double getPreco() {
        return preco;
    }

    /**
     *
     * @param preco
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     *
     * @return
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     *
     * @param unidade
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     *
     * @return
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     *
     * @param categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     *
     * @return
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     *
     * @param quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     *
     * @return
     */
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     *
     * @param quantidadeMinima
     */
    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     *
     * @return
     */
    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     *
     * @param quantidadeMaxima
     */
    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     *
     * @return
     */
    public boolean acimadoMAX() {
        return this.quantidade > quantidadeMaxima;
    }

    /**
     *
     * @return
     */
    public boolean abaixodoMIN() {
        return this.quantidade < quantidadeMinima;
    }

    /**
     *
     * @return
     */
    public double ValorTotal() {
        return this.preco * quantidade;
    }

    /**
     *
     */
    public void entrada() {
        this.quantidade += quantidade;
    }

    /**
     *
     */
    public void saida() {
        this.quantidade -= quantidade;
    }

    /**
     *
     * @param porcentual
     */
    public void ajustarPreco(double porcentual) {
        this.preco += preco * (porcentual / 100);
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID: %d |nome: %s |preço %.2f |unidade %s |categoria %s |Qtd: %d",
                id, nome, preco, unidade, categoria.getNome(), quantidade);
    }
}