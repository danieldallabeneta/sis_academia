package academia.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;

@Entity(name = "tbpedido")
public class ModelPedido {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private Integer usuario;

    @CreationTimestamp
    private LocalDateTime dataPedido;

    private String situacao;

    private String pagamento;

    public ModelPedido(Integer id, Integer usuario) {
        super();
        this.id = id;
        this.usuario = usuario;
    }

    public ModelPedido() {
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

    public LocalDateTime getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(LocalDateTime dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getPagamento() {
        return pagamento;
    }

    public void setPagamento(String pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "ModelPedido [id=" + id + ", usuario=" + usuario + ", dataPedido=" + dataPedido + ", situacao="
                + situacao + ", pagamento=" + pagamento + "]";
    }

}
