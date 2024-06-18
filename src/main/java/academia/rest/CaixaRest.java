package academia.rest;

import academia.jpa.AlunoRepository;
import academia.jpa.CaixaRepository;
import academia.jpa.VendaRepository;
import academia.model.ModelAluno;
import academia.model.ModelCaixa;
import academia.model.ModelVenda;
import jakarta.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class CaixaRest {
    
    private CaixaRepository repo;
    private VendaRepository repoVenda;
    private AlunoRepository repoAluno;

    public CaixaRest(CaixaRepository repo, VendaRepository repoVenda, AlunoRepository repoAluno) {
        this.repo = repo;
        this.repoVenda = repoVenda;
        this.repoAluno = repoAluno;
    }
    
    @PostMapping("/caixa")
    public ResponseEntity<ModelCaixa> createCaixa(@Valid @RequestBody ModelCaixa caixa) {
        ModelCaixa savedCaixa = repo.save(caixa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCaixa.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/caixas/{id}")
    public List<ModelCaixa> allCaixasByLoja(@PathVariable int id) throws Exception {
        List<ModelCaixa> caixas = repo.findAllByLojaOrderByDate(id);
        if (caixas.isEmpty()) {
            return null;
        } else {
            for (ModelCaixa caixa : caixas) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(caixa.getData());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                caixa.setData(dataFormatada);
            }
            return caixas;
        }
    }
    
    @GetMapping("/caixa/{id}")
    public ModelCaixa getCaixaById(@PathVariable int id) throws Exception {
        Optional<ModelCaixa> caixa = repo.findById(id);
        if (caixa.isEmpty()) {
            return null;
        } else {
            return caixa.get();
        }
    }
    
    /**
     * Retorna o caixa com situação aberto para a
     * @param data
     * @return
     * @throws Exception 
     */
    @GetMapping("/caixadata/{data}")
    public ModelCaixa getCaixaByData(@PathVariable String data) throws Exception {
        List<ModelCaixa> caixas = repo.findAllByData(data);
        if (caixas.isEmpty()) {
            return null;
        } else {
            for (ModelCaixa caixa : caixas) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date dataCaixa = formatoOriginal.parse(caixa.getData());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(dataCaixa);
                caixa.setData(dataFormatada);
                return caixa;
            }
        }
        return null;
    }
    
    @DeleteMapping("/caixa/{id}")
    public void deleteCaixa(@PathVariable int id) {
        repo.deleteById(id);
    }
    
    @PutMapping("/caixa")
    public void updateCaixa(@Valid @RequestBody ModelCaixa caixa) {
        Optional<ModelCaixa> aux = repo.findById(caixa.getId());
        if (!aux.isEmpty()) {
            repo.save(caixa);
        } 
    }
    
    @PutMapping("/caixa/fechar/{id}")
    public void fecharCaixa(@PathVariable int id) {
        Optional<ModelCaixa> aux = repo.findById(id);
        if (!aux.isEmpty()) {
            ModelCaixa caixa = aux.get();
            caixa.setSituacao(2);
            repo.save(caixa);
        } 
    }
    
    @PutMapping("/caixa/reabrir/{id}")
    public void reabrirCaixa(@PathVariable int id) {
        Optional<ModelCaixa> aux = repo.findById(id);
        if (!aux.isEmpty()) {
            ModelCaixa caixa = aux.get();
            caixa.setSituacao(1);
            repo.save(caixa);
        } 
    }
    
    @GetMapping("/caixa/vendas/{id}")
    public List<ModelVenda> getVendasCaixa(@PathVariable int id) throws Exception {
        List<ModelVenda> vendas = repoVenda.findAllByCaixa(id);
        if (vendas.isEmpty()) {
            return null;
        } else {
            for (ModelVenda venda : vendas) {
                if(venda.getAluno() != null){
                    Optional<ModelAluno> aluno = repoAluno.findById(venda.getAluno());
                    if(!aluno.isEmpty()){
                        venda.setNomeAluno(aluno.get().getNome());
                    }
                }               
            }
            return vendas;
        }
    }
    
}
