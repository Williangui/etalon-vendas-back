package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.EnderecoCliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoClienteRepository extends JpaRepository<EnderecoCliente, Long> {
}
