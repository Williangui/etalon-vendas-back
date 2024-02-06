package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Movimentacao;
import br.com.etalon.core.model.Produto;
import br.com.etalon.core.model.Usuario;
import br.com.etalon.core.model.dto.MovimentacaoFiltrosDTO;
import br.com.etalon.core.model.dto.MovimentacaoListagemDTO;
import br.com.etalon.core.model.enums.TipoMovimentacao;
import br.com.etalon.core.repository.jpa.MovimentacaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class MovimentacaoService {

    private final MovimentacaoRepository repository;

    private final TokenAuthenticationService tokenAuthenticationService;

    private final ProdutoService produtoService;

    public Movimentacao salvar(Movimentacao movimentacao, HttpServletRequest request) {
        Produto produto = produtoService.buscarPorId(movimentacao.getProduto().getId());
        movimentacao.setData(LocalDateTime.now());
        if (movimentacao.getTipo().equals(TipoMovimentacao.ENTRADA)) {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + movimentacao.getQuantidade());
        } else {
            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - movimentacao.getQuantidade());
        }
        produtoService.salvar(produto);
        movimentacao.setUsuario(tokenAuthenticationService.obterUsuarioLogado(request));
        return repository.save(movimentacao);
    }

    public Movimentacao buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<MovimentacaoListagemDTO> listar(MovimentacaoFiltrosDTO filtros, Pageable pageable) {
        if (filtros.getDataInicial() == null) {
            filtros.setDataInicial(LocalDateTime.now().minusDays(30));
            filtros.setDataFinal(LocalDateTime.now());
        }
        filtros.setDataInicial(filtros.getDataInicial().withHour(0).withMinute(0).withSecond(0));
        filtros.setDataFinal(filtros.getDataFinal().withHour(23).withMinute(59).withSecond(59));
        return repository.listar(filtros.getId(), filtros.getIdProduto(), filtros.getTipo(), filtros.getDataInicial(),
                filtros.getDataFinal(), filtros.getIdUsuario(), pageable);
    }
}
