package br.edu.dio.springsingletonprototype.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SistemaDeMensagem {
    @Autowired
    private Remetente noReply;

    @Autowired
    private Remetente techTeam;

    public void enviarConfirmacaoCadastro() {
        System.out.println(noReply);
        System.out.println("Seu cadastro foi aprovado com sucesso");
    }

    public void enviarMensagemBoasVindas() {
        techTeam.setEmail("tech@dio.com.br");
        System.out.println(techTeam);
        System.out.println("Bem-vindo à tech Elite");
    }
}
