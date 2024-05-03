package academia.jpa;

import academia.model.ModelHorario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HorarioRepository extends JpaRepository<ModelHorario, Integer>{
    
}
