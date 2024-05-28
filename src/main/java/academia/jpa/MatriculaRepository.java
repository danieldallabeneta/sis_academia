package academia.jpa;

import academia.model.ModelMatricula;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatriculaRepository extends JpaRepository<ModelMatricula, Integer>{
    
    @Query(value = "Select tbmatricula.*"
                  + "From tbmatricula "
                  + "join tbaluno "
                    + "on tbaluno.id = tbmatricula.aluno "
                 + "where tbmatricula.loja = :loja "
                 + "Order by tbaluno.nome", nativeQuery = true)
    List<ModelMatricula> findAllByLojaOrderByAluno(@Param("loja") Integer loja);
    
    @Query(value = "Select * From tbmatricula where loja = :loja and aluno = :aluno", nativeQuery = true)
    ModelMatricula findFirstByAlunoAndLoja(@Param("loja") Integer loja, @Param("aluno") Integer aluno);
}
