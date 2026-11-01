package br.edu.fundamentos.baixo_acoplamento;

import br.edu.fundamentos.Cliente;

public class Main2 {
    public static void main(String[] args) {
        Notificador notificador = new NotificacaoEmail();
        AtivacaoClienteServiceDesaclopado ativacaoClienteService = new AtivacaoClienteServiceDesaclopado(notificador);

        Cliente cliente = new Cliente("Caua", "caua@hotmail.com", "994454677");

        ativacaoClienteService.ativar(cliente);
    }
}
