package br.com.etalon.core.repository.jpa;

import br.com.etalon.core.model.Usuario;
import br.com.etalon.core.model.dto.UsuarioListagemDTO;
import br.com.etalon.core.model.enums.TipoUsuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("SELECT new br.com.etalon.core.model.dto.UsuarioListagemDTO(usr)  FROM Usuario usr " +
            "WHERE (:id IS NULL OR usr.id = :id) " +
            "AND (:tipo IS NULL OR usr.tipo = :tipo) " +
            "AND (:usuario IS NULL OR usr.usuario LIKE :usuario) ")
    Page<UsuarioListagemDTO> listar(Long id, TipoUsuario tipo, String usuario, Pageable pageable);

    @Query("SELECT usr FROM Usuario usr where usr.usuario = :username ")
    Usuario findByUsuario(String username);

    @Modifying
    @Query("UPDATE Usuario SET ativo = :ativo WHERE id = :id")
    Usuario ativarInativar(Boolean ativo);
}
