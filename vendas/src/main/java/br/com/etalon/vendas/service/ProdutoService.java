package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Produto;
import br.com.etalon.core.model.dto.ProdutoFiltrosDTO;
import br.com.etalon.core.repository.jpa.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        produtoRepository.deleteById(id);
    }

    public Page<Produto> listar(ProdutoFiltrosDTO filtros, Pageable pageable) {
        filtros.setNome(filtros.getNome().concat("%"));
        return produtoRepository.listar(filtros.getId(), filtros.getNome(),
                filtros.getValorInicial(), filtros.getValorFinal(), pageable);
    }
}
