package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Produto;
import br.com.etalon.core.model.dto.ProdutoFiltrosDTO;
import br.com.etalon.core.model.dto.ProdutoListagemDTO;
import br.com.etalon.core.repository.jpa.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<ProdutoListagemDTO> listar(ProdutoFiltrosDTO filtros, Pageable pageable) {
        filtros.setNome(filtros.getNome().concat("%"));
        return repository.listar(filtros.getId(), filtros.getNome(),
                filtros.getValorInicial(), filtros.getValorFinal(), filtros.getIdCategoria(), pageable);
    }

    public List<Produto> buscarTodos() {
        List<Produto> list = repository.findAll();
        list.sort(Comparator.comparing(Produto::getNome));
        return list;
    }
}
