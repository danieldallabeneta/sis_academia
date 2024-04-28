package academia.dao;

import academia.jpa.ProfissionalRepository;
import academia.model.ModelProfissional;
import java.util.List;
import org.springframework.data.domain.Example;

public class ProfissionalDao {

    public static List<ModelProfissional> getAllProfissionalByLoja(ProfissionalRepository repo, Integer loja) {
        ModelProfissional profissional = new ModelProfissional();
        profissional.setLoja(loja);
        Example<ModelProfissional> profExample = Example.of(profissional);
        List<ModelProfissional> res = repo.findAll(profExample);
        return res;
    }
}
