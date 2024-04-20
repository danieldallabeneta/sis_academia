package academia.jpa;

import academia.model.ModelUsuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<ModelUsuario, Integer> {

    Optional<ModelUsuario> findByEmail(String email);

}
