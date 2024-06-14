package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

@Entity(name="tbcaixa")
public class ModelCaixa {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private String data;
    
    private float valorAbertura;
    
    private float valorFechamento;
    
    @Max(value = 2)
    @Min(value = 1)
    private Integer situacao;
    
    private Integer loja;

    public ModelCaixa(Integer id, String data, float valorAbertura, float valorFechamento, Integer situacao, Integer loja) {
        this.id = id;
        this.data = data;
        this.valorAbertura = valorAbertura;
        this.valorFechamento = valorFechamento;
        this.situacao = situacao;
        this.loja = loja;
    }

    public ModelCaixa() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getValorAbertura() {
        return valorAbertura;
    }

    public void setValorAbertura(float valorAbertura) {
        this.valorAbertura = valorAbertura;
    }

    public float getValorFechamento() {
        return valorFechamento;
    }

    public void setValorFechamento(float valorFechamento) {
        this.valorFechamento = valorFechamento;
    }

    public Integer getSituacao() {
        return situacao;
    }

    public void setSituacao(Integer situacao) {
        this.situacao = situacao;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "ModelCaixa{" + "id=" + id + ", data=" + data + ", valorAbertura=" + valorAbertura + ", valorFechamento=" + valorFechamento + ", situacao=" + situacao + '}';
    }
        
    
}
