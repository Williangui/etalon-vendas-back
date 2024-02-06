package br.com.etalon.redis.service;

import br.com.etalon.core.model.Estado;
import br.com.etalon.core.model.Municipio;
import br.com.etalon.core.repository.redis.MunicipioRepository;
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
import java.util.List;

@Service
@RequiredArgsConstructor
public class MunicipioService {

    private String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/";
    static int codigoSucesso = 200;

    private final MunicipioRepository municipioRepository;

    public List<Municipio> buscarPorUf(Long idEstado) throws IOException {
        List<Municipio> municipios = municipioRepository.findAllByEstadoId(idEstado);
        if (municipios.isEmpty()) {
            municipios = buscarMunicipios(idEstado);
        }
        return municipios;
    }

    private List<Municipio> buscarMunicipios(Long idEstado) throws IOException {
        URL url = new URL(this.url + idEstado + "/municipios");
        HttpURLConnection conexao = (HttpURLConnection) url.openConnection();
        if (conexao.getResponseCode() != codigoSucesso){
            throw new RuntimeException("HTTP error code : " + conexao.getResponseCode());
        }
        BufferedReader resposta = new BufferedReader(new InputStreamReader((conexao.getInputStream())));
        String jsonEmString = JsonUtils.converteJsonEmString(resposta);
        Gson gson = new Gson();
        List<Municipio> municipios = gson.fromJson(jsonEmString,new TypeToken<List<Municipio>>(){}.getType());
        municipios.forEach(mun -> {
            mun.setEstado(new Estado(idEstado));
        });
        return (List<Municipio>) this.municipioRepository.saveAll(municipios);
    }
}
