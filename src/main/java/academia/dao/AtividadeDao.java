package academia.dao;

import academia.jpa.AtividadeRepository;
import academia.model.ModelAtividade;
import java.util.List;
import org.springframework.data.domain.Example;

public class AtividadeDao {
    
    public static List<ModelAtividade> getAllAtividadeByLoja(AtividadeRepository repo, Integer loja) {
        ModelAtividade ativ = new ModelAtividade();
        ativ.setLoja(loja);
        Example<ModelAtividade> alunoExample = Example.of(ativ);
        List<ModelAtividade> res = repo.findAll(alunoExample);
        return res;
    }
    
    public static boolean existAtividadeByProfessor(AtividadeRepository repo, Integer prof){
        ModelAtividade ativ = new ModelAtividade();
        ativ.setProfessor(prof);
        Example<ModelAtividade> alunoExample = Example.of(ativ);
        boolean res = repo.exists(alunoExample);
        return res;
    }
    
}
