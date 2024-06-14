package academia.jpa;

import academia.model.ModelCaixa;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CaixaRepository extends JpaRepository<ModelCaixa, Integer>{
 
    @Query(value = "Select * From tbcaixa where loja = :loja Order by data", nativeQuery = true)
    List<ModelCaixa> findAllByLojaOrderByDate(@Param("loja") Integer loja);
    
}
