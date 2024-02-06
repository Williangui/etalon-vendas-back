package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Categoria;
import br.com.etalon.core.model.dto.CategoriaFiltrosDTO;
import br.com.etalon.core.repository.jpa.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository repository;

    public Categoria salvar(Categoria categoria) {
        return repository.save(categoria);
    }

    public Categoria buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<Categoria> listar(CategoriaFiltrosDTO filtros, Pageable pageable) {
        filtros.setDescricao(filtros.getDescricao().concat("%"));
        return repository.listar(filtros.getId(), filtros.getDescricao(), pageable);
    }

    public List<Categoria> buscarTodas() {
        return repository.findAll();
    }
}
