package academia.rest;

import academia.dao.AtividadeDao;
import academia.jpa.AtividadeRepository;
import academia.jpa.ProfissionalRepository;
import academia.model.ModelAtividade;
import academia.model.ModelProfissional;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class AtividadeRest {
    
    private AtividadeRepository repo;
    private ProfissionalRepository repoProf;

    public AtividadeRest(AtividadeRepository repo, ProfissionalRepository repoProf) {
        super();
        this.repo = repo;
        this.repoProf = repoProf;
    }
    
    @PostMapping("/atividade")
    public ResponseEntity<ModelAtividade> createAtividade(@Valid @RequestBody ModelAtividade atividade) {
        ModelAtividade savedAtiv = repo.save(atividade);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAtiv.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/atividades/{id}")
    public List<ModelAtividade> allAtividadeByLoja(@PathVariable int id) throws Exception {
        List<ModelAtividade> atividades = AtividadeDao.getAllAtividadeByLoja(repo, id);
        if (atividades.isEmpty()) {
            return null;
        } else {
            for (ModelAtividade ativid : atividades) {
                Optional<ModelProfissional> prof = repoProf.findById(ativid.getProfessor());
                ativid.setNomeProfissional(prof.get().getNome());
            }
            return atividades;
        }
    }
    
    @DeleteMapping("/atividade/{id}")
    public void deleteAtividade(@PathVariable int id) {
        repo.deleteById(id);
    }
    
    
}
