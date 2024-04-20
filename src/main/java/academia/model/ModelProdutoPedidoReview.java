package academia.model;

public class ModelProdutoPedidoReview {

    private Integer id;

    private ModelProduto produto;

    private Integer quantidade;

    private float preco;

    private float total;

    public ModelProdutoPedidoReview(Integer id, ModelProduto produto, Integer quantidade, float preco) {
        super();
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.preco = preco;
    }

    public ModelProdutoPedidoReview() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ModelProduto getProduto() {
        return produto;
    }

    public void setProduto(ModelProduto produto) {
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "ModelProdutoPedidoReview [id=" + id + ", produto=" + produto + ", quantidade=" + quantidade + ", preco="
                + preco + ", total=" + total + "]";
    }

}
