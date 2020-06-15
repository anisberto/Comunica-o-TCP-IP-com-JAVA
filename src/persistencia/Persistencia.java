package persistencia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Persistencia {

    private String nomeDoArquivoNoDisco;

    public Persistencia(String nomeDoArquivo) {
        this.nomeDoArquivoNoDisco = nomeDoArquivo;
    }

    public void incluir(String editoraObjeto) throws Exception {
        try {
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(editoraObjeto + "\n");
            bw.close();
        } catch (IOException errorEditora) {
            throw errorEditora;
        }
    }

    public String listagem() throws Exception {
        try {
            String itensListaPersist = "";

            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);

            String linha = "";
            while ((linha = br.readLine()) != null) {
                itensListaPersist = br.readLine();
            }
            return itensListaPersist;
        } catch (Exception erroConsultaAutor) {
            throw erroConsultaAutor;
        }
    }
}
