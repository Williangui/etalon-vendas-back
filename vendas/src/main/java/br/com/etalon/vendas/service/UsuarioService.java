package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Usuario;
import br.com.etalon.core.model.dto.UsuarioFiltrosDTO;
import br.com.etalon.core.model.dto.UsuarioListagemDTO;
import br.com.etalon.core.repository.jpa.UsuarioRepository;
import br.com.etalon.vendas.exception.SecurityError;
import br.com.etalon.vendas.security.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class UsuarioService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = null;
        try {
            usuario = buscarPorUsuario(username);
        } catch (SecurityError e) {
            throw new RuntimeException(e);
        }
        Collection<SimpleGrantedAuthority> permissoes = new ArrayList<>();
        usuario.getPermissoes().forEach(permissao -> permissoes.add(new SimpleGrantedAuthority(permissao.getDescricao())));
        return new User(usuario.getUsuario(), usuario.getSenha(), permissoes);
    }

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(SecurityConfig.passwordEncoder().encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public Usuario buscarPorId(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void excluir(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario buscarPorUsuario(String usr) throws SecurityError {
        Usuario usuario = usuarioRepository.findByUsuario(usr);
        if (usuario == null) {
            throw new SecurityError("Usu??rio n??o encontrado");
        } else if (!usuario.getAtivo()) {
            throw new SecurityError("Erro ao logar, o Usu??rio est?? inativo.");
        }
        return usuario;
    }

    public Page<UsuarioListagemDTO> listar(UsuarioFiltrosDTO filtros, Pageable pageable) {
        filtros.setNome(filtros.getNome().concat("%"));
        filtros.setUsuario(filtros.getUsuario().concat("%"));
        return usuarioRepository.listar(filtros.getId(), filtros.getNome(),
                filtros.getUsuario(), pageable);
    }

    public Usuario ativarInativar(Usuario usuario) {
        return usuarioRepository.ativarInativar(usuario.getAtivo());
    }
}
