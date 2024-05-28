package academia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="tbmatriculaatividade")
public class ModelMatriculaAtividade {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    private Integer matricula;
    private Integer horario;

    public ModelMatriculaAtividade(Integer id, Integer matricula, Integer horario) {
        super();
        this.id = id;
        this.matricula = matricula;
        this.horario = horario;
    }

    public ModelMatriculaAtividade() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Integer getHorario() {
        return horario;
    }

    public void setHorario(Integer horario) {
        this.horario = horario;
    }

    @Override
    public String toString() {
        return "ModelMatriculaAtividade{" + "matricula=" + matricula + ", horario=" + horario + '}';
    }
    
}
