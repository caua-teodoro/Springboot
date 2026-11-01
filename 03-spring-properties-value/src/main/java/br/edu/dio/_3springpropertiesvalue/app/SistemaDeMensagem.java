package br.edu.dio._3springpropertiesvalue.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SistemaDeMensagem implements CommandLineRunner {

    @Value("${nome:NoReply-DIO}") // o pós : é o padrão caso o value nao encontrado
    private String nome;
    @Value("${email}")
    private String email;
    @Value("${telefone}")
    private List<Long> telefones;

    @Override
    public void run(String[] args) throws Exception {
        System.out.println("Mensagem enviada por " + nome +
                "\nEmai: " + email +
                "\nCom telefones para contato: " + telefones);
        System.out.println("Seu cadastro foi aprovado");
    }
}
