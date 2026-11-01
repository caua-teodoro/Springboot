package br.edu.dio.primeirospassos;

import br.edu.dio.primeirospassos.app.ConversorJson;
import br.edu.dio.primeirospassos.app.ViaCepResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PrimeirosPassosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrimeirosPassosApplication.class, args);
    }

    // É um Bean, porque não é uma implementação (classe ou interface) da minha aplicação.
    @Bean
    public CommandLineRunner init(ConversorJson conversor) {
        return args -> {
            String json = "{\"cep\": \"01001-000\", \"logradouro\": \"Praça da Sé\", \"localidade\": \"tal lugar\"}";
            ViaCepResponse response = conversor.converter(json);
            System.out.println("Dados do CEP: " + response);
        };
    }

}
