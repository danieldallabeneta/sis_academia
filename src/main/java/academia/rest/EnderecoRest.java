package academia.rest;

import academia.jpa.EnderecoRepository;
import academia.model.ModelEndereco;
import java.net.URI;
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

import jakarta.validation.Valid;

@RestController
public class EnderecoRest {

    private EnderecoRepository repository;

    public EnderecoRest(EnderecoRepository repository) {
        super();
        this.repository = repository;
    }

    @GetMapping("/endereco/{id}")
    public ModelEndereco getEnderecoById(@PathVariable int id) throws Exception {
        Optional<ModelEndereco> endereco = repository.findById(id);
        if (endereco.isEmpty()) {
            throw new Exception("Erro: Id do Endereço não encontrado");
        }
        return endereco.get();
    }

    @GetMapping("/endereco")
    public List<ModelEndereco> getListaEndereco() throws Exception {
        return repository.findAll();
    }

    @PostMapping("/endereco")
    public ResponseEntity<ModelEndereco> criaEndereco(@Valid @RequestBody ModelEndereco endereco) {
        ModelEndereco savedEndereco = repository.save(endereco);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedEndereco.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("endereco/{id}")
    public void alteraEndereco(@PathVariable int id, @Valid @RequestBody ModelEndereco endereco) {
        Optional<ModelEndereco> buscaEndereco = repository.findById(id);
        if (!buscaEndereco.isEmpty()) {
            endereco.setId(buscaEndereco.get().getId());
            repository.save(endereco);
        }
    }

    @DeleteMapping("endereco/{id}")
    public void deleteUser(@PathVariable int id) {
        repository.deleteById(id);
    }

    @GetMapping("users/{id}/enderecoentrega")
    public ModelEndereco getEnderecoEntregaByUserId(@PathVariable int id) throws Exception {
        Optional<ModelEndereco> endereco = repository.findByUsuarioAndTipo(id, 2);
        if (endereco.isEmpty()) {
            throw new Exception("Erro: Id do Endereço não encontrado");
        }
        return endereco.get();
    }

}
