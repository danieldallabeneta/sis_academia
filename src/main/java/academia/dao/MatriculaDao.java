package academia.dao;

import academia.jpa.MatriculaRepository;
import academia.model.ModelMatricula;
import java.util.List;
import org.springframework.data.domain.Example;

public class MatriculaDao {
    
    public static boolean existMatriculaByAluno(MatriculaRepository repo, Integer aluno){
        ModelMatricula matricula = new ModelMatricula();
        matricula.setAluno(aluno);
        Example<ModelMatricula> matriculaExample = Example.of(matricula);
        boolean res = repo.exists(matriculaExample);
        return res;
    }
    
    public static List<ModelMatricula> getAllMatriculaByLoja(MatriculaRepository repo, Integer loja) {
        ModelMatricula matricula = new ModelMatricula();
        matricula.setLoja(loja);
        Example<ModelMatricula> MatExample = Example.of(matricula);
        List<ModelMatricula> res = repo.findAll(MatExample);
        return res;
    }
}
