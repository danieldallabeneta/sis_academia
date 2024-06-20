package academia.rest;

import academia.jpa.AlunoMensalidadeRepository;
import academia.jpa.AlunoRepository;
import academia.jpa.CaixaRepository;
import academia.jpa.VendaRepository;
import academia.model.ModelAluno;
import academia.model.ModelAlunoMensalidade;
import academia.model.ModelCaixa;
import academia.model.ModelVenda;
import jakarta.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AlunoMensalidadeRest {
    
    private AlunoMensalidadeRepository repo;
    private CaixaRepository repoCaixa;
    private VendaRepository repoVenda;
    private AlunoRepository repoAluno;

    public AlunoMensalidadeRest(AlunoMensalidadeRepository repo, CaixaRepository repoCaixa, VendaRepository repoVenda, AlunoRepository repoAluno) {
        this.repo = repo;
        this.repoCaixa = repoCaixa;
        this.repoVenda = repoVenda;
        this.repoAluno = repoAluno;
    }
    
    @PostMapping("/mensalidade")
    public ResponseEntity<ModelAlunoMensalidade> createMensalidade(@Valid @RequestBody ModelAlunoMensalidade mensalidade) {
        ModelAlunoMensalidade aux = mensalidade;
        ModelAlunoMensalidade savedMensalidade = repo.save(mensalidade);
        InsereMensalidadeCaixa(mensalidade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMensalidade.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    private void InsereMensalidadeCaixa(ModelAlunoMensalidade mensalidade){
        ModelCaixa caixa = getCaixa(mensalidade);
        criaVendaMensalidadeCaixa(mensalidade, caixa.getId());
    }
    
    private ModelCaixa getCaixa(ModelAlunoMensalidade mensalidade){
        List<ModelCaixa> caixas = repoCaixa.findAllByData(mensalidade.getData());
        ModelCaixa caixa = new ModelCaixa();
        if(caixas.isEmpty()){
            caixa.setData(mensalidade.getData());
            caixa.setLoja(mensalidade.getLoja());
            caixa.setSituacao(1);
            caixa.setValorAbertura(0);
            caixa.setValorFechamento(0);
            repoCaixa.save(caixa);
        } else {
            for (ModelCaixa caixaAux : caixas) {
                return caixaAux;
            }
        }
        return caixa;
    }
    
    private void criaVendaMensalidadeCaixa(ModelAlunoMensalidade mensalidade, Integer caixa){
        ModelVenda venda = new ModelVenda();
        venda.setNome(null);
        venda.setCaixa(caixa);        
        venda.setAluno(mensalidade.getAluno());
        venda.setData(mensalidade.getData());
        venda.setLoja(mensalidade.getLoja());
        venda.setTipoPagamento(1);
        venda.setTipoVenda(3);
        venda.setUsuario(mensalidade.getUsuario());
        venda.setValorTotal(mensalidade.getValorTotal());
        repoVenda.save(venda);
    }
    
    @GetMapping("/mensalidades/{id}")
    public List<ModelAlunoMensalidade> getallMensalidadeByLoja(@PathVariable int id) throws Exception {
        List<ModelAlunoMensalidade> mensalidades = repo.findAllByLojaOrderByData(id);
        if (mensalidades.isEmpty()) {
            return null;
        } else {
            for (ModelAlunoMensalidade mensalidade : mensalidades) {
                Optional<ModelAluno> aluno = repoAluno.findById(mensalidade.getAluno());
                mensalidade.setNomeAluno(aluno.get().getNome());
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(mensalidade.getData());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                mensalidade.setData(dataFormatada);
            }
            return mensalidades;
        }
    }
    
    @GetMapping("/mensalidades/aluno/{id}")
    public List<ModelAlunoMensalidade> getallMensalidadeByAluno(@PathVariable int id) throws Exception {
        List<ModelAlunoMensalidade> mensalidades = repo.findAllByAlunoOrderByData(id);
        if (mensalidades.isEmpty()) {
            return null;
        } else {
            for (ModelAlunoMensalidade mensalidade : mensalidades) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(mensalidade.getData());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                mensalidade.setData(dataFormatada);
            }
            return mensalidades;
        }
    }
    
    
    
}
