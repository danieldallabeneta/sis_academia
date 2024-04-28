package academia.rest;

import academia.dao.ProfissionalDao;
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

    public ProfissionalRest(ProfissionalRepository repo) {
        super();
        this.repo = repo;
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
            throw new Exception("Erro: Nenhum Profissional cadastrado");
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
    public void deleteProduct(@PathVariable int id) {
        repo.deleteById(id);
    }
    
}
