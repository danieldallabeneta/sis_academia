package academia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name = "tbentrega")
public class ModelEntrega {

    @Id
    @GeneratedValue
    private Integer id;

    private LocalDateTime dataSaida;

    private LocalDateTime dataRetorno;

    public ModelEntrega(Integer id, LocalDateTime dataSaida, LocalDateTime dataRetorno) {
        super();
        this.id = id;
        this.dataSaida = dataSaida;
        this.dataRetorno = dataRetorno;
    }

    public ModelEntrega() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataRetorno() {
        return dataRetorno;
    }

    public void setDataRetorno(LocalDateTime dataRetorno) {
        this.dataRetorno = dataRetorno;
    }

    @Override
    public String toString() {
        return "ModelEntrega [id=" + id + ", dataSaida=" + dataSaida + ", dataRetorno=" + dataRetorno + "]";
    }

}
