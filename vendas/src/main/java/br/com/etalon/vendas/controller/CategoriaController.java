package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Categoria;
import br.com.etalon.core.model.dto.CategoriaFiltrosDTO;
import br.com.etalon.vendas.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService service;

    @PostMapping("/salvar")
    public Categoria salvar(@RequestBody Categoria categoria) {
        return service.salvar(categoria);
    }

    @GetMapping("/buscarPorId/{id}")
    public Categoria buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/listar")
    public Page<Categoria> listar(@RequestBody CategoriaFiltrosDTO filtros, Pageable pageable) {
        return service.listar(filtros, pageable);
    }

    @GetMapping("/buscarTodas")
    public List<Categoria> buscarTodas() {
        return service.buscarTodas();
    }


}
