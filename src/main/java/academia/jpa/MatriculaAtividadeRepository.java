package academia.jpa;

import academia.model.ModelMatriculaAtividade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatriculaAtividadeRepository extends JpaRepository<ModelMatriculaAtividade, Integer>{
    
    List<ModelMatriculaAtividade> findAllByMatricula(Integer matricula);
}
