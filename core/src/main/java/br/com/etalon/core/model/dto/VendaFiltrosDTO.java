package br.com.etalon.core.model.dto;

import br.com.etalon.core.model.Cliente;
import br.com.etalon.core.model.Produto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendaFiltrosDTO {

    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataInicial = null;

    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dataFinal = null;

    private BigDecimal valorInicial = null;

    private BigDecimal valorFinal = null;

    private Long idCliente = null;

    private Long idProduto = null;
}
