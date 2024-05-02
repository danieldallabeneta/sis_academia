package academia.rest;

import academia.dao.AlunoDao;
import academia.jpa.AlunoRepository;
import academia.model.ModelAluno;
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
public class AlunoRest {

    private AlunoRepository repository;

    public AlunoRest(AlunoRepository repository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/aluno")
    public ResponseEntity<ModelAluno> createAluno(@Valid @RequestBody ModelAluno aluno) {
        ModelAluno savedLoja = repository.save(aluno);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLoja.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/alunos/{id}")
    public List<ModelAluno> allAlunosByLoja(@PathVariable int id) throws Exception {
        List<ModelAluno> alunos = AlunoDao.getAllAlunoByLoja(repository, id);
        if (alunos.isEmpty()) {
            return null;
        } else {
            for (ModelAluno aluno : alunos) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(aluno.getDataNascimento());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                aluno.setDataNascimento(dataFormatada);
            }
            return alunos;
        }
    }
    
    @DeleteMapping("/aluno/{id}")
    public void deleteAluno(@PathVariable int id) {
        repository.deleteById(id);
    }
}
