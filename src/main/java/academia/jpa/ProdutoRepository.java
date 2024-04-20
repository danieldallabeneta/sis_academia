package academia.jpa;

import academia.model.ModelProduto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ModelProduto, Integer> {

}
