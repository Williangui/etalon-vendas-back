package br.com.etalon.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteFiltrosDTO {

    private Long id;

    private String nome;

    private String email;

    private String documento;

    public String getNome() {
        if (nome == null) {
            nome = "";
        }
        return nome;
    }

    public String getEmail() {
        if (email == null) {
            email = "";
        }
        return email;
    }

    public String getDocumento() {
        if (documento == null ) {
            documento = "";
        }
        return documento;
    }
}
