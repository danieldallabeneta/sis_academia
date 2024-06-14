package academia.rest;

import academia.jpa.AlunoRepository;
import academia.jpa.CaixaRepository;
import academia.jpa.VendaRepository;
import academia.model.ModelVenda;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class VendaRest {
    
    private VendaRepository repo;
    private CaixaRepository repoCaixa;
    private AlunoRepository repoAluno;

    public VendaRest(VendaRepository repo, CaixaRepository repoCaixa, AlunoRepository repoAluno) {
        super();
        this.repo = repo;
        this.repoCaixa = repoCaixa;
        this.repoAluno = repoAluno;
    }
    
    @PostMapping("/venda")
    public ResponseEntity<ModelVenda> createProduct(@Valid @RequestBody ModelVenda produto) {
        ModelVenda savedProduct = repo.save(produto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
}
