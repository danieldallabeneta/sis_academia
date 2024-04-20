package academia.jpa;

import academia.model.ModelEntrega;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntregaRepository extends JpaRepository<ModelEntrega, Integer> {

    Optional<ModelEntrega> findTopByOrderByIdDesc();

}
