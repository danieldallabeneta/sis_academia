package academia.dao;

import academia.jpa.CarrinhoRepository;
import academia.model.ModelCarrinho;
import java.util.List;

import org.springframework.data.domain.Example;

public class CarrinhoDao {

    /**
     * Retorna todos os modelos de carrinho cadastrados para o usuário informado
     * por parâmetro
     */
    public static List<ModelCarrinho> getCarrinhoByUser(CarrinhoRepository repo, Integer user) {
        ModelCarrinho carrinho = new ModelCarrinho();
        carrinho.setUsuario(user);
        Example<ModelCarrinho> carrinhoExample = Example.of(carrinho);
        List<ModelCarrinho> res = repo.findAll(carrinhoExample);
        return res;
    }

}
