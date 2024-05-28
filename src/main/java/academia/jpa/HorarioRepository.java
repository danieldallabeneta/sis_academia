package academia.jpa;

import academia.model.ModelHorario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HorarioRepository extends JpaRepository<ModelHorario, Integer>{
    
    @Query(value = "Select * From tbhorario where loja = :loja Order by id", nativeQuery = true)
    List<ModelHorario> findAllByLojaOrderById(@Param("loja") Integer loja);
    
    @Query(value = "Select tbhorario.* From tbhorario join tbatividade on tbatividade.id = tbhorario.atividade where tbatividade.loja = :loja and capacidade > 0 Order by tbatividade.nome", nativeQuery = true)
    List<ModelHorario> findAllByLojaAndCapacidadeOrderById(@Param("loja") Integer loja);
    
}
