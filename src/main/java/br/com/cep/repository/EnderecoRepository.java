package br.com.cep.repository;

import br.com.cep.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {

    Endereco findByCep(String cep);
    @Query("select case when  (count(1) > 0) then true else false end from Endereco where cep = :cep")
    boolean hasCep(@Param("cep") String cep);

}
