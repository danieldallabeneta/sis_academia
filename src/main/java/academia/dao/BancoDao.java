package academia.dao;

import academia.jpa.BancoRepository;
import academia.model.ModelBanco;
import java.util.List;
import org.springframework.data.domain.Example;

public class BancoDao {
    
    public static List<ModelBanco> getAllBancoByLoja(BancoRepository repo, Integer loja) {
        ModelBanco banco = new ModelBanco();
        banco.setLoja(loja);
        Example<ModelBanco> bancoExample = Example.of(banco);
        List<ModelBanco> res = repo.findAll(bancoExample);
        return res;
    }
}
