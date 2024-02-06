package br.com.etalon.core.model.dto;

import br.com.etalon.core.model.enums.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioFiltrosDTO {
    private Long id;
    private TipoUsuario tipo;
    private String usuario;
    public String getUsuario() {
        if (usuario == null) {
            usuario = "";
        }
        return usuario;
    }
}
