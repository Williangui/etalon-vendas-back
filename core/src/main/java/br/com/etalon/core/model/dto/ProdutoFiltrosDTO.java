package br.com.etalon.core.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoFiltrosDTO {

    private Long id;
    private String nome;
    private Double valorInicial;
    private Double valorFinal;
    private Long idCategoria;

    public String getNome() {
        if (nome == null) {
            nome = "";
        }
        return nome;
    }
}
