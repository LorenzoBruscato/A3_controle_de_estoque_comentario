package modelo;

/**
 * Representa um produto no sistema, contendo informações como identificador, nome, preço,
 * unidade de medida, categoria associada, quantidade em estoque e limites mínimos e máximos.
 * <p>
 * Esta classe permite manipular dados do produto, verificar se a quantidade está acima ou abaixo dos limites
 * definidos, calcular o valor total do estoque do produto e ajustar seu preço com base em um percentual.
 * </p>
 * 
 * @author ivaxs
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
     * Construtor padrão que inicializa um produto com todos os atributos nulos.
     */
    public Produto() {
        this(null, null, null, null, null, null, null, null);
    }

    /**
     * Construtor completo para criação de um produto com todos os atributos.
     * 
     * @param id              Identificador único do produto.
     * @param nome            Nome do produto.
     * @param preco           Preço unitário do produto.
     * @param unidade         Unidade de medida do produto (ex: "kg", "unidade").
     * @param categoria       Categoria à qual o produto pertence.
     * @param quantidade      Quantidade atual em estoque do produto.
     * @param quantidadeMinima Quantidade mínima aceitável em estoque.
     * @param quantidadeMaxima Quantidade máxima aceitável em estoque.
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
     * Obtém o identificador do produto.
     * 
     * @return o ID do produto.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador do produto.
     * 
     * @param id Novo ID para o produto.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Obtém o nome do produto.
     * 
     * @return o nome do produto.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome do produto.
     * 
     * @param nome Novo nome para o produto.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Obtém o preço unitário do produto.
     * 
     * @return o preço do produto.
     */
    public double getPreco() {
        return preco;
    }

    /**
     * Define o preço unitário do produto.
     * 
     * @param preco Novo preço para o produto.
     */
    public void setPreco(double preco) {
        this.preco = preco;
    }

    /**
     * Obtém a unidade de medida do produto.
     * 
     * @return a unidade de medida (ex: "kg", "unidade").
     */
    public String getUnidade() {
        return unidade;
    }

    /**
     * Define a unidade de medida do produto.
     * 
     * @param unidade Nova unidade de medida.
     */
    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    /**
     * Obtém a categoria do produto.
     * 
     * @return a categoria associada ao produto.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Define a categoria do produto.
     * 
     * @param categoria Nova categoria para o produto.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtém a quantidade atual em estoque.
     * 
     * @return a quantidade em estoque.
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * Define a quantidade atual em estoque.
     * 
     * @param quantidade Nova quantidade em estoque.
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    /**
     * Obtém a quantidade mínima aceitável em estoque.
     * 
     * @return a quantidade mínima.
     */
    public int getQuantidadeMinima() {
        return quantidadeMinima;
    }

    /**
     * Define a quantidade mínima aceitável em estoque.
     * 
     * @param quantidadeMinima Nova quantidade mínima.
     */
    public void setQuantidadeMinima(int quantidadeMinima) {
        this.quantidadeMinima = quantidadeMinima;
    }

    /**
     * Obtém a quantidade máxima aceitável em estoque.
     * 
     * @return a quantidade máxima.
     */
    public int getQuantidadeMaxima() {
        return quantidadeMaxima;
    }

    /**
     * Define a quantidade máxima aceitável em estoque.
     * 
     * @param quantidadeMaxima Nova quantidade máxima.
     */
    public void setQuantidadeMaxima(int quantidadeMaxima) {
        this.quantidadeMaxima = quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está acima da quantidade máxima permitida.
     * 
     * @return true se a quantidade está acima do máximo; false caso contrário.
     */
    public boolean acimadoMAX() {
        return this.quantidade > quantidadeMaxima;
    }

    /**
     * Verifica se a quantidade atual está abaixo da quantidade mínima permitida.
     * 
     * @return true se a quantidade está abaixo do mínimo; false caso contrário.
     */
    public boolean abaixodoMIN() {
        return this.quantidade < quantidadeMinima;
    }

    /**
     * Calcula o valor total do estoque deste produto (preço unitário * quantidade).
     * 
     * @return o valor total em estoque.
     */
    public double ValorTotal() {
        return this.preco * quantidade;
    }

    /**
     * Aumenta a quantidade do produto no estoque adicionando o valor atual da quantidade.
     * (Método parece usar a quantidade atual como incremento — pode precisar revisão).
     */
    public void entrada() {
        this.quantidade += quantidade;
    }

    /**
     * Diminui a quantidade do produto no estoque subtraindo o valor atual da quantidade.
     * (Método parece usar a quantidade atual como decremento — pode precisar revisão).
     */
    public void saida() {
        this.quantidade -= quantidade;
    }

    /**
     * Ajusta o preço do produto com base em um percentual informado.
     * 
     * @param porcentual Percentual de ajuste (positivo para aumento, negativo para redução).
     */
    public void ajustarPreco(double porcentual) {
        this.preco += preco * (porcentual / 100);
    }

    /**
     * Retorna uma representação textual do produto, com seus principais atributos formatados.
     * 
     * @return string formatada contendo id, nome, preço, unidade, categoria e quantidade.
     */
    @Override
    public String toString() {
        return String.format("ID: %d |nome: %s |preço %.2f |unidade %s |categoria %s |Qtd: %d",
                id, nome, preco, unidade, categoria.getNome(), quantidade);
    }
}
