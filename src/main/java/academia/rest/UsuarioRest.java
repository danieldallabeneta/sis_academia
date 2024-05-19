package academia.rest;

import academia.dao.UsuarioDao;
import academia.jpa.UsuarioRepository;
import academia.model.ModelCredencial;
import academia.model.ModelUsuario;
import academia.model.ModelUsuarioAuxiliar;
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
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UsuarioRest {

    private UsuarioRepository usuarioRepository;

    public UsuarioRest(UsuarioRepository usuarioRepository) {
        super();
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/users")
    public Integer createUser(@Valid @RequestBody ModelUsuarioAuxiliar user) {
        Optional<ModelUsuario> aux = usuarioRepository.findByEmail(user.getEmail());

        if (aux.isEmpty()) {
            ModelUsuario usuario = new ModelUsuario();
            usuario.setNome(user.getNome());
            usuario.setCpf(user.getCpf());
            usuario.setEmail(user.getEmail());
            usuario.setPassword(user.getPassword());
            
            ModelCredencial cred = new ModelCredencial(usuario.getPassword());
            String[] encript = cred.getSenhaCriptografada();
            usuario.setPassword(encript[0]);
            usuario.setSalt(encript[1]);
            ModelUsuario savedUser = usuarioRepository.save(usuario);

            return savedUser.getId();
        }

        return aux.get().getId();
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
        List<ModelUsuario> user = UsuarioDao.getUsuarioByEmail(usuarioRepository, email);
        if (user.isEmpty()) {
            throw new Exception("Erro: Id do usuário não encontrado");
        }
        return user.get(0);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) {
        usuarioRepository.deleteById(id);
    }

    @PostMapping("/autenticar")
    public Boolean autenticar(@RequestBody ModelCredencial credencial) {
        List<ModelUsuario> user = UsuarioDao.getUsuarioByEmail(usuarioRepository, credencial.getUserEmail());
        if (user.isEmpty()) {
            return false;
        }
        boolean valido = BCrypt.checkpw(credencial.getSenha(), user.get(0).getPassword());
        return valido;
    }

    @PutMapping("/user")
    public void updateUser(@Valid @RequestBody ModelUsuario user) {
        Optional<ModelUsuario> aux = usuarioRepository.findById(user.getId());

        if (!aux.isEmpty()) {
            ModelUsuario usuario = aux.get();
            usuario.setBairro(user.getBairro());
            usuario.setCep(user.getCep());
            usuario.setCidade(user.getCidade());
            usuario.setComplemento(user.getComplemento());
            usuario.setEstado(user.getEstado());
            usuario.setRua(user.getRua());
            usuario.setNumero(user.getNumero());
            usuarioRepository.save(usuario);
        } 
    }

}
