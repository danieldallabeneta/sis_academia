package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;

@Entity(name="tbmatricula")
public class ModelMatricula {
     
    @Id
    @GeneratedValue
    private Integer id;
 
    private Integer aluno;
    private String dataCadastro;
    private Integer periodo;
    private String dataExame;
    private float valorMatricula;
    private float valorMensalidade;
    private String dataMensalidade;
    private Integer loja;
    
    @Transient
    private String nomeAluno;
        
    private String horarios;

    public ModelMatricula(Integer id, Integer aluno, String dataCadastro, Integer periodo, String dataExame, float valorMatricula, float valorMensalidade, String dataMensalidade, Integer loja, String horarios) {
        this.id = id;
        this.aluno = aluno;
        this.dataCadastro = dataCadastro;
        this.periodo = periodo;
        this.dataExame = dataExame;
        this.valorMatricula = valorMatricula;
        this.valorMensalidade = valorMensalidade;
        this.dataMensalidade = dataMensalidade;
        this.loja = loja;
        this.horarios = horarios;
    }

    public ModelMatricula() {}

    public ModelMatricula(Integer id, String horarios) {
        this.id = id;
        this.horarios = horarios;
    }

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

    public String getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(String dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Integer getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Integer periodo) {
        this.periodo = periodo;
    }

    public String getDataExame() {
        return dataExame;
    }

    public void setDataExame(String dataExame) {
        this.dataExame = dataExame;
    }

    public float getValorMatricula() {
        return valorMatricula;
    }

    public void setValorMatricula(float valorMatricula) {
        this.valorMatricula = valorMatricula;
    }

    public float getValorMensalidade() {
        return valorMensalidade;
    }

    public void setValorMensalidade(float valorMensalidade) {
        this.valorMensalidade = valorMensalidade;
    }

    public String getDataMensalidade() {
        return dataMensalidade;
    }

    public void setDataMensalidade(String dataMensalidade) {
        this.dataMensalidade = dataMensalidade;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }
    
    @Override
    public String toString() {
        return "ModelMatricula{" + "id=" + id + ", aluno=" + aluno + ", periodo=" + periodo + ", valorMensalidade=" + valorMensalidade + ", dataMensalidade=" + dataMensalidade + ", loja=" + loja + '}';
    }
    
}
