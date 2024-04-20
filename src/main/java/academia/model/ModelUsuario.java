package academia.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "tbusuario")
public class ModelUsuario {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull
    @Size(min = 3)
    private String nome;

    @NotNull
    private String cpf;

    private String cartao;

    @NotNull
    private String email;

    @NotNull
    private String password;

    private String salt;

    @Max(value = 2)
    @Min(value = 1)
    private Integer tipo;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private List<ModelCarrinho> carrinho;

    public ModelUsuario(Integer id, @NotNull @Size(min = 3) String nome, @NotNull String cpf, String cartao,
            @NotNull String email, @NotNull String password, String salt,
            @Max(2) @Min(1) Integer tipo) {
        super();
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cartao = cartao;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.tipo = tipo;
    }

    public ModelUsuario() {
    }

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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCartao() {
        return cartao;
    }

    public void setCartao(String cartao) {
        this.cartao = cartao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public List<ModelCarrinho> getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(List<ModelCarrinho> carrinho) {
        this.carrinho = carrinho;
    }

    @Override
    public String toString() {
        return "ModelUsuario [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cartao=" + cartao + ", email=" + email
                + ", password=" + password + ", salt=" + salt + ", tipo=" + tipo + "]";
    }

}
