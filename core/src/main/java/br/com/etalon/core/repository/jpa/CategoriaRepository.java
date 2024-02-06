package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT cat FROM Categoria cat " +
            "WHERE (:id IS NULL OR cat.id = :id) " +
            "AND (:descricao IS NULL OR cat.descricao LIKE :descricao)")
    Page<Categoria> listar(Long id, String descricao, Pageable pageable);
}
