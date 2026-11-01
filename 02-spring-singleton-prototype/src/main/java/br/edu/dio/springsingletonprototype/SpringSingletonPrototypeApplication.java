package br.edu.dio.springsingletonprototype;

import br.edu.dio.springsingletonprototype.app.SistemaDeMensagem;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringSingletonPrototypeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSingletonPrototypeApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(SistemaDeMensagem sistema) throws Exception {
      return args -> {
        sistema.enviarConfirmacaoCadastro();
        sistema.enviarMensagemBoasVindas();
        sistema.enviarConfirmacaoCadastro();
      };
    }
}
