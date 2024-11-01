package br.com.nojos.model;

public class Produto {
    private Long id;
    private String desc;
    private String marca;
    private Double valor;

    public Produto(Long id, String desc, String marca, Double valor) {
        this.id = id;
        this.desc = desc;
        this.marca = marca;
        this.valor = valor;
    }

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

    public void setId(Long id) {
        this.id = id;
    }

    public void setDesc(String desc) {
        if (desc == null || desc.isEmpty()) {
            throw new IllegalArgumentException("Descrição não pode ser vazia.");
        }
        this.desc = desc;
    }

    public void setMarca(String marca) {
        if (marca == null || marca.isEmpty()) {
            throw new IllegalArgumentException("Marca não pode ser vazia.");
        }
        this.marca = marca;
    }

    public void setValor(Double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        this.valor = valor;
    }
}
