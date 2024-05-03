package academia.model;

public class ModelHorarioAuxiliar {

    private Integer atividade;
    
    private Integer loja;
    
    private String horarios;

    public ModelHorarioAuxiliar(Integer atividade, Integer loja, String horarios) {
        this.atividade = atividade;
        this.loja = loja;
        this.horarios = horarios;
    }

    public Integer getAtividade() {
        return atividade;
    }

    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }

    public Integer getLoja() {
        return loja;
    }

    public void setLoja(Integer loja) {
        this.loja = loja;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    @Override
    public String toString() {
        return "ModelHorarioAuxiliar{" + "atividade=" + atividade + ", loja=" + loja + ", horarios=" + horarios + '}';
    }

    
}
