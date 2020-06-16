package br.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import persistencia.Persistencia;

public class ControlePersistir {

    private String caminhoDoAquivoNoDisco = "";
    private Persistencia serverIncluir = null;

    public ControlePersistir() {

    }

    public void metodosDeControle(String mensagem) throws Exception {
        String[] metodos = mensagem.split(";");
        String metodo = metodos[0];

        switch (metodo) {
            case "post":
                gravarTXT(mensagem);
                break;
            case "get":
                getTXT(mensagem);
                break;
            case "put":
                gravarTXT(mensagem);
                break;
            default:
                System.out.println("Metodos Desconhecido! ");
                break;
        }
    }

    public void gravarTXT(String msg) throws Exception {
        String[] vetorItens = msg.split(";");
        caminhoDoAquivoNoDisco = vetorItens[1];
        serverIncluir = new Persistencia("./arquivos/" + caminhoDoAquivoNoDisco + ".txt");

        switch (vetorItens[1]) {
            case "Editora":
                String arquiv1 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + "|";
                serverIncluir.incluir(arquiv1);
                break;
            case "Usuario":
                String arquiv2 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + "|";
                serverIncluir.incluir(arquiv2);
                break;
            case "Livro":
                String arquiv3 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + ";" + vetorItens[7] + ";" + vetorItens[8] + "|";
                serverIncluir.incluir(arquiv3);
                break;
            case "Autor":
                String arquiv4 = vetorItens[2] + ";" + vetorItens[3] + "|";
                serverIncluir.incluir(arquiv4);
                break;
            case "AreaDoLivro":
                String arquiv = vetorItens[2] + ";" + vetorItens[3] + "|";
                serverIncluir.incluir(arquiv);
                break;
            case "Colaborador":
                String arquiv6 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + ";" + vetorItens[7] + ";" + vetorItens[8] + ";" + vetorItens[9] + ";" + vetorItens[10] + "|";
                serverIncluir.incluir(arquiv6);
                break;
            case "Exemplar":
                String arquiv8 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + ";" + vetorItens[7] + ";" + vetorItens[8] + ";" + vetorItens[9] + ";" + vetorItens[10] + "|";
                serverIncluir.incluir(arquiv8);
                break;
            case "Reserva":
                String arquiv9 = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + "|";
                serverIncluir.incluir(arquiv9);
                break;
            case "Devolucao":
                String arquivd = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + "|";
                serverIncluir.incluir(arquivd);
                break;
            case "Emprestimo":
                String arquivE = vetorItens[2] + ";" + vetorItens[3] + ";" + vetorItens[4] + ";" + vetorItens[5] + ";" + vetorItens[6] + "|";
                serverIncluir.incluir(arquivE);
                break;
            default:
                System.out.println("Erro! Incluindo");
                break;
        }
    }

    public List<String> getTXT(String msg) throws Exception {
        String[] vetorItens = msg.split(";");
        caminhoDoAquivoNoDisco = vetorItens[1];
        serverIncluir = new Persistencia("./arquivos/" + caminhoDoAquivoNoDisco + ".txt");
        ArrayList<String> lista = (ArrayList<String>) listagem("./arquivos/" + caminhoDoAquivoNoDisco + ".txt");
        return lista;
    }

    public List<String> listagem(String nomeDoArquivoNoDisco) throws Exception {
        try {
            String[] vetor = null;
            List<String> retornoListagem = new ArrayList<>();
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);

            String linha = "";
            while ((linha = br.readLine()) != null) {
                retornoListagem.add(linha.split("|").toString());
            }
            return retornoListagem;
        } catch (Exception ErroAoLerAqui) {
            System.out.println("Erro: Ao Ler - " + ErroAoLerAqui.getMessage());
        }
        return null;
    }

    public String getCaminhoDoAquivoNoDisco() {
        return caminhoDoAquivoNoDisco;
    }

    public void setCaminhoDoAquivoNoDisco(String caminhoDoAquivoNoDisco) {
        this.caminhoDoAquivoNoDisco = caminhoDoAquivoNoDisco;
    }

    public Persistencia getEditoraIncluir() {
        return serverIncluir;
    }

    public void setEditoraIncluir(Persistencia editoraIncluir) {
        this.serverIncluir = editoraIncluir;
    }

}
