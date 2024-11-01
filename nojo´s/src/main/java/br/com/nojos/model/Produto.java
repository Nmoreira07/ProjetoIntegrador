package br.com.nojos.model;

public class Produto {
    private Long id; // Adicionando o ID
    private String desc;
    private String marca;
    private Double valor;

    // Construtor com ID e três parâmetros
    public Produto(Long id, String desc, String marca, Double valor) {
        this.id = id;
        this.desc = desc;
        this.marca = marca;
        this.valor = valor;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public String getMarca() {
        return marca;
    }

    public Double getValor() {
        return valor;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
