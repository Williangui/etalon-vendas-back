package br.com.etalon.core.repository.redis;

import br.com.etalon.core.model.Estado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends CrudRepository<Estado, Long> {

    Estado findBySigla(String sigla);
}
