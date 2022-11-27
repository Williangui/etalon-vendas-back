package br.com.etalon.core.model.dto;

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
    private String nome;
    private String usuario;

    public String getNome() {
        if (nome == null) {
            nome = "";
        }
        return nome;
    }

    public String getUsuario() {
        if (usuario == null) {
            usuario = "";
        }
        return usuario;
    }
}
