package br.edu.fundamentos.alto_acoplamento;

import br.edu.fundamentos.Cliente;

public class NotificacaoEmail {
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("%s foi enviado uma mensagem para o e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
