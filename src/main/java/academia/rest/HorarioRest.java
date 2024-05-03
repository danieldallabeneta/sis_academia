package academia.rest;

import academia.dao.HorarioDao;
import academia.jpa.AtividadeRepository;
import academia.jpa.HorarioRepository;
import academia.jpa.ProfissionalRepository;
import academia.model.ModelAtividade;
import academia.model.ModelHorario;
import academia.model.ModelHorarioAuxiliar;
import academia.model.ModelHorarioReturn;
import academia.model.ModelProfissional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class HorarioRest {
    
    private HorarioRepository repo;
    private ProfissionalRepository repoProf;
    private AtividadeRepository repoAtiv;

    public HorarioRest(HorarioRepository repo, ProfissionalRepository repoProf, AtividadeRepository repoAtiv) {
        super();
        this.repo = repo;
        this.repoProf = repoProf;
        this.repoAtiv = repoAtiv;
    }
    
    @PostMapping("/horario")
    public boolean createHorarios(@Valid @RequestBody ModelHorarioAuxiliar horarios) {
        Gson gson = new Gson();
        ModelHorarioReturn[] items = gson.fromJson(horarios.getHorarios(), ModelHorarioReturn[].class);
        List<ModelHorario> saved = new ArrayList<>();
        for (ModelHorarioReturn item : items) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaIni = LocalTime.parse(item.getHoraInicio(), formatter);
            Time horaInicio = Time.valueOf(horaIni);
            
            LocalTime horaTerm = LocalTime.parse(item.getHoraTermino(), formatter);
            Time horaTermino = Time.valueOf(horaTerm);
                        
            ModelHorario horario = new ModelHorario();
            horario.setAtividade(horarios.getAtividade());
            horario.setCapacidade(item.getCapacidade());
            horario.setDiaSemana(item.getDiaSemana());
            horario.setHoraInicio(horaInicio);
            horario.setHoraTermino(horaTermino);
            horario.setLoja(horarios.getLoja());
            saved.add(horario);
        }
        
        repo.saveAll(saved);

        return true;
    }
    
    @GetMapping("/horarios/{id}")
    public List<ModelHorario> allHorarioByLoja(@PathVariable int id) throws Exception {
        List<ModelHorario> horarios = HorarioDao.getAllHorarioByLoja(repo, id);
        if (horarios.isEmpty()) {
            return null;
        } else {
            for (ModelHorario horario : horarios) {
                Optional<ModelAtividade> ativ = repoAtiv.findById(horario.getAtividade());
                Optional<ModelProfissional> prof = repoProf.findById(ativ.get().getProfessor());
                horario.setNomeProfessor(prof.get().getNome());
                horario.setNomeAtividade(ativ.get().getNome());
            }
            return horarios;
        }
    }
    
    @DeleteMapping("/horario/{id}")
    public void deleteHorario(@PathVariable int id) {
        repo.deleteById(id);
    }
    
}
