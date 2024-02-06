package br.com.etalon.core.model.dto;

import java.math.BigDecimal;

public interface ProdutoListagemDTO {

    Long getId();
    String getNome();
    BigDecimal getValor();
    String getCategoria();
}
