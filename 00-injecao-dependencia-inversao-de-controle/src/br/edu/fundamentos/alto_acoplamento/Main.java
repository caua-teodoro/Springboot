package br.edu.fundamentos.alto_acoplamento;

import br.edu.fundamentos.Cliente;

public class Main {
    public static void main(String[] args) {

        AtivacaoClienteService ativacaoClienteService = new AtivacaoClienteService();

        Cliente cliente = new Cliente("Caua", "caua@hotmail.com", "994454677");

        ativacaoClienteService.ativar(cliente);

    }
}