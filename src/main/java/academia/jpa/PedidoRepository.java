package academia.jpa;

import academia.model.ModelPedido;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PedidoRepository extends JpaRepository<ModelPedido, Integer> {

    List<ModelPedido> findAllByUsuario(Integer usuario);

}
