package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "tbloja")
public class ModelLoja {

    @Id
    @GeneratedValue
    private Integer id;

    @Size(min = 3)
    @NotNull
    private String nome;
    
    @Size(min = 3)
    @NotNull
    private String cidade;
    
    @NotNull
    private String cnpj;

    public ModelLoja(Integer id, String nome, String cidade, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cidade = cidade;
        this.cnpj = cnpj;
    }

    public ModelLoja() {}

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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public String toString() {
        return "ModelLoja{" + "id=" + id + ", nome=" + nome + ", cidade=" + cidade + ", cnpj=" + cnpj + '}';
    }

}
