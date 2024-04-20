package academia.rest;

import academia.jpa.CarrinhoRepository;
import academia.jpa.ProdutoRepository;
import academia.model.ModelCarrinho;
import academia.model.ModelCarrinhoReview;
import academia.model.ModelProduto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class CarrinhoRest {

    private CarrinhoRepository repository;
    private ProdutoRepository prodRepository;

    public CarrinhoRest(CarrinhoRepository repository, ProdutoRepository prodRepository) {
        super();
        this.repository = repository;
        this.prodRepository = prodRepository;
    }

    @PostMapping("/carrinho")
    public Boolean createUser(@Valid @RequestBody String carrinho) throws JSONException {
        JSONObject jsonObject = new JSONObject(carrinho);
        ModelCarrinho newCarrinho = new ModelCarrinho();
        newCarrinho.setUsuario(jsonObject.getInt("usuario"));
        newCarrinho.setProduto(jsonObject.getInt("produto"));
        newCarrinho.setQuantidade(jsonObject.getInt("quantidade"));
        ModelCarrinho savedCarrinho = repository.save(newCarrinho);
        if (savedCarrinho == null) {
            return false;
        }
        return true;
    }

    @GetMapping("/user/{id}/carrinho")
    public ModelCarrinho getCarrinho(@PathVariable int id) throws Exception {
        Optional<ModelCarrinho> carrinho = repository.findById(id);
        if (carrinho.isEmpty()) {
            return null;
        }
        return carrinho.get();
    }

    @GetMapping("/user/{id}/carrinho/products")
    public List<ModelCarrinhoReview> getProdutosByCarrinhoUser(@PathVariable int id) throws Exception {
        List<ModelCarrinhoReview> res = new ArrayList<>();

        List<ModelCarrinho> carrinho = repository.findByUsuarioOrderByIdAsc(id);
        if (!carrinho.isEmpty()) {
            for (ModelCarrinho car : carrinho) {
                Optional<ModelProduto> prod = prodRepository.findById(car.getProduto());

                ModelCarrinhoReview model = new ModelCarrinhoReview();
                model.setId(car.getId());
                model.setQuantidade(car.getQuantidade());
                model.setUsuario(car.getUsuario());
                model.setProduto(prod.get());
                res.add(model);
            }
        }
        return res;
    }

    @DeleteMapping("/carrinho/{id}")
    public void deleteCarrinho(@PathVariable int id) {
        repository.deleteById(id);
    }

    @PutMapping("/carrinho/{id}/diminui")
    public void diminuiQuantidadeCarrinho(@PathVariable int id) throws Exception {
        Optional<ModelCarrinho> carrinho = repository.findById(id);
        if (carrinho.isEmpty()) {
            throw new Exception("Erro: Id do carrinho não encontrado");
        } else {
            ModelCarrinho car = carrinho.get();
            if ((car.getQuantidade() - 1) >= 1) {
                car.setQuantidade(car.getQuantidade() - 1);
                repository.save(car);
            }
        }
    }

    @PutMapping("/carrinho/{id}/aumenta")
    public void aumentarQuantidadeCarrinho(@PathVariable int id) throws Exception {
        Optional<ModelCarrinho> carrinho = repository.findById(id);
        if (carrinho.isEmpty()) {
            throw new Exception("Erro: Id do carrinho não encontrado");
        } else {
            ModelCarrinho car = carrinho.get();
            car.setQuantidade(car.getQuantidade() + 1);
            repository.save(car);
        }
    }

}
