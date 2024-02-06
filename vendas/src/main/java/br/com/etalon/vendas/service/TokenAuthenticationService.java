package br.com.etalon.vendas.service;

import br.com.etalon.core.model.Usuario;
import br.com.etalon.vendas.exception.SecurityError;
import br.com.etalon.vendas.security.SecurityConstants;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TokenAuthenticationService {

    private static final String BEARER_PREFIX = "Bearer ";

    private final UsuarioService usuarioService;

    public Usuario obterUsuarioLogado(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            String token = authorizationHeader.substring(BEARER_PREFIX.length());
            try {
                String jwtSecret = SecurityConstants.SECRET;
                Algorithm algorithm = Algorithm.HMAC256(jwtSecret.getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(token);
                String username = decodedJWT.getSubject();
                return usuarioService.buscarPorUsuario(username);
            } catch (SecurityError e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
