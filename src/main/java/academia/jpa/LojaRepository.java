package academia.jpa;

import academia.model.ModelLoja;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LojaRepository extends JpaRepository<ModelLoja, Integer> {

}
