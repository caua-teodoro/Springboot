package br.edu.fundamentos;

public class AtivacaoClienteService {

    public void ativar(Cliente cliente){
        cliente.ativar();

        NotificacaoEmail notificacaoEmail = new NotificacaoEmail();
        NotificacaoSMS notificacaoSMS = new NotificacaoSMS();

        /*
        * nossa classe de serviço (AtivacaoClienteService) está
        * altamente acoplada às instâncias de notificação.
        * */

        notificacaoEmail.notificar(cliente, "Seu cadastro está ativo");
        notificacaoSMS.notificar(cliente, "Seu cadastro está ativo");

    }

}