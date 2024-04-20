package academia.jpa;

import academia.model.ModelEntregaPedido;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaPedidoRepository extends JpaRepository<ModelEntregaPedido, Integer> {

    List<ModelEntregaPedido> findAllByEntrega(Integer entrega);

    Optional<ModelEntregaPedido> findTopByPedidoOrderByIdDesc(Integer pedido);

}
