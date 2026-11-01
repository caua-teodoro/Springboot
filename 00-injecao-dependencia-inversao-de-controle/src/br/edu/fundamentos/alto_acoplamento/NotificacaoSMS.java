package br.edu.fundamentos.alto_acoplamento;

import br.edu.fundamentos.Cliente;

public class NotificacaoSMS {
    public void notificar(Cliente cliente, String mensagem){
        System.out.printf("%s foi enviado uma SMS para o telefone %s: %s\n",
                cliente.getNome(), cliente.getTelefone(), mensagem);
    }
}
