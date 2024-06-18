package academia.jpa;

import academia.model.ModelVenda;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VendaRepository extends JpaRepository<ModelVenda, Integer>{
    
    @Query(value = "Select * From tbvenda where loja = :loja Order by data", nativeQuery = true)
    List<ModelVenda> findAllByLojaOrderByDate(@Param("loja") Integer loja);
    
    @Query(value = "Select * From tbvenda where caixa = :caixa", nativeQuery = true)
    List<ModelVenda> findAllByCaixa(@Param("caixa") Integer caixa);
    
}
