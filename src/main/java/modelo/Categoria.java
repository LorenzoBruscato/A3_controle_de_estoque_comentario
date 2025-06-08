package modelo;

/**
 * Representa uma categoria de produto, definindo um nome, tamanho e tipo de embalagem.
 */
public class Categoria {

    private Integer id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    /**
     * Enumeração que define os tamanhos possíveis para a categoria.
     */
    public enum Tamanho {
        /** Categoria de tamanho pequeno. */
        PEQUENO,
        /** Categoria de tamanho médio. */
        MEDIO,
        /** Categoria de tamanho grande. */
        GRANDE
    }

    /**
     * Enumeração que define os tipos de embalagem possíveis para a categoria.
     */
    public enum Embalagem {
        /** Embalagem do tipo lata. */
        LATA,
        /** Embalagem do tipo vidro. */
        VIDRO,
        /** Embalagem do tipo plástico. */
        PLASTICO
    }

    /**
     * Construtor padrão que inicializa os atributos com valores nulos.
     */
    public Categoria() {
        this(null, null, null, null);
    }

    /**
     * Construtor completo para criar uma categoria com todos os atributos definidos.
     * 
     * @param id identificador da categoria
     * @param nome nome da categoria
     * @param tamanho tamanho da categoria
     * @param embalagem tipo de embalagem da categoria
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
     * @return id da categoria
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define o identificador da categoria.
     * 
     * @param id id a ser definido
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
     * @param nome nome a ser definido
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
     * @param tamanho tamanho a ser definido
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     * Retorna o tipo de embalagem da categoria.
     * 
     * @return embalagem da categoria
     */
    public Embalagem getEmbalagem() {
        return embalagem;
    }

    /**
     * Define o tipo de embalagem da categoria.
     * 
     * @param embalagem embalagem a ser definida
     */
    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    /**
     * Retorna uma representação em string da categoria.
     * 
     * @return string contendo os dados da categoria
     */
    @Override
    public String toString() {
        return String.format("ID: %d | nome: %s | tamanho: %s | embalagem: %s",
            id, nome, tamanho, embalagem);
    }
}