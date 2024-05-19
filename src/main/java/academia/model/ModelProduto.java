package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Entity(name = "tbproduto")
public class ModelProduto {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3)
    @NotNull
    private String descricao;

    @NotNull
    private float preco;

    @PositiveOrZero
    private Integer quantidade;

    private String informacao;
    
    private Integer loja;

    public ModelProduto(Integer id, String descricao, float preco, Integer quantidade, String informacao, Integer loja) {
        this.id = id;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
        this.informacao = informacao;
        this.loja = loja;
    }

    public ModelProduto() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getInformacao() {
        return informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    @Override
    public String toString() {
        return "ModelProduto{" + "id=" + id + ", descricao=" + descricao + ", preco=" + preco + ", quantidade=" + quantidade + ", informacao=" + informacao + '}';
    }    
    

}
