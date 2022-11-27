package br.com.etalon.core.model;

import br.com.etalon.core.model.dto.EnderecoCepDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String logradouro;

    private String numero;

    private String bairro;

    private String complemento;

    private String pontoReferencia;

    private String cep;

    private Long idMunicipio;

    private Long idEstado;

    public EnderecoCliente(EnderecoCepDTO endereco) {
        logradouro = endereco.getLogradouro();
        bairro = endereco.getBairro();
        complemento = endereco.getComplemento();
        cep = endereco.getCep().replace("-", "");
        idMunicipio = endereco.getIbge();
    }
}
