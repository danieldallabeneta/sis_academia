package academia.jpa;

import academia.model.ModelProdutoPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoPedidoRepository extends JpaRepository<ModelProdutoPedido, Integer> {

    List<ModelProdutoPedido> findByPedido(Integer pedido);

}
