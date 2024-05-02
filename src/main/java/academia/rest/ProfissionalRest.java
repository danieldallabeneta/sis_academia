package academia.rest;

import academia.dao.AtividadeDao;
import academia.dao.ProfissionalDao;
import academia.jpa.AtividadeRepository;
import academia.jpa.ProfissionalRepository;
import academia.model.ModelProfissional;
import jakarta.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class ProfissionalRest {
    
    private ProfissionalRepository repo;
    private AtividadeRepository repoAtiv;

    public ProfissionalRest(ProfissionalRepository repo, AtividadeRepository repoAtiv) {
        super();
        this.repo = repo;
        this.repoAtiv = repoAtiv;
    }
    
    @PostMapping("/profissional")
    public ResponseEntity<ModelProfissional> createAluno(@Valid @RequestBody ModelProfissional prof) {
        ModelProfissional savedProf = repo.save(prof);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProf.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
    
    @GetMapping("/profissionais/{id}")
    public List<ModelProfissional> allProfissionaisByLoja(@PathVariable int id) throws Exception {
        List<ModelProfissional> profissionais = ProfissionalDao.getAllProfissionalByLoja(repo, id);
        if (profissionais.isEmpty()) {
            return null;
        } else {
            for (ModelProfissional profissional : profissionais) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(profissional.getDataNascimento());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                profissional.setDataNascimento(dataFormatada);
            }
            return profissionais;
        }
    }
    
    @DeleteMapping("/profissional/{id}")
    public boolean deleteProduct(@PathVariable int id) {
        boolean existAtivProf = AtividadeDao.existAtividadeByProfessor(repoAtiv,id);
        if(existAtivProf){
            return false;
        }
        repo.deleteById(id);
        return true;
    }
    
}
