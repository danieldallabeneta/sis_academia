package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity(name="tbhorario")
public class ModelHorario {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @NotNull
    private Integer atividade;
    
    @Max(value = 6)
    @Min(value = 0)
    private Integer diaSemana;
    
    @Temporal(TemporalType.TIME)
    private Date horaInicio;

    @Temporal(TemporalType.TIME)
    private Date horaTermino;
    
    private Integer capacidade;
    
    private Integer loja;
    
    @Transient
    private String nomeProfessor;
    
    @Transient
    private String nomeAtividade;

    public ModelHorario(Integer id, Integer atividade, Integer diaSemana, Date horaInicio, Date horaTermino, Integer capacidade, Integer loja) {
        super();
        this.id = id;
        this.atividade = atividade;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.capacidade = capacidade;
        this.loja = loja;
    }

    public ModelHorario() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAtividade() {
        return atividade;
    }

    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(Date horaTermino) {
        this.horaTermino = horaTermino;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public String getNomeProfessor() {
        return nomeProfessor;
    }

    public void setNomeProfessor(String nomeProfessor) {
        this.nomeProfessor = nomeProfessor;
    }

    public String getNomeAtividade() {
        return nomeAtividade;
    }

    public void setNomeAtividade(String nomeAtividade) {
        this.nomeAtividade = nomeAtividade;
    }

    @Override
    public String toString() {
        return "ModelHorario{" + "id=" + id + ", atividade=" + atividade + ", diaSemana=" + diaSemana + ", horaInicio=" + horaInicio + ", horaTermino=" + horaTermino + ", capacidade=" + capacidade + ", loja=" + loja + '}';
    }
    
    
}
