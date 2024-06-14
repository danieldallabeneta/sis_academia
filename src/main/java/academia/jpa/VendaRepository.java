package academia.jpa;

import academia.model.ModelVenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<ModelVenda, Integer>{
    
}
