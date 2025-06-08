package modelo;

/**
 *
 *
 */
public class Categoria {

    private Integer id;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    /**
     *
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
     *
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
     *
     */
    public Categoria() {
        this(null, null, null, null);
    }

    /**
     *
     * @param id
     * @param nome
     * @param tamanho
     * @param embalagem
     */
    public Categoria(Integer id, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.id = id;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
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
    public Tamanho getTamanho() {
        return tamanho;
    }

    /**
     *
     * @param tamanho
     */
    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    /**
     *
     * @return
     */
    public Embalagem getEmbalagem() {
        return embalagem;
    }

    /**
     *
     * @param embalagem
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
        return String.format("ID: %d |nome: %s |tamanho: %s |Embalagem: %s", id, nome, tamanho, embalagem);
    }
}
