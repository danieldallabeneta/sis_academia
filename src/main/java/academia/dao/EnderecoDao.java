package academia.dao;

import academia.jpa.EnderecoRepository;
import academia.model.ModelEndereco;
import java.util.List;
import org.springframework.data.domain.Example;

public class EnderecoDao {

    /*
	 * Retorna lista de endereço do usuario informado por parâmetro
     */
    public static List<ModelEndereco> getEnderecoByUser(EnderecoRepository repo, Integer user) {
        ModelEndereco endereco = new ModelEndereco();
        endereco.setUsuario(user);
        Example<ModelEndereco> enderecoExample = Example.of(endereco);
        List<ModelEndereco> res = repo.findAll(enderecoExample);
        return res;
    }

}
