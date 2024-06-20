package academia.jpa;

import academia.model.ModelAlunoMensalidade;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AlunoMensalidadeRepository extends JpaRepository<ModelAlunoMensalidade, Integer>{
    
    @Query(value = "Select * "
                   + "From tbalunomensalidade "
                  + "where aluno = :aluno"
                  + "order by data", nativeQuery = true)
    List<ModelAlunoMensalidade> findAllByAlunoOrderByData(@Param("aluno") Integer aluno);
    
    @Query(value = "Select * "
                   + "From tbalunomensalidade "
                  + "where loja = :loja "
                  + "order by data", nativeQuery = true)
    List<ModelAlunoMensalidade> findAllByLojaOrderByData(@Param("loja") Integer loja);
    
    
}
