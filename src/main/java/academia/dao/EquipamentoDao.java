package academia.dao;

import academia.jpa.EquipamentoRepository;
import academia.model.ModelEquipamento;
import java.util.List;
import org.springframework.data.domain.Example;

public class EquipamentoDao {
    
    public static List<ModelEquipamento> getAllEquipamentoByLoja(EquipamentoRepository repo, Integer loja) {
        ModelEquipamento equip = new ModelEquipamento();
        equip.setLoja(loja);
        Example<ModelEquipamento> equipoExample = Example.of(equip);
        List<ModelEquipamento> res = repo.findAll(equipoExample);
        return res;
    }
    
}
