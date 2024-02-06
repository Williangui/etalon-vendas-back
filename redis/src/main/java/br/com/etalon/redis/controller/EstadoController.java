package br.com.etalon.redis.controller;

import br.com.etalon.core.model.Estado;
import br.com.etalon.redis.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/estado")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService estadoService;

    @GetMapping("/buscarTodos")
    public List<Estado> findAll() throws IOException {
        return estadoService.findAll();
    }
}
