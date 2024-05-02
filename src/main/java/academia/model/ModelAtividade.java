package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity(name = "tbatividade")
public class ModelAtividade {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Size(min = 3)
    @NotNull
    private String nome;
    
    @NotNull
    private Integer professor;
    
    @NotNull
    private String local;
    
    @Lob
    private String descricao;
    
    private Integer loja;
    
    @Transient
    private String nomeProfissional;

    public ModelAtividade(Integer id, String nome, Integer professor, String local, String descricao, Integer loja) {
        super();
        this.id = id;
        this.nome = nome;
        this.professor = professor;
        this.local = local;
        this.descricao = descricao;
        this.loja = loja;
    }

    public ModelAtividade() {}

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

    public Integer getProfessor() {
        return professor;
    }

    public void setProfessor(Integer professor) {
        this.professor = professor;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }
    
    public String getNomeProfissional() {
        return nomeProfissional;
    }

    public void setNomeProfissional(String nomeProfissional) {
        this.nomeProfissional = nomeProfissional;
    }

    @Override
    public String toString() {
        return "ModelAtividade{" + "id=" + id + ", nome=" + nome + '}';
    }
    
    
}
