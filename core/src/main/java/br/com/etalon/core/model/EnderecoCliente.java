package br.com.etalon.core.model;

import br.com.etalon.core.model.dto.EnderecoCepDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "endereco_cliente")
public class EnderecoCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String logradouro;

    private String numero;

    @Column(nullable = false)
    private String bairro;

    private String complemento;

    @Column(name = "ponto_referencia")
    private String pontoReferencia;

    @Column(nullable = false)
    private String cep;

    @Column(name = "id_municipio")
    private Long idMunicipio;

    @Column(name = "id_estado")
    private Long idEstado;

    private String latitude;

    private String longitude;

    public EnderecoCliente() {
    }

    public EnderecoCliente(EnderecoCepDTO endereco) {
        logradouro = endereco.getLogradouro();
        bairro = endereco.getBairro();
        complemento = endereco.getComplemento();
        cep = endereco.getCep().replace("-", "");
        idMunicipio = endereco.getIbge();
    }

}

