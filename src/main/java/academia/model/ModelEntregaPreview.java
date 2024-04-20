package academia.model;

public class ModelEntregaPreview {

    private Integer id;

    private String dataSaida;
    private String horaSaida;

    private String dataRetorno;
    private String horaRetorno;

    public ModelEntregaPreview(Integer id, String dataSaida, String horaSaida, String dataRetorno, String horaRetorno) {
        super();
        this.id = id;
        this.dataSaida = dataSaida;
        this.horaSaida = horaSaida;
        this.dataRetorno = dataRetorno;
        this.horaRetorno = horaRetorno;
    }

    public ModelEntregaPreview() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getHoraSaida() {
        return horaSaida;
    }

    public void setHoraSaida(String horaSaida) {
        this.horaSaida = horaSaida;
    }

    public String getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(String dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    public String getHoraRetorno() {
        return horaRetorno;
    }

    public void setHoraRetorno(String horaRetorno) {
        this.horaRetorno = horaRetorno;
    }

    @Override
    public String toString() {
        return "ModelEntregaPreview [id=" + id + ", dataSaida=" + dataSaida + ", horaSaida=" + horaSaida
                + ", dataRetorno=" + dataRetorno + ", horaRetorno=" + horaRetorno + "]";
    }

}
