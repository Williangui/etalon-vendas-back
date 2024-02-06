package br.com.etalon.core.repository.redis;

import br.com.etalon.core.model.Municipio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunicipioRepository extends CrudRepository<Municipio, Long> {

    List<Municipio> findAllByEstadoId(Long idEstado);

    Municipio findByNomeAndEstadoId(String nome, Long idEstado);
}
