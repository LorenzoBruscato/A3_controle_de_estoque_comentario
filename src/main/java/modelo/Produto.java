package modelo;

/**
 * Representa um produto, incluindo dados básicos, categoria e controle de
 * estoque. Contém métodos para verificar limites e ajustar preço.
 *
 * @author Henrique
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
     * Construtor padrão que inicializa todos os atributos como nulos.
     */
    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * Construtor completo para criar o produto com todos os atributos
     * definidos.
     *
     * @param id Identificador do produto
     * @param nome Nome do produto
     * @param preco Preço unitário do produto
     * @param unidade Unidade de medida do produto
     * @param categoria Categoria do produto
     * @param quantidade Quantidade atual em estoque
     * @param quantidadeMinima Quantidade mínima permitida
     * @param quantidadeMaxima Quantidade máxima permitida
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
     * Retorna o identificador do produto.
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do produto.
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     *
     * @return
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     *
     * @param nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço unitário do produto.
     *
     * @return
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * Define o preço unitário do produto.
     *
     * @param preco
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a unidade de medida do produto.
     *
     * @return
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade de medida do produto.
     *
     * @param unidade
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna a categoria do produto.
     *
     * @return
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     *
     * @param categoria
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna a quantidade atual em estoque.
     *
     * @return
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade atual em estoque.
     *
     * @param quantidade
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna a quantidade mínima permitida em estoque.
     *
     * @return
     */
    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * Define a quantidade mínima permitida em estoque.
     *
     * @param quantidadeMinima
     */
    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * Retorna a quantidade máxima permitida em estoque.
     *
     * @return
     */
    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     * Define a quantidade máxima permitida em estoque.
     *
     * @param quantidadeMaxima
     */
    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está acima do limite máximo.
     *
     * @return true se quantidade estiver acima da quantidade máxima, false caso
     * contrário
     */
    public boolean acimadoMAX() {
        return this.quantidade != null && this.quantidadeMaxima != null && this.quantidade > quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está abaixo do limite mínimo.
     *
     * @return true se quantidade estiver abaixo da quantidade mínima, false
     * caso contrário
     */
    public boolean abaixodoMIN() {
        return this.quantidade != null && this.quantidadeMinima != null && this.quantidade < quantidadeMinima;
    }

    /**
     * Calcula o valor total do produto (preço * quantidade).
     *
     * @return valor total ou 0.0 se preço ou quantidade forem nulos
     */
    public double ValorTotal() {
        if (preco == null || quantidade == null) {
            return 0.0;
        }
        return this.preco * quantidade;
    }

    /**
     * Incrementa a quantidade atual (exemplo de entrada de estoque).
     */
    public void entrada() {
        if (quantidade != null) {
            this.quantidade += quantidade;
        }
    }

    /**
     * Decrementa a quantidade atual (exemplo de saída de estoque).
     */
    public void saida() {
        if (quantidade != null) {
            this.quantidade -= quantidade;
        }
    }

    /**
     * Ajusta o preço do produto em uma porcentagem dada.
     *
     * @param porcentual porcentagem de ajuste no preço (ex: 10 para aumentar
     * 10%)
     */
    public void ajustarPreco(double porcentual) {
        if (preco != null) {
            this.preco += preco * (porcentual / 100);
        }
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID: %d | nome: %s | preço: %.2f | unidade: %s | categoria: %s | Qtd: %d",
                id, nome, preco, unidade, categoria != null ? categoria.getNome() : "N/A", quantidade);
    }
}
