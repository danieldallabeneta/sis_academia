package academia.jpa;

import academia.model.ModelAtividade;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AtividadeRepository extends JpaRepository<ModelAtividade, Integer>{
    
}
