package br.edu.dio.primeirospassos.app;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Beans: Classes externas que precisarão ser gerenciadas pelo Spring

/*
* Quando usar Bean: Não tenho acesso ao código fonte
*
* Component: Quando é uma classe que tem a possibilidade ou finalidade de ser
*  provida atráves do conceito de Injestão de Dependência
* */
@Configuration
public class Beans {
    @Bean
    public Gson gson() {
        return new Gson();
    }
}
