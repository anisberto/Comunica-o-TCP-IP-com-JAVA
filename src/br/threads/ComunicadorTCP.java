package br.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ComunicadorTCP implements Runnable {

    private Socket socket = null;
    private DataOutputStream dWriter = null;
    private DataInputStream dReader = null;

    public ComunicadorTCP(Socket socket) throws IOException {
        this.socket = socket;
        this.dReader = new DataInputStream(new DataInputStream(this.socket.getInputStream()));
        this.dWriter = new DataOutputStream(new DataOutputStream(this.socket.getOutputStream()));
    }

    public void enviarMensagem(String mensagem) throws IOException {
        this.dWriter.writeUTF(mensagem);
        this.dWriter.flush();
    }

    public String receberMensagem() throws IOException {
        return this.dReader.readUTF();
    }

    @Override
    public void run() {
        System.out.println("ComunicadorTCP no ar..");
        try {
            while (!Thread.interrupted()) {
                System.out.println(receberMensagem());
            }
        } catch (Exception e) {
            System.out.println("Nova Thread Processada!");
        }
    }

}
