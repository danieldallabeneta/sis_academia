package academia.jpa;

import academia.model.ModelEquipamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamentoRepository extends JpaRepository<ModelEquipamento, Integer>{
    
}
