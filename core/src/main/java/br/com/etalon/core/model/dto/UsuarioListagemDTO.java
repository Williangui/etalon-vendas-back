package br.com.etalon.core.model.dto;

import br.com.etalon.core.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioListagemDTO {

    private Long id;
    private String tipo;
    private String usuario;
    private String ativo;

    public UsuarioListagemDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.tipo = usuario.getTipo().name();
        this.usuario = usuario.getUsuario();
        this.ativo = usuario.getAtivo() ? "Sim" : "Não";
    }
}
