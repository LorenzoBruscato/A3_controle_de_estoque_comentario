package modelo;

/**
 * Representa uma categoria de produto, contendo informações de nome, tamanho e
 * embalagem. Possui enums internos para os tipos de tamanho e embalagem.
 *
 * @author Henrique
 */
public class Categoria {

    private Integer id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    /**
     * Enumeração dos tamanhos possíveis para a categoria.
     */
    public enum Tamanho {

        /**
         *
         */
        PEQUENO,
        /**
         *
         */
        MEDIO,
        /**
         *
         */
        GRANDE
    }

    /**
     * Enumeração dos tipos de embalagem possíveis para a categoria.
     */
    public enum Embalagem {

        /**
         *
         */
        LATA,
        /**
         *
         */
        VIDRO,
        /**
         *
         */
        PLASTICO
    }

    /**
     * Construtor padrão que inicializa todos os atributos com valor nulo.
     */
    public Categoria() {
        this(null, null, null, null);
    }

    /**
     * Construtor completo da categoria.
     *
     * @param id Identificador da categoria
     * @param nome Nome da categoria
     * @param tamanho Tamanho da categoria
     * @param embalagem Tipo de embalagem da categoria
     */
    public Categoria(Integer id, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    /**
     * Retorna o id da categoria.
     *
     * @return id da categoria
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o id da categoria.
     *
     * @param id novo id da categoria
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Retorna o nome da categoria.
     *
     * @return nome da categoria
     */
    public String getNome() {
        return nome;
    }

    /**
     * Define o nome da categoria.
     *
     * @param nome novo nome da categoria
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tamanho da categoria.
     *
     * @return tamanho da categoria
     */
    public Tamanho getTamanho() {
        return tamanho;
    }

    /**
     * Define o tamanho da categoria.
     *
     * @param tamanho novo tamanho da categoria
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     *
     * @return tipo de embalagem da categoria
     */
    public Embalagem getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     *
     * @param embalagem novo tipo de embalagem da categoria
     */
    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return String.format("ID: %d | nome: %s | tamanho: %s | embalagem: %s",
                id, nome, tamanho, embalagem);
    }
}
