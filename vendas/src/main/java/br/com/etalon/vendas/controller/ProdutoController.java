package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Produto;
import br.com.etalon.core.model.dto.ProdutoFiltrosDTO;
import br.com.etalon.core.model.dto.ProdutoListagemDTO;
import br.com.etalon.vendas.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;

    @PostMapping("/salvar")
    public Produto salvar(@RequestBody Produto produto) {
        return produtoService.salvar(produto);
    }

    @GetMapping("/buscarPorId/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        produtoService.excluir(id);
    }

    @PostMapping("/listar")
    public Page<ProdutoListagemDTO> listar(@RequestBody ProdutoFiltrosDTO filtros, Pageable pageable) {
        return produtoService.listar(filtros, pageable);
    }

    @GetMapping("/buscarTodos")
    public List<Produto> buscarTodos() {
        return produtoService.buscarTodos();
    }
}
