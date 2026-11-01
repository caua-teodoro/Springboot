package br.edu.dio.springdatajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        new org.springframework.boot.builder.SpringApplicationBuilder(Application.class)
                .headless(false)
                .run(args);
    }

//    // Ao inicializar o container o CommandLineRunner é executado
//    public CommandLineRunner init(UserRepository userRepository) {
//
//    }
}
