package academia.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import academia.jpa.ProdutoRepository;
import academia.model.ModelProduto;

@RestController
public class ProdutoRest {

    private ProdutoRepository repository;

    public ProdutoRest(ProdutoRepository repository) {
        super();
        this.repository = repository;
    }

    @PostMapping("/products")
    public ResponseEntity<ModelProduto> createProduct(@Valid @RequestBody ModelProduto produto) {
        ModelProduto savedProduct = repository.save(produto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping("/products")
    public List<ModelProduto> allProducts() {
        return repository.findAll();
    }

    @GetMapping("/products/{id}")
    public ModelProduto getProduto(@PathVariable int id) throws Exception {
        Optional<ModelProduto> produto = repository.findById(id);
        if (produto.isEmpty()) {
            throw new Exception("Erro: Id do produto não encontrado");
        }
        return produto.get();
    }

    @DeleteMapping("/products/{id}")
    public void deleteProduct(@PathVariable int id) {
        repository.deleteById(id);
    }
    

}
