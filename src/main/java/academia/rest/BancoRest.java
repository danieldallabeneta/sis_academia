package academia.rest;

import academia.jpa.BancoRepository;
import academia.model.ModelBanco;
import jakarta.validation.Valid;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class BancoRest {
    
    private BancoRepository repo;

    public BancoRest(BancoRepository repo) {
        super();
        this.repo = repo;
    }
    
    @PostMapping("/banco")
    public ResponseEntity<ModelBanco> createBanco(@Valid @RequestBody ModelBanco banco) {
        ModelBanco savedBanco = repo.save(banco);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedBanco.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/bancos/{id}")
    public List<ModelBanco> allBancosByLoja(@PathVariable int id) throws Exception {
        List<ModelBanco> bancos = repo.findAllByLojaOrderById(id);
        if (bancos.isEmpty()) {
            return null;
        } else {
            for (ModelBanco banco : bancos) {
                SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd");
                Date data = formatoOriginal.parse(banco.getDataCadastro());
                SimpleDateFormat novoFormato = new SimpleDateFormat("dd-MM-yyyy");
                String dataFormatada = novoFormato.format(data);
                banco.setDataCadastro(dataFormatada);
            }
            return bancos;
        }
    }
    
    @GetMapping("/banco/{id}")
    public ModelBanco getBancoById(@PathVariable int id) throws Exception {
        Optional<ModelBanco> banco = repo.findById(id);
        if (banco.isEmpty()) {
            return null;
        } else {
            return banco.get();
        }
    }
    
    @DeleteMapping("/banco/{id}")
    public void deleteBanco(@PathVariable int id) {
        repo.deleteById(id);
    }
    
    @PutMapping("/banco")
    public void updateBanco(@Valid @RequestBody ModelBanco banco) {
        Optional<ModelBanco> aux = repo.findById(banco.getId());

        if (!aux.isEmpty()) {
            repo.save(banco);
        } 
    }
    
}
