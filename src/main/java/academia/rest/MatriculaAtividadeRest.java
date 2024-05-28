package academia.rest;

import academia.jpa.HorarioRepository;
import academia.jpa.MatriculaAtividadeRepository;
import academia.jpa.MatriculaRepository;
import academia.model.ModelHorario;
import academia.model.ModelMatricula;
import academia.model.ModelMatriculaAtividade;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import jakarta.validation.Valid;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MatriculaAtividadeRest {

    private MatriculaAtividadeRepository repo;
    private MatriculaRepository repoMatricula;
    private HorarioRepository repoHorario;

    public MatriculaAtividadeRest(MatriculaAtividadeRepository repo, MatriculaRepository repoMatricula, HorarioRepository repoHorario) {
        super();
        this.repo = repo;
        this.repoMatricula = repoMatricula;
        this.repoHorario = repoHorario;
    }

    @PostMapping("/matriculaAtividade")
    public boolean createMatriculaAtividade(@Valid @RequestBody ModelMatricula matricula) {
        removeConsumoAtividade(matricula.getId());

        String horarios = matricula.getHorarios();

        Type listType = new TypeToken<List<Object>>() {
        }.getType();
        List<Object> jsonArray = new Gson().fromJson(horarios, listType);
        for (Object t : jsonArray) {
            ModelMatriculaAtividade model = new ModelMatriculaAtividade();
            model.setMatricula(matricula.getId());
            String jsonElement = new Gson().toJson(t);

            JsonObject jsonObject = JsonParser.parseString(jsonElement).getAsJsonObject();
            for (String key : jsonObject.keySet()) {
                Integer value = Integer.valueOf(jsonObject.get(key).getAsString());
                consomeHorario(value);
                model.setHorario(value);
                repo.save(model);
            }
        }

        Optional<ModelMatricula> matriculaPrincipal = repoMatricula.findById(matricula.getId());
        ModelMatricula mat = matriculaPrincipal.get();
        mat.setHorarios(matricula.getHorarios());
        repoMatricula.save(mat);
        return true;

    }

    /**
     * Diminui o consumo da matrícula
     *
     * @param id
     */
    private void removeConsumoAtividade(Integer id) {
        List<ModelMatriculaAtividade> modelos = repo.findAllByMatricula(id);
        for (ModelMatriculaAtividade model : modelos) {
            Optional<ModelHorario> horarioAux = repoHorario.findById(model.getHorario());
            if (!horarioAux.isEmpty()) {
                ModelHorario horario = horarioAux.get();
                Integer consumoAtual = horario.getConsumo();
                horario.setConsumo(consumoAtual - 1);
                repoHorario.save(horario);
            }
            repo.delete(model);
        }
    }

    /**
     * Consome uma vaga para a matrícula
     * @param id 
     */
    private void consomeHorario(Integer id) {
        Optional<ModelHorario> horarioAux = repoHorario.findById(id);
        if (!horarioAux.isEmpty()) {
            ModelHorario horario = horarioAux.get();
            Integer consumoAtual = horario.getConsumo();
            horario.setConsumo(consumoAtual + 1);
            repoHorario.save(horario);
        }
    }

    @GetMapping("/matriculaAtividade/{id}")
    public ModelMatricula getMatriculaAtividadeById(@PathVariable int id) throws Exception {
        Optional<ModelMatricula> matricula = repoMatricula.findById(id);
        if (matricula.isEmpty()) {
            return null;
        } else {
            return matricula.get();
        }
    }

}
