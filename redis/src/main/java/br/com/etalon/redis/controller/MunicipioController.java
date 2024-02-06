package br.com.etalon.redis.controller;

import br.com.etalon.core.model.Municipio;
import br.com.etalon.redis.service.MunicipioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/municipio")
@RequiredArgsConstructor
public class MunicipioController {

    private final MunicipioService municipioService;

    @GetMapping("/buscarPorUf")
    public List<Municipio> buscarPorUf(@RequestParam Long idEstado) throws IOException {
        return municipioService.buscarPorUf(idEstado);
    }
}
