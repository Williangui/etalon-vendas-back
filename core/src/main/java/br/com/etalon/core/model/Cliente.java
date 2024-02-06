package br.com.etalon.core.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cliente")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    private String telefone;

    @Column(name = "data_nascimento")
    private LocalDateTime dataNascimento;

    private String documento;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_cliente_id", referencedColumnName = "id")
    private EnderecoCliente enderecoCliente;
}
