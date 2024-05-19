package academia.jpa;

import academia.model.ModelProduto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProdutoRepository extends JpaRepository<ModelProduto, Integer> {

    @Query(value = "Select * From tbproduto where loja = :loja Order by id", nativeQuery = true)
    List<ModelProduto> findAllByLojaOrderById(@Param("loja") Integer loja);
    
}
