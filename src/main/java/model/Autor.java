package model;

/**
 * @author joaoleal
 */

public class Autor {
    private int codigo;
    private String nome, estado;
    
    public Autor (int codigo, String nome, String estado) {
        this.codigo = codigo;
        this.nome = nome;
        this.estado = estado;
    }
    
    public Autor () {}

    /**
     * @return the codigo
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
