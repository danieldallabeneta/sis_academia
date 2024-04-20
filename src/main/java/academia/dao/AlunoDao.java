package academia.dao;

import academia.jpa.AlunoRepository;
import academia.model.ModelAluno;
import java.util.List;
import org.springframework.data.domain.Example;

public class AlunoDao {

    public static List<ModelAluno> getAllAlunoByLoja(AlunoRepository repo, Integer loja) {
        ModelAluno aluno = new ModelAluno();
        aluno.setLoja(loja);
        Example<ModelAluno> alunoExample = Example.of(aluno);
        List<ModelAluno> res = repo.findAll(alunoExample);
        return res;
    }
}
