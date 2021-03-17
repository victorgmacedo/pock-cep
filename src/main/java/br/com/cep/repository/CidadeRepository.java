package br.com.cep.repository;

import br.com.cep.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    @Query("select case when  (count(1) > 0) then true else false end from Cidade where ibge = :ibge")
    boolean hasIbge(@Param("ibge") Long ibge);
    @Query("select case when  (count(1) > 0) then true else false end from Cidade where id = :id")
    boolean cityExists(@Param("id") Long id);
}
