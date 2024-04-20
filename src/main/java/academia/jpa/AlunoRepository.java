package academia.jpa;

import academia.model.ModelAluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<ModelAluno, Integer>{
    
}
