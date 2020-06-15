package br.principal;

import br.service.ComunicadorTCP;
import br.service.ControlePersistir;
import java.io.IOException;
import java.util.ArrayList;

public class TCPServer {

    public static void main(String[] args) throws IOException, Exception {
        int porta = 6789;
        String mensagem = "";
        ControlePersistir controleTCP = new ControlePersistir();
        System.out.println("Servidor no Ar!");
        while (true) {
            ComunicadorTCP comunicacao = new ComunicadorTCP(porta);
            mensagem = comunicacao.receberMensagem();
            System.out.println("Mensagem: " + mensagem);
            controleTCP.metodosDeControle(mensagem);
            if (mensagem.contains("get")) {
                comunicacao.enviarMensagemGet((ArrayList<String>) controleTCP.getTXT(mensagem));
                System.out.println("Mensagem Enviada: " + controleTCP.getTXT(mensagem));
            }
            comunicacao.getServerSocket().close();
        }
    }
}
