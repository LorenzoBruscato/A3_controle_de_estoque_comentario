package modelo;

/**
 * Representa um produto com seus dados básicos, categoria, preço e controle de quantidade.
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
     * Construtor padrão que inicializa os atributos como nulos.
     */
    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * Construtor completo para criar um produto com todos os dados definidos.
     * 
     * @param id identificador do produto
     * @param nome nome do produto
     * @param preco preço unitário do produto
     * @param unidade unidade de medida do produto (ex: kg, unidade)
     * @param categoria categoria a que o produto pertence
     * @param quantidade quantidade atual em estoque
     * @param quantidadeMinima quantidade mínima permitida em estoque
     * @param quantidadeMaxima quantidade máxima permitida em estoque
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
     * @return id do produto
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do produto.
     * 
     * @param id id a ser definido
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome do produto.
     * 
     * @return nome do produto
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * 
     * @param nome nome a ser definido
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o preço unitário do produto.
     * 
     * @return preço do produto
     */
    public Double getPreco() {
        return preco;
    }

    /**
     * Define o preço unitário do produto.
     * 
     * @param preco preço a ser definido
     */
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    /**
     * Retorna a unidade de medida do produto.
     * 
     * @return unidade do produto
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade de medida do produto.
     * 
     * @param unidade unidade a ser definida
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Retorna a categoria do produto.
     * 
     * @return categoria do produto
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     * 
     * @param categoria categoria a ser definida
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Retorna a quantidade atual em estoque do produto.
     * 
     * @return quantidade atual
     */
    public Integer getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade atual em estoque do produto.
     * 
     * @param quantidade quantidade a ser definida
     */
    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Retorna a quantidade mínima permitida em estoque.
     * 
     * @return quantidade mínima
     */
    public Integer getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * Define a quantidade mínima permitida em estoque.
     * 
     * @param quantidadeMinima quantidade mínima a ser definida
     */
    public void setQuantidadeMinima(Integer quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * Retorna a quantidade máxima permitida em estoque.
     * 
     * @return quantidade máxima
     */
    public Integer getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     * Define a quantidade máxima permitida em estoque.
     * 
     * @param quantidadeMaxima quantidade máxima a ser definida
     */
    public void setQuantidadeMaxima(Integer quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está acima do limite máximo.
     * 
     * @return true se quantidade > quantidadeMaxima, false caso contrário
     */
    public boolean acimadoMAX() {
        return this.quantidade != null && this.quantidadeMaxima != null && this.quantidade > quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está abaixo do limite mínimo.
     * 
     * @return true se quantidade < quantidadeMinima, false caso contrário
     */
    public boolean abaixodoMIN() {
        return this.quantidade != null && this.quantidadeMinima != null && this.quantidade < quantidadeMinima;
    }

    /**
     * Calcula o valor total do produto com base no preço e quantidade.
     * 
     * @return valor total (preço * quantidade)
     */
    public double ValorTotal() {
        if (preco == null || quantidade == null) {
            return 0.0;
        }
        return this.preco * quantidade;
    }

    /**
     * Adiciona a quantidade atual a si mesma (exemplo de entrada).
     */
    public void entrada() {
        if (quantidade != null) {
            this.quantidade += quantidade;
        }
    }

    /**
     * Subtrai a quantidade atual de si mesma (exemplo de saída).
     */
    public void saida() {
        if (quantidade != null) {
            this.quantidade -= quantidade;
        }
    }

    /**
     * Ajusta o preço do produto com base em uma porcentagem.
     * 
     * @param porcentual porcentagem para ajustar o preço (ex: 10 para +10%)
     */
    public void ajustarPreco(double porcentual) {
        if (preco != null) {
            this.preco += preco * (porcentual / 100);
        }
    }

    /**
     * Retorna uma representação em string com informações resumidas do produto.
     * 
     * @return string contendo id, nome, preço, unidade, categoria e quantidade
     */
    @Override
    public String toString() {
        return String.format("ID: %d | nome: %s | preço: %.2f | unidade: %s | categoria: %s | Qtd: %d",
                id, nome, preco, unidade, categoria != null ? categoria.getNome() : "N/A", quantidade);
    }
}