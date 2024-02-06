package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Movimentacao;
import br.com.etalon.core.model.dto.MovimentacaoFiltrosDTO;
import br.com.etalon.core.model.dto.MovimentacaoListagemDTO;
import br.com.etalon.vendas.service.MovimentacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/movimentacao")
@RequiredArgsConstructor
public class MovimentacaoController {

    private final MovimentacaoService service;

    @PostMapping("/salvar")
    public Movimentacao salvar(@RequestBody Movimentacao movimentacao, HttpServletRequest request) {
        return service.salvar(movimentacao, request);
    }

    @GetMapping("/buscarPorId/{id}")
    public Movimentacao buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/listar")
    public Page<MovimentacaoListagemDTO> listar(@RequestBody MovimentacaoFiltrosDTO filtros, Pageable pageable) {
        return service.listar(filtros, pageable);
    }
}
