package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Venda;
import br.com.etalon.core.model.dto.VendaFiltrosDTO;
import br.com.etalon.vendas.service.VendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/venda")
@RequiredArgsConstructor
public class VendaController {

    private final VendaService service;

    @PostMapping("/salvar")
    public Venda salvar(@RequestBody Venda venda) {
        return service.salvar(venda);
    }

    @GetMapping("/buscarPorId/{id}")
    public Venda buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/listar")
    public Page<Venda> listar(@RequestBody VendaFiltrosDTO filtros, Pageable pageable) {
        return service.listar(filtros, pageable);
    }
}
