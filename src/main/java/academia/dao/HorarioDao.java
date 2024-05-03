package academia.dao;

import academia.jpa.HorarioRepository;
import academia.model.ModelHorario;
import java.util.List;
import org.springframework.data.domain.Example;

public class HorarioDao {

    public static List<ModelHorario> getAllHorarioByLoja(HorarioRepository repo, Integer loja) {
        ModelHorario horario = new ModelHorario();
        horario.setLoja(loja);
        Example<ModelHorario> horarioExample = Example.of(horario);
        List<ModelHorario> res = repo.findAll(horarioExample);
        return res;
    }

    
}
