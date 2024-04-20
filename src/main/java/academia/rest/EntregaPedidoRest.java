package academia.rest;

import academia.jpa.EntregaPedidoRepository;
import academia.model.ModelEntregaPedido;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntregaPedidoRest {

    private EntregaPedidoRepository repository;

    public EntregaPedidoRest(EntregaPedidoRepository repository) {
        super();
        this.repository = repository;
    }

    @GetMapping("/entrega/{id}/pedido")
    public List<ModelEntregaPedido> getListaPedidosFromEntrega(@PathVariable int id) {
        return repository.findAllByEntrega(id);
    }

}
