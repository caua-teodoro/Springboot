package br.edu.fundamentos.baixo_acoplamento;

import br.edu.fundamentos.Cliente;

public class AtivacaoClienteServiceDesaclopado {

    private Notificador notificador;

    /*
    * criamos uma dependência em que só conseguiremos instanciar
    *  a classe “AtivacaoClienteServiceDesaclopado”
    *  se a interface “Notificador” for passada como argumento
    *  no momento da criação do serviço.*/
    public AtivacaoClienteServiceDesaclopado(Notificador notificador){
        this.notificador = notificador;
    }

    public void ativar(Cliente cliente){
        cliente.ativar();
        notificador.notificar(cliente, "Seu cadastro está ativo");
    }

    /*
    *  A classe de serviço não é mais responsável por criar as instâncias;
    *  fizemos uma inversão no responsável por esse controle.
    *
    *
    * Retiramos a responsabilidade do serviço de criar essas instâncias e
    * , em vez disso, apenas indicamos que ele precisará notificar algo
    *  por meio da interface “Notificador”.
    *  Não importa se será por e-mail, SMS ou WhatsApp;
    *  o serviço tem apenas a responsabilidade de notificar.
    * */
}
