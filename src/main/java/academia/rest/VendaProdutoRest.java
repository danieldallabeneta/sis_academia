package academia.rest;

import academia.jpa.ProdutoRepository;
import academia.jpa.VendaProdutoRepository;
import academia.model.ModelProduto;
import academia.model.ModelVendaProduto;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VendaProdutoRest {
    
    private VendaProdutoRepository repo;
    private ProdutoRepository repoProduto;

    public VendaProdutoRest(VendaProdutoRepository repo, ProdutoRepository repoProduto) {
        this.repo = repo;
        this.repoProduto = repoProduto;
    }
    
    @GetMapping("/venda/produtos/{id}")
    public List<ModelVendaProduto> allProdutosVendaByVenda(@PathVariable int id) throws Exception {
        List<ModelVendaProduto> vendas = repo.findAllByVendaOrder(id);
        if (vendas.isEmpty()) {
            return null;
        }else {
            for (ModelVendaProduto venda : vendas) {
                Optional<ModelProduto> produto = repoProduto.findById(venda.getProduto());
                if(!produto.isEmpty()){
                    venda.setNomeProduto(produto.get().getDescricao());
                }                             
            }
        }
        return vendas;
    }
    
    
}
