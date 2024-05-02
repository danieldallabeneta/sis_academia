package academia.rest;

import academia.jpa.LojaRepository;
import academia.model.ModelLoja;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class LojaRest {

    private LojaRepository repository;

    public LojaRest(LojaRepository repository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/loja")
    public ResponseEntity<ModelLoja> createLoja(@Valid @RequestBody ModelLoja loja) {
        ModelLoja savedLoja = repository.save(loja);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedLoja.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/lojas")
    public List<ModelLoja> allLojas() {
        List<ModelLoja> lojas = repository.findAll();
        if(lojas.isEmpty()){
            return null;
        }
        return lojas;
    }

    @GetMapping("/lojas/{id}")
    public ModelLoja getLoja(@PathVariable int id) throws Exception {
        Optional<ModelLoja> loja = repository.findById(id);
        if (loja.isEmpty()) {
            throw new Exception("Erro: Id da loja não encontrado");
        }
        return loja.get();
    }

}
