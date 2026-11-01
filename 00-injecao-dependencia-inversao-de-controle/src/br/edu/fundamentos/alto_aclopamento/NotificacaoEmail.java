package br.edu.fundamentos;

public class NotificacaoEmail {
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("%s foi enviado uma mensagem para o e-mail %s: %s\n",
                cliente.getNome(), cliente.getEmail(), mensagem);
    }
}
