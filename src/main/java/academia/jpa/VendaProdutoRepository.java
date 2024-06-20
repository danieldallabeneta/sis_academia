package academia.jpa;

import academia.model.ModelVendaProduto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendaProdutoRepository extends JpaRepository<ModelVendaProduto, Integer>{
    
    @Query(value = "Select tbvendaproduto.* "
                   + "From tbvendaproduto "
                   + "join tbvenda"
                    + " on tbvenda.id = tbvendaproduto.caixa"
                  + "where tbvendaproduto.venda = :venda"
                    + "and tbvenda.tipovenda <> 3", nativeQuery = true)
    List<ModelVendaProduto> findAllByVendaOrder(@Param("venda") Integer venda);
    
    
}
