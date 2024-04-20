package academia.jpa;

import academia.model.ModelCarrinho;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CarrinhoRepository extends JpaRepository<ModelCarrinho, Integer> {

    List<ModelCarrinho> findByUsuarioOrderByIdAsc(Integer usuario);

    void deleteAllByUsuario(Integer usuario);

}
