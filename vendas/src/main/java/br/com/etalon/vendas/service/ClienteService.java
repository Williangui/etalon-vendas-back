package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Cliente;
import br.com.etalon.core.model.dto.ClienteFiltrosDTO;
import br.com.etalon.core.repository.jpa.ClienteRepository;
import br.com.etalon.core.util.CpfUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    public Cliente salvar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Page<Cliente> listar(ClienteFiltrosDTO filtros, Pageable pageable) {
        if (filtros.getNome() != null) {
            filtros.setNome(filtros.getNome().concat("%"));
        }
        if (filtros.getEmail() != null) {
            filtros.setEmail(filtros.getEmail().concat("%"));
        }
        if (filtros.getDocumento() != null) {
            filtros.setDocumento(filtros.getDocumento().concat("%"));
        }
        Page<Cliente> page = repository.listar(filtros.getId(), filtros.getNome(),
                filtros.getDocumento(), filtros.getEmail(), pageable);

        page.getContent().forEach(cli -> {
            try {
                cli.setDocumento(CpfUtil.formatarCpfCnpj(cli.getDocumento()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        return page;
    }
}
