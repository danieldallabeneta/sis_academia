package academia.jpa;

import academia.model.ModelBanco;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BancoRepository extends JpaRepository<ModelBanco, Integer>{
    
}
