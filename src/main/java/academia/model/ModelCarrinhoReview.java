package academia.model;

public class ModelCarrinhoReview {

    private Integer id;

    private Integer usuario;

    private ModelProduto produto;

    private Integer quantidade;

    public ModelCarrinhoReview(Integer id, Integer usuario, ModelProduto produto, Integer quantidade) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public ModelCarrinhoReview() {
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

    @Override
    public String toString() {
        return "ModelCarrinhoReview [id=" + id + ", usuario=" + usuario + ", produto=" + produto + ", quantidade="
                + quantidade + "]";
    }

}
