package academia.rest;

import academia.jpa.EntregaRepository;
import academia.model.ModelEntregaPreview;
import academia.model.ModelEntrega;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntregaRest {

    private EntregaRepository repository;

    public EntregaRest(EntregaRepository repository) {
        super();
        this.repository = repository;
    }

    @GetMapping("/entregas")
    public List<ModelEntregaPreview> getListaEntrega() throws Exception {
        List<ModelEntrega> entregas = repository.findAll();
        List<ModelEntregaPreview> res = new ArrayList<>();
        for (ModelEntrega modelEntrega : entregas) {
            LocalDateTime saida = modelEntrega.getDataSaida();
            LocalDateTime retorno = modelEntrega.getDataRetorno();

            ModelEntregaPreview model = new ModelEntregaPreview();
            model.setId(modelEntrega.getId());
            model.setDataSaida(saida.getDayOfMonth() + "/" + saida.getMonth() + "/" + saida.getYear());
            model.setHoraSaida(saida.getHour() + ":" + saida.getMinute() + ":" + saida.getSecond());
            model.setDataRetorno(retorno.getDayOfMonth() + "/" + retorno.getMonth() + "/" + retorno.getYear());
            model.setHoraRetorno(retorno.getHour() + ":" + retorno.getMinute() + ":" + retorno.getSecond());
            res.add(model);
        }
        return res;
    }

}
