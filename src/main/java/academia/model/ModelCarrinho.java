package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Positive;

@Entity(name = "tbcarrinho")
public class ModelCarrinho {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer usuario;

    private Integer produto;

    @Positive
    private Integer quantidade;

    public ModelCarrinho(Integer id, Integer usuario, Integer produto, @Positive Integer quantidade) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ModelCarrinho() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getProduto() {
        return produto;
    }

    public void setProduto(Integer produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ModelCarrinho [id=" + id + ", usuario=" + usuario + ", produto=" + produto + ", quantidade="
                + quantidade + "]";
    }

}
