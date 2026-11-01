package br.edu.dio.primeirospassos.app;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ConversorJson {
    //@Autowired
    //private Gson gson = new Gson(); // Implementação externa da biblioteca do google; precisa torna-lo um bean]

    @Autowired
    private Gson gson;

    public ViaCepResponse converter(String json) {
        ViaCepResponse response = gson.fromJson(json, ViaCepResponse.class);
        return response;

    }
}
