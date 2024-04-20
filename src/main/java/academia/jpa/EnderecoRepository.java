package academia.jpa;

import academia.model.ModelEndereco;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<ModelEndereco, Integer> {

    Optional<ModelEndereco> findByUsuarioAndTipo(Integer usuario, Integer tipo);
}
