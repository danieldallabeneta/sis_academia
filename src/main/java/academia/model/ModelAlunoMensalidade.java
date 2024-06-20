package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;

@Entity(name="tbalunomensalidade")
public class ModelAlunoMensalidade {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private Integer aluno;
    
    private float valor;
    
    private String data;
    
    private Integer desconto;
    
    private float valorTotal;
    
    private Integer loja;
    
    private Integer usuario;
    
    @Transient
    private String nomeAluno;

    public ModelAlunoMensalidade(Integer id, Integer aluno, float valor, String data, Integer desconto, float valorTotal, Integer loja, Integer usuario) {
        this.id = id;
        this.aluno = aluno;
        this.valor = valor;
        this.data = data;
        this.desconto = desconto;
        this.valorTotal = valorTotal;
        this.loja = loja;
        this.usuario = usuario;
    }
    
    public ModelAlunoMensalidade() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAluno() {
        return aluno;
    }

    public void setAluno(Integer aluno) {
        this.aluno = aluno;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Integer getDesconto() {
        return desconto;
    }

    public void setDesconto(Integer desconto) {
        this.desconto = desconto;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public Integer getUsuario() {
        return usuario;
    }

    public void setUsuario(Integer usuario) {
        this.usuario = usuario;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    @Override
    public String toString() {
        return "ModelAlunoMensalidade{" + "id=" + id + ", aluno=" + aluno + ", valor=" + valor + ", data=" + data + ", desconto=" + desconto + ", valorTotal=" + valorTotal + '}';
    }
    
    
}
