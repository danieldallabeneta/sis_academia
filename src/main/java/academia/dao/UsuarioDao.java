package academia.dao;

import academia.jpa.UsuarioRepository;
import academia.model.ModelUsuario;
import java.util.List;
import org.springframework.data.domain.Example;

public class UsuarioDao {

    public static List<ModelUsuario> getUsuarioByEmail(UsuarioRepository repo, String email) {
        ModelUsuario usuario = new ModelUsuario();
        usuario.setEmail(email);
        Example<ModelUsuario> usuarioExample = Example.of(usuario);
        List<ModelUsuario> res = repo.findAll(usuarioExample);
        return res;
    }

}
