package academia.rest;

import academia.dao.AlunoDao;
import academia.jpa.AlunoRepository;
import academia.model.ModelAluno;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ModelAluno> createLoja(@Valid @RequestBody ModelAluno aluno) {
        ModelAluno savedLoja = repository.save(aluno);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLoja.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/alunos/{id}")
    public List<ModelAluno> allLojas(@PathVariable int id) throws Exception {
        List<ModelAluno> alunos = AlunoDao.getAllAlunoByLoja(repository, id);
        if (alunos.isEmpty()) {
            throw new Exception("Erro: Id do usuário não encontrado");
        } else {
            return alunos;
        }
    }
}
