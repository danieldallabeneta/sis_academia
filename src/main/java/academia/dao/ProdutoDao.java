package academia.dao;

import academia.jpa.ProdutoRepository;
import academia.model.ModelProduto;
import java.util.List;
import org.springframework.data.domain.Example;

public class ProdutoDao {
    
    public static List<ModelProduto> getAllProdutoByLoja(ProdutoRepository repo, Integer loja) {
        ModelProduto produto = new ModelProduto();
        produto.setLoja(loja);
        Example<ModelProduto> produtoExample = Example.of(produto);
        List<ModelProduto> res = repo.findAll(produtoExample);
        return res;
    }

}
