package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Venda;
import br.com.etalon.core.model.dto.VendaFiltrosDTO;
import br.com.etalon.core.repository.jpa.VendaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class VendaService {

    private final VendaRepository repository;

    public Venda salvar(Venda venda) {
        return repository.save(venda);
    }

    public Venda buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<Venda> listar(VendaFiltrosDTO filtros, Pageable pageable) {
        if (filtros.getDataInicial() == null) {
            filtros.setDataInicial(LocalDateTime.now().minusDays(30));
            filtros.setDataFinal(LocalDateTime.now());
        }
        filtros.setDataInicial(filtros.getDataInicial().withHour(0).withMinute(0).withSecond(0));
        filtros.setDataFinal(filtros.getDataFinal().withHour(23).withMinute(59).withSecond(59));
        return repository.listar(filtros.getId(), filtros.getDataInicial(), filtros.getDataFinal(),
                filtros.getValorInicial(), filtros.getValorFinal(), filtros.getIdCliente(), filtros.getIdProduto(), pageable);
    }


}
