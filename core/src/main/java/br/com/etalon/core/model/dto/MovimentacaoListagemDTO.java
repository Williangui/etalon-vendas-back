package br.com.etalon.core.model.dto;

import java.time.LocalDateTime;

public interface MovimentacaoListagemDTO {

    Long getId();
    String getProduto();
    LocalDateTime getData();
    String getTipo();
}
