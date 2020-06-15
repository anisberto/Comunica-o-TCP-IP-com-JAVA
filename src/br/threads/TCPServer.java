package br.threads;

import br.service.ControlePersistir;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer implements Runnable {

    private ServerSocket serverSocket = null;
    private boolean canRun = true;

    public TCPServer() throws IOException {
        this(6789);
    }

    public ServerSocket getServerSocket() throws IOException {
        return this.serverSocket;
    }

    public TCPServer(int port) throws IOException {
        this.serverSocket = new ServerSocket(port);

    }

    @Override
    public void run() {
        System.out.println("TCP server no ar..");
        String mensagem;
        while (!Thread.interrupted() && canRun) {
            try {
                ControlePersistir incluir = new ControlePersistir();

                Socket socket = this.serverSocket.accept();
                System.out.println("Cliente conectado: " + socket.getRemoteSocketAddress());
                ComunicadorTCP comunicadorTCP = new ComunicadorTCP(socket);
                mensagem = comunicadorTCP.receberMensagem();

                if (comunicadorTCP.receberMensagem().contains("get")) {
                    //String mensagemRespon = incluir.getTXT(mensagem);
                    //comunicadorTCP.enviarMensagem(mensagemRespon);
                }

                System.out.println("Resposta: " + mensagem);
                incluir.metodosDeControle(mensagem);

                comunicadorTCP.enviarMensagem("Reponsta: " + mensagem);
                Thread thread = new Thread(comunicadorTCP);
                thread.start();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

    }

    public static void main(String... args) {
        try {
            TCPServer tcpServer = new TCPServer();
            Thread thread = new Thread(tcpServer);
            thread.start();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
