package academia.model;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.util.Date;

/**
 *
 * @author danie
 */
public class ModelHorarioReturn {
        
    private String horaInicio;
    
    private String horaTermino;
    
    private Integer capacidade;
    private Integer diaSemana;

    public ModelHorarioReturn(String horaInicio, String horaTermino, Integer diaSemana, Integer capacidade) {
        this.horaInicio = horaInicio;
        this.horaTermino = horaTermino;
        this.diaSemana = diaSemana;
        this.capacidade = capacidade;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraTermino() {
        return horaTermino;
    }

    public void setHoraTermino(String horaTermino) {
        this.horaTermino = horaTermino;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public Integer getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(Integer diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    
    
}
