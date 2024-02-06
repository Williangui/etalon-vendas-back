package br.com.etalon.core.model;

import br.com.etalon.core.model.enums.TipoUsuario;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String usuario;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private Boolean ativo = Boolean.FALSE;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao",
            joinColumns = {@JoinColumn(name = "usuario_id")},
            inverseJoinColumns = {@JoinColumn(name = "permissao_id")})
    private List<Permissao> permissoes = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private TipoUsuario tipo;
}
