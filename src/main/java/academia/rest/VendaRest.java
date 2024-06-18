package academia.rest;

import academia.jpa.AlunoRepository;
import academia.jpa.CaixaRepository;
import academia.jpa.ProdutoRepository;
import academia.jpa.VendaProdutoRepository;
import academia.jpa.VendaRepository;
import academia.model.ModelAluno;
import academia.model.ModelVenda;
import academia.model.ModelVendaAuxiliar;
import academia.model.ModelVendaProduto;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.lang.reflect.Type;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VendaRest {
    
    private VendaRepository repo;
    private CaixaRepository repoCaixa;
    private AlunoRepository repoAluno;
    private ProdutoRepository repoProduto;
    private VendaProdutoRepository repoVendaProduto;

    public VendaRest(VendaRepository repo, CaixaRepository repoCaixa, AlunoRepository repoAluno, ProdutoRepository repoProduto, VendaProdutoRepository repoVendaProduto) {
        this.repo = repo;
        this.repoCaixa = repoCaixa;
        this.repoAluno = repoAluno;
        this.repoProduto = repoProduto;
        this.repoVendaProduto = repoVendaProduto;
    }
    
    @PostMapping("/venda")
    public ResponseEntity<ModelVenda> createVenda(@Valid @RequestBody ModelVendaAuxiliar produto) {
        ModelVenda aux = new ModelVenda();
        aux.carregaDadosModelo(produto);
        ModelVenda savedProduct = repo.save(aux);
        
        Gson gson = new Gson();
        Type produtoListType = new TypeToken<List<ModelVendaProduto>>(){}.getType();
        List<ModelVendaProduto> produtos = gson.fromJson(produto.getProdutos(), produtoListType);
        
        ProdutoRest restProd = new ProdutoRest(repoProduto);
        for (ModelVendaProduto vendaProduto : produtos) {
            vendaProduto.setVenda(savedProduct.getId());            
            restProd.diminuirQuantidade(vendaProduto.getProduto(), vendaProduto.getQuantidade());
            repoVendaProduto.save(vendaProduto);
        }
        
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/vendas/{id}")
    public List<ModelVenda> allVendasByLoja(@PathVariable int id) throws Exception {
        List<ModelVenda> vendas = repo.findAllByLojaOrderByDate(id);
        if (vendas.isEmpty()) {
            return null;
        }else {
            for (ModelVenda venda : vendas) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(venda.getData());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                venda.setData(dataFormatada);
                if(venda.getAluno() != null){
                    Optional<ModelAluno> aluno = repoAluno.findById(venda.getAluno());
                    if(!aluno.isEmpty()){
                        venda.setNomeAluno(aluno.get().getNome());
                    }
                }                
            }
        }
        return vendas;
    }
    
}
