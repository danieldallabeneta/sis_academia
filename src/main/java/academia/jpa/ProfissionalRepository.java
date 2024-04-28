package academia.jpa;

import academia.model.ModelProfissional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<ModelProfissional, Integer>{
    
}
