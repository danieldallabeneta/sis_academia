package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Entity(name = "tbendereco")
public class ModelEndereco {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    private String cep;

    @Size(min = 3)
    private String rua;

    @Size(min = 3)
    private String bairro;

    @NotNull
    @Positive
    private Integer numero;

    @Size(min = 3)
    private String cidade;

    @Size(min = 3)
    private String estado;

    @Size(min = 3)
    private String pais;

    private String complemento;

    private Integer usuario;

    @NotNull
    //1-Residencial, 2 - Entrega
    private Integer tipo;

    public ModelEndereco(Integer id, @NotNull String cep, @Size(min = 3) String rua, @Size(min = 3) String bairro,
            @NotNull @Positive Integer numero, @Size(min = 3) String cidade, @Size(min = 3) String estado,
            @Size(min = 3) String pais, String complemento, Integer usuario, Integer tipo) {
        super();
        this.id = id;
        this.cep = cep;
        this.rua = rua;
        this.bairro = bairro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.complemento = complemento;
        this.usuario = usuario;
        this.tipo = tipo;
    }

    public ModelEndereco() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "ModelEndereco [id=" + id + ", cep=" + cep + ", rua=" + rua + ", bairro=" + bairro + ", numero=" + numero
                + ", cidade=" + cidade + ", estado=" + estado + ", pais=" + pais + ", complemento=" + complemento
                + ", usuario=" + usuario + ", tipo=" + tipo + "]";
    }

}
