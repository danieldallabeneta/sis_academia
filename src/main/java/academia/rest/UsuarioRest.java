package academia.rest;

import academia.dao.CarrinhoDao;
import academia.dao.EnderecoDao;
import academia.jpa.CarrinhoRepository;
import academia.jpa.EnderecoRepository;
import academia.jpa.UsuarioRepository;
import academia.model.ModelCarrinho;
import academia.model.ModelCredencial;
import academia.model.ModelEndereco;
import academia.model.ModelUsuario;
import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
public class UsuarioRest {

    private UsuarioRepository usuarioRepository;
    private CarrinhoRepository carrinhoRepository;
    private EnderecoRepository enderecoRepository;

    public UsuarioRest(UsuarioRepository usuarioRepository, CarrinhoRepository carrinhoRepository,
            EnderecoRepository enderecoRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @PostMapping("/users")
    public ModelUsuario createUser(@Valid @RequestBody ModelUsuario user) {
        Optional<ModelUsuario> aux = usuarioRepository.findByEmail(user.getEmail());

        if (aux.isEmpty()) {

            ModelCredencial cred = new ModelCredencial(user.getPassword());
            String[] encript = cred.getSenhaCriptografada();
            user.setPassword(encript[0]);
            user.setSalt(encript[1]);
            ModelUsuario savedUser = usuarioRepository.save(user);

            return savedUser;
        }

        return new ModelUsuario();
    }

    @GetMapping("/users")
    public List<ModelUsuario> allUsers() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ModelUsuario getUser(@PathVariable int id) throws Exception {
        Optional<ModelUsuario> user = usuarioRepository.findById(id);
        if (user.isEmpty()) {
            throw new Exception("Erro: Id do usuário não encontrado");
        }
        return user.get();
    }

    @GetMapping("/usuario/{email}")
    public ModelUsuario getUserByEmail(@PathVariable String email) throws Exception {
        Optional<ModelUsuario> user = usuarioRepository.findByEmail(email);
        if (user.isEmpty()) {
            throw new Exception("Erro: Id do usuário não encontrado");
        }
        return user.get();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        usuarioRepository.deleteById(id);
    }

    @GetMapping("/users/{id}/carrinho")
    public List<ModelCarrinho> getCarrinhoUsuario(@PathVariable int id) throws Exception {
        List<ModelCarrinho> carrinhos = CarrinhoDao.getCarrinhoByUser(carrinhoRepository, id);

        if (carrinhos.isEmpty()) {
            throw new Exception("Erro: Id do usuário não encontrado");
        } else {
            return carrinhos;
        }
    }

    @GetMapping("/users/{id}/endereco")
    public List<ModelEndereco> getEnderecoByUser(@PathVariable int id) {
        List<ModelEndereco> endereco = EnderecoDao.getEnderecoByUser(enderecoRepository, id);
        return endereco;
    }

    @PostMapping("/autenticar")
    public Boolean autenticar(@RequestBody ModelCredencial credencial) {
        Optional<ModelUsuario> user = usuarioRepository.findByEmail(credencial.getUserEmail());
        if (user.isEmpty()) {
            return false;
        }
        return BCrypt.checkpw(credencial.getSenha(), user.get().getPassword());
    }

}
