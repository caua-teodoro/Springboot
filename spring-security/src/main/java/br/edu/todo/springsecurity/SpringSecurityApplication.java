package br.edu.todo.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication agrega três anotações essenciais do Spring:
// 1. @Configuration: permite registrar Beans extras no contexto.
// 2. @EnableAutoConfiguration: configura automaticamente Web, JPA e Spring Security com base no pom.xml.
// 3. @ComponentScan: varre este pacote (br.edu.todo.springsecurity) e subpacotes procurando @Component, @RestController, @Repository, @Configuration, etc.
@SpringBootApplication
public class SpringSecurityApplication {

    public static void main(String[] args) {
        // Inicializa o container do Spring, sobe o servidor Tomcat embutido (porta 8080 por padrão)
        // e dispara os CommandLineRunners registrados (como a classe StartApplication).
        SpringApplication.run(SpringSecurityApplication.class, args);
    }

}
