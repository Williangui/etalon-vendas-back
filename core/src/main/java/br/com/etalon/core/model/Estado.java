package br.com.etalon.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Estado")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Estado {

    private Long id;

    private String nome;

    private String sigla;

    public Estado(Long id) {
        this.id = id;
    }
}
