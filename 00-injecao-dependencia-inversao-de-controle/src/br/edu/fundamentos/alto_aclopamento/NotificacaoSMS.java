package br.edu.fundamentos;

public class NotificacaoSMS {
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("%s foi enviado uma SMS para o telefone %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
