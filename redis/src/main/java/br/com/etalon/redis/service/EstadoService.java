package br.com.etalon.redis.service;

import br.com.etalon.core.model.Estado;
import br.com.etalon.core.repository.redis.EstadoRepository;
import br.com.etalon.core.util.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstadoService {

    static String apiLocalidades = "https://servicodados.ibge.gov.br/api/v1/localidades/estados";
    static int codigoSucesso = 200;

    private final EstadoRepository estadoRepository;

    public List<Estado> findAll() throws IOException {
        List<Estado> estados = (List<Estado>) estadoRepository.findAll();
        if (estados.isEmpty()) {
            estados = buscarEstados();
        }
        estados.sort(Comparator.comparing(Estado::getNome));
        return estados;
    }

    private List<Estado> buscarEstados() throws IOException {
        URL url = new URL(apiLocalidades);
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if (conexao.getResponseCode() != codigoSucesso){
            throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
        }
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = JsonUtils.converteJsonEmString(resposta);
        Gson gson = new Gson();
        List<Estado> estados = gson.fromJson(jsonEmString,new TypeToken<List<Estado>>(){}.getType());
        return (List<Estado>) this.estadoRepository.saveAll(estados);
    }

    public Estado buscarPorUf(String uf) {
        return estadoRepository.findBySigla(uf.toUpperCase());
    }
}
