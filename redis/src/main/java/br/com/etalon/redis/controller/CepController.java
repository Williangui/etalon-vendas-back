package br.com.etalon.redis.controller;

import br.com.etalon.core.model.EnderecoCliente;
import br.com.etalon.redis.service.CepService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
public class CepController {

    private final CepService cepService;

    @GetMapping("/buscarPorCep")
    public EnderecoCliente buscarPorCep(@RequestParam String cep) throws IOException {
        return cepService.buscarPorCep(cep);
    }
}
