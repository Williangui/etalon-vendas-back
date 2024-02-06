package br.com.etalon.core.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "produto")
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false, name = "quantidade_estoque")
    private Integer quantidadeEstoque = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Categoria categoria;
}
