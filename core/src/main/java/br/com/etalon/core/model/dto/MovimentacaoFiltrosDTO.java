package br.com.etalon.core.model.dto;

import br.com.etalon.core.model.enums.TipoMovimentacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovimentacaoFiltrosDTO {
    private Long id;
    private Long idProduto;
    private TipoMovimentacao tipo;
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInicial = LocalDateTime.now().minusDays(30);
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataFinal = LocalDateTime.now();
    private Long idUsuario;
}
