package modelo;

/**
 * Representa uma categoria de produto com atributos como ID, nome, tamanho e tipo de embalagem.
 */
public class Categoria {

    private Integer id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    /**
     * Enumeração para os possíveis tamanhos da categoria.
     */
    public enum Tamanho {
        PEQUENO, MEDIO, GRANDE
    }

    /**
     * Enumeração para os tipos de embalagem disponíveis.
     */
    public enum Embalagem {
        LATA, VIDRO, PLASTICO
    }

    /**
     * Construtor padrão que inicializa os atributos com valores nulos.
     */
    public Categoria() {
        this(null, null, null, null);
    }

    /**
     * Construtor que permite inicializar todos os atributos da categoria.
     *
     * @param id        o identificador da categoria
     * @param nome      o nome da categoria
     * @param tamanho   o tamanho da categoria
     * @param embalagem o tipo de embalagem da categoria
     */
    public Categoria(Integer id, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    /**
     * Retorna o ID da categoria.
     *
     * @return o identificador da categoria
     */
    public int getId() {
        return id;
    }

    /**
     * Define o ID da categoria.
     *
     * @param id o identificador a ser definido
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
     * @param nome o nome a ser definido
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o tamanho da categoria.
     *
     * @return o tamanho da categoria
     */
    public Tamanho getTamanho() {
        return tamanho;
    }

    /**
     * Define o tamanho da categoria.
     *
     * @param tamanho o tamanho a ser definido
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     *
     * @return o tipo de embalagem da categoria
     */
    public Embalagem getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     *
     * @param embalagem o tipo de embalagem a ser definido
     */
    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    /**
     * Retorna uma representação textual da categoria.
     *
     * @return string formatada com os dados da categoria
     */
    @Override
    public String toString() {
        return String.format("ID: %d |nome: %s |tamanho: %s |Embalagem: %s", id, nome, tamanho, embalagem);
    }
}
