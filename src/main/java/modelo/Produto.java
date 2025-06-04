package modelo;

public class Produto {

    private Integer id;
    private String nome;
    private Double preco;
    private String unidade;
    private Categoria categoria;
    private Integer quantidade;
    private Integer quantidadeMinima;
    private Integer quantidadeMaxima;

    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    public boolean acimadoMAX() {
        return this.quantidade > quantidadeMaxima;
    }

    public boolean abaixodoMIN() {
        return this.quantidade < quantidadeMinima;
    }

    public double ValorTotal() {
        return this.preco * quantidade;
    }

    public void entrada() {
        this.quantidade += quantidade;
    }

    public void saida() {
        this.quantidade -= quantidade;
    }

    public void ajustarPreco(double porcentual) {
        this.preco += preco * (porcentual / 100);
    }

    @Override
    public String toString() {
        return String.format("ID: %d |nome: %s |preço %.2f |unidade %s |categoria %s |Qtd: %d",
                id, nome, preco, unidade, categoria.getNome(), quantidade);
    }
}
