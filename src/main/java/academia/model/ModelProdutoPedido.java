package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tbprodutopedido")
public class ModelProdutoPedido {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer pedido;

    private Integer produto;

    private Integer quantidade;

    @NotNull
    private float preco;

    public ModelProdutoPedido(Integer id, Integer pedido, Integer produto, Integer quantidade, @NotNull float preco) {
        super();
        this.id = id;
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ModelProdutoPedido() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
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

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "ModelProdutoPedido [id=" + id + ", pedido=" + pedido + ", produto=" + produto + ", quantidade="
                + quantidade + ", preco=" + preco + "]";
    }

}
