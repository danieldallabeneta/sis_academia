package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "tbequipamento")
public class ModelEquipamento {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Size(min = 3)
    @NotNull
    private String nome;
    
    @Max(value = 21)
    @Min(value = 1)
    private Integer tipo;
    
    private String fabricante;
    
    @Max(value = 4)
    @Min(value = 1)
    private Integer condicao;
    
    private Integer ano;
    
    private String numeroSerie;
    
    private String dataAquisicao;
    
    private Float valor;
    
    @Lob
    private String descricao;
    
    private Integer loja;

    public ModelEquipamento(Integer id, String nome, Integer tipo, String fabricante, Integer condicao, Integer ano, String numeroSerie, String dataAquisicao, Float valor, String descricao, Integer loja) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.condicao = condicao;
        this.ano = ano;
        this.numeroSerie = numeroSerie;
        this.dataAquisicao = dataAquisicao;
        this.valor = valor;
        this.descricao = descricao;
        this.loja = loja;
    }

    public ModelEquipamento() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getNumeroSerie() {
        return numeroSerie;
    }

    public void setNumeroSerie(String numeroSerie) {
        this.numeroSerie = numeroSerie;
    }

    public String getDataAquisicao() {
        return dataAquisicao;
    }

    public void setDataAquisicao(String dataAquisicao) {
        this.dataAquisicao = dataAquisicao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "ModelEquipamento{" + "id=" + id + ", nome=" + nome + ", tipo=" + tipo + ", numeroSerie=" + numeroSerie + '}';
    }
    
    
}
