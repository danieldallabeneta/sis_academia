package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "tbentregapedido")
public class ModelEntregaPedido {

    @Id
    @GeneratedValue
    private Integer id;

    private Integer entrega;

    private Integer pedido;

    private Integer quantidade;

    public ModelEntregaPedido(Integer id, Integer entrega, Integer pedido, Integer quantidade) {
        super();
        this.id = id;
        this.entrega = entrega;
        this.pedido = pedido;
        this.quantidade = quantidade;
    }

    public ModelEntregaPedido() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEntrega() {
        return entrega;
    }

    public void setEntrega(Integer entrega) {
        this.entrega = entrega;
    }

    public Integer getPedido() {
        return pedido;
    }

    public void setPedido(Integer pedido) {
        this.pedido = pedido;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "ModelEntregaPedido [id=" + id + ", entrega=" + entrega + ", pedido=" + pedido + ", quantidade="
                + quantidade + "]";
    }

}
