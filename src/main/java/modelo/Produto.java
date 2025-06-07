package modelo;

/**
 * Representa uma categoria de produto com atributos como ID, nome, tamanho e tipo de embalagem.
 * <p>
 * Esta classe é utilizada para agrupar informações relacionadas a uma categoria específica
 * de produto, incluindo seu identificador, nome descritivo, tamanho físico e tipo de embalagem.
 * </p>
 */
public class Categoria {

    private Integer id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    /**
     * Enumeração que define os possíveis tamanhos de uma categoria de produto.
     * <ul>
     *     <li>PEQUENO</li>
     *     <li>MEDIO</li>
     *     <li>GRANDE</li>
     * </ul>
     */
    public enum Tamanho {
        PEQUENO, MEDIO, GRANDE
    }

    /**
     * Enumeração que define os tipos de embalagem disponíveis.
     * <ul>
     *     <li>LATA</li>
     *     <li>VIDRO</li>
     *     <li>PLASTICO</li>
     * </ul>
     */
    public enum Embalagem {
        LATA, VIDRO, PLASTICO
    }

    /**
     * Construtor padrão.
     * <p>
     * Inicializa todos os atributos com valores nulos.
     * </p>
     */
    public Categoria() {
        this(null, null, null, null);
    }

    /**
     * Construtor completo.
     *
     * @param id        o identificador único da categoria
     * @param nome      o nome da categoria
     * @param tamanho   o tamanho da categoria (PEQUENO, MEDIO, GRANDE)
     * @param embalagem o tipo de embalagem da categoria (LATA, VIDRO, PLASTICO)
     */
    public Categoria(Integer id, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    /**
     * Retorna o identificador da categoria.
     *
     * @return o ID da categoria
     */
    public int getId() {
        return id;
    }

    /**
     * Define o identificador da categoria.
     *
     * @param id o novo ID a ser atribuído
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     *
     * @return o nome da categoria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome o novo nome a ser atribuído
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tamanho da categoria.
     *
     * @return o tamanho (enum Tamanho)
     */
    public Tamanho getTamanho() {
        return tamanho;
    }

    /**
     * Define o tamanho da categoria.
     *
     * @param tamanho o novo tamanho a ser atribuído
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     *
     * @return a embalagem (enum Embalagem)
     */
    public Embalagem getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     *
     * @param embalagem a nova embalagem a ser atribuída
     */
    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    /**
     * Retorna uma representação textual da categoria.
     *
     * @return uma string contendo ID, nome, tamanho e embalagem
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Nome: %s | Tamanho: %s | Embalagem: %s", id, nome, tamanho, embalagem);
    }
}
