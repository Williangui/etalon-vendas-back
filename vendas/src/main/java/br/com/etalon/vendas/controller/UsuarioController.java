package br.com.etalon.vendas.controller;

import br.com.etalon.core.model.Usuario;
import br.com.etalon.core.model.dto.UsuarioFiltrosDTO;
import br.com.etalon.core.model.dto.UsuarioListagemDTO;
import br.com.etalon.vendas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping("/salvar")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return service.salvar(usuario);
    }

    @GetMapping("/buscarPorId/{id}")
    public Usuario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @PostMapping("/listar")
    public Page<UsuarioListagemDTO> listar(@RequestBody UsuarioFiltrosDTO filtros, Pageable pageable) {
        return service.listar(filtros, pageable);
    }

    @PostMapping("/ativarInativar")
    public Usuario ativarInativar(@RequestBody Usuario usuario) {
        return service.ativarInativar(usuario);
    }

    @GetMapping("/buscarTodos")
    public List<Usuario> buscarTodos() {
        return service.buscarTodos();
    }
}
