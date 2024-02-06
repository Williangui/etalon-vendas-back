package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Cliente;
import br.com.etalon.core.model.dto.ClienteFiltrosDTO;
import br.com.etalon.vendas.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cliente")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping("/salvar")
    public Cliente salvar(@RequestBody Cliente cliente) {
        return clienteService.salvar(cliente);
    }

    @GetMapping("/buscarPorId/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        clienteService.excluir(id);
    }

    @PostMapping("/listar")
    public Page<Cliente> listar(@RequestBody ClienteFiltrosDTO filtros, Pageable pageable) {
        return clienteService.listar(filtros, pageable);
    }
}
