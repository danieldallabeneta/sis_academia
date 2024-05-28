package academia.rest;

import academia.jpa.AlunoRepository;
import academia.jpa.MatriculaAtividadeRepository;
import academia.jpa.MatriculaRepository;
import academia.model.ModelAluno;
import academia.model.ModelMatricula;
import academia.model.ModelMatriculaAtividade;
import jakarta.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatriculaRest {

    private MatriculaRepository repo;
    private AlunoRepository repoAluno;
    private MatriculaAtividadeRepository repoAtivida;

    public MatriculaRest(MatriculaRepository repo, AlunoRepository repoAluno, MatriculaAtividadeRepository repoAtivida) {
        super();
        this.repo = repo;
        this.repoAluno = repoAluno;
        this.repoAtivida = repoAtivida;
    }

    @PostMapping("/matricula")
    public boolean createMatricula(@Valid @RequestBody ModelMatricula matricula) {
        ModelMatricula existe = repo.findFirstByAlunoAndLoja(matricula.getLoja(), matricula.getAluno());
        if (existe == null) {
            ModelMatricula savedMatricula = repo.save(matricula);
            return savedMatricula.getId() > 0;
        } 
        return false;
    }

    @GetMapping("/matriculas/{id}")
    public List<ModelMatricula> allMatriculasByLoja(@PathVariable int id) throws Exception {
        List<ModelMatricula> matriculas = repo.findAllByLojaOrderByAluno(id);
//        List<ModelMatricula> matriculas = MatriculaDao.getAllMatriculaByLoja(repo, id);
        if (matriculas.isEmpty()) {
            return null;
        } else {
            for (ModelMatricula matricula : matriculas) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(matricula.getDataCadastro());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                matricula.setDataCadastro(dataFormatada);
                Optional<ModelAluno> aluno = repoAluno.findById(matricula.getAluno());
                matricula.setNomeAluno(aluno.get().getNome());
            }
            return matriculas;
        }
    }

    @GetMapping("/matricula/{id}")
    public ModelMatricula getMatriculaById(@PathVariable int id) throws Exception {
        Optional<ModelMatricula> matricula = repo.findById(id);
        if (matricula.isEmpty()) {
            return null;
        } else {
            return matricula.get();
        }
    }    

    @DeleteMapping("/matricula/{id}")
    public void deleteBanco(@PathVariable int id) {
        List<ModelMatriculaAtividade> dados = repoAtivida.findAllByMatricula(id);
        if(!dados.isEmpty()){
            for(ModelMatriculaAtividade ativ: dados){
                repoAtivida.delete(ativ);
            }
        }
        repo.deleteById(id);
    }

    @PutMapping("/matricula")
    public void updateMatricula(@Valid @RequestBody ModelMatricula matricula) {
        Optional<ModelMatricula> aux = repo.findById(matricula.getId());        
        if (!aux.isEmpty()) {
            repo.save(matricula);
        }
    }

}
