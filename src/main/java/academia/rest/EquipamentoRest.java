package academia.rest;

import academia.dao.EquipamentoDao;
import academia.jpa.EquipamentoRepository;
import academia.model.ModelEquipamento;
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
public class EquipamentoRest {
    
    private EquipamentoRepository repo;

    public EquipamentoRest(EquipamentoRepository repo) {
        super();
        this.repo = repo;
    }
    
    @PostMapping("/equipamento")
    public ResponseEntity<ModelEquipamento> createEquipamento(@Valid @RequestBody ModelEquipamento equip) {
        ModelEquipamento savedEquip = repo.save(equip);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEquip.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/equipamentos/{id}")
    public List<ModelEquipamento> allEquipamentosByLoja(@PathVariable int id) throws Exception {
        List<ModelEquipamento> equipamentos = EquipamentoDao.getAllEquipamentoByLoja(repo, id);
        if (equipamentos.isEmpty()) {
            return null;
        } else {
            for (ModelEquipamento equipa : equipamentos) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(equipa.getDataAquisicao());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                equipa.setDataAquisicao(dataFormatada);
            }
            return equipamentos;
        }
    }
    
    @DeleteMapping("/equipamento/{id}")
    public void deleteEquipamento(@PathVariable int id) {
        repo.deleteById(id);
    }
    
}
