package br.com.etalon.core.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "permissao")
public class Permissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;
}
