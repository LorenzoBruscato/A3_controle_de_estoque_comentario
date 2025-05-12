
package model;

public class Categoria {
    private String nome;
    private String Tamanho; //(Pequeno, Médio, Grande)
    private String Embalagem; // (Lata, Vidro, Plástico)

    public Categoria() {
    }

    public Categoria(String nome, String Tamanho, String Embalagem) {
        this.nome = nome;
        this.Tamanho = Tamanho;
        this.Embalagem = Embalagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTamanho() {
        return Tamanho;
    }

    public void setTamanho(String Tamanho) {
        this.Tamanho = Tamanho;
    }
    
    public String getEmbalagem() {
        return Embalagem;
    }

    public void setEmbalagem(String Embalagem) {
        this.Embalagem = Embalagem;
    }

    @Override
    public String toString() {
        return nome;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Categoria)) return false;
        Categoria c = (Categoria) obj;
        return this.nome.equalsIgnoreCase(c.getNome());
    }
    
    @Override
    public int hashCode() {
        return nome.toLowerCase().hashCode();
    }
}