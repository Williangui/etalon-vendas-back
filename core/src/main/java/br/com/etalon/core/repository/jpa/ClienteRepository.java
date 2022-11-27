package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("SELECT cli FROM Cliente cli " +
            "WHERE (:id IS NULL OR cli.id = :id) " +
            "AND (:nome IS NULL OR cli.nome LIKE :nome) " +
            "AND (:email IS NULL OR cli.email LIKE :email) " +
            "AND (:documento IS NULL OR cli.documento LIKE :documento)")
    Page<Cliente> listar(Long id, String nome, String documento, String email, Pageable pageable);
}
