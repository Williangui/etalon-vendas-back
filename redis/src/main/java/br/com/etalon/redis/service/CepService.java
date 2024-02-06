package br.com.etalon.redis.service;

import br.com.etalon.core.model.EnderecoCliente;
import br.com.etalon.core.model.Estado;
import br.com.etalon.core.model.dto.EnderecoCepDTO;
import br.com.etalon.core.util.JsonUtils;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@RequiredArgsConstructor
public class CepService {

    private final EstadoService estadoService;

    private final MunicipioService municipioService;

    static String viacepUrl = "https://viacep.com.br/ws/";
    static int codigoSucesso = 200;

    public EnderecoCliente buscarPorCep(String cep) throws IOException {
        URL url = new URL(viacepUrl + cep + "/json");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if (conexao.getResponseCode() != codigoSucesso){
            throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
        }
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = JsonUtils.converteJsonEmString(resposta);
        Gson gson = new Gson();
        EnderecoCepDTO endereco = gson.fromJson(jsonEmString, EnderecoCepDTO.class);
        EnderecoCliente enderecoCliente = new EnderecoCliente(endereco);
        Estado estado = estadoService.findAll().stream().filter(est -> est.getSigla().equals(endereco.getUf())).findFirst().orElse(null);
        enderecoCliente.setIdEstado(estado.getId());
        return enderecoCliente;
    }
}
