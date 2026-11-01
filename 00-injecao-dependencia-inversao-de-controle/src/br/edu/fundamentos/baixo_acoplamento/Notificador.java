package br.edu.fundamentos.baixo_acoplamento;

import br.edu.fundamentos.Cliente;

public interface Notificador {

    void notificar(Cliente cLiente, String mensagem);

    /*
    *  Resumidamente, uma interface define um contrato, ou seja, um conjunto de métodos
    *  que qualquer classe que a implemente deve obrigatoriamente fornecer.
    *
    * */
}
