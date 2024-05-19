package academia.jpa;

import academia.model.ModelBanco;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BancoRepository extends JpaRepository<ModelBanco, Integer>{
    
    @Query(value = "Select * From tbbanco where loja = :loja Order by id", nativeQuery = true)
    List<ModelBanco> findAllByLojaOrderById(@Param("loja") Integer loja);
}
