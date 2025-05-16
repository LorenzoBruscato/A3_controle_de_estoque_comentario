package model;

public class Categoria {

    private int idc;
    private String nome;
    private Tamanho tamanho;
    private Embalagem embalagem;

    public enum Tamanho {
        PEQUENO, MEDIO, GRANDE
    }

    public enum Embalagem {
        LATA, VIDRO, PLASTICO
    }

    // Construtor
    public Categoria(int idc, String nome, Tamanho tamanho, Embalagem embalagem) {
        this.idc = idc;
        this.nome = nome;
        this.tamanho = tamanho;
        this.embalagem = embalagem;
    }
   

    // Getters
    public int getIdc() {
        return idc;
    }

    public String getNome() {
        return nome;
    }

    public Tamanho getTamanho() {
        return tamanho;
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    // Setters
    public void setIdc(int idc) {
        this.idc = idc;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }
    

    @Override
    public String toString() {
        return "Categoria{"
                + "id_Categoria=" + idc
                + ", nome='" + nome + '\''
                + ", tamanho=" + tamanho
                + ", embalagem=" + embalagem
                + '}';
    }
}
