package br.com.etalon.core.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;

@Data
@RedisHash("Municipio")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Municipio {

    private Long id;

    private String nome;

    private Estado estado;
}
