package br.com.etalon.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaFiltrosDTO {

    private Long id;

    private String descricao;

    public String getDescricao() {
        if (descricao == null) {
            descricao = "";
        }
        return descricao;
    }
}
