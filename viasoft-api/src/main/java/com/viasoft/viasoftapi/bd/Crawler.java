package com.viasoft.viasoftapi.bd;

import com.viasoft.viasoftapi.bd.Nota;
import com.viasoft.viasoftapi.bd.NotaDAO;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;


public class Crawler {
    private Document doc;
    private NotaDAO dao = new NotaDAO();

    public void setValuesDB(Elements cols, Nota n) {
        n.setAutorizador(String.valueOf(cols.get(0)).replace("<td>","").replace("</td>",""));
        n.setAutorizacao(n.verificaStatus(String.valueOf(cols.get(1))));
        n.setRetornoAutorizacao(n.verificaStatus(String.valueOf(cols.get(2))));
        n.setInutilizacao(n.verificaStatus(String.valueOf(cols.get(3))));
        n.setConsultaProtocolo(n.verificaStatus(String.valueOf(cols.get(4))));
        n.setStatusServico(n.verificaStatus(String.valueOf(cols.get(5))));
        n.setTempoMedio(String.valueOf(cols.get(6)).replace("<td>","").replace("</td>",""));
        n.setConsultaCadastro(n.verificaStatus(String.valueOf(cols.get(7))));
        n.setRecepcaoEvento(n.verificaStatus(String.valueOf(cols.get(8))));
        n.setDataAcesso(Date.valueOf(LocalDateTime.now().toLocalDate()));
        n.setHoraAcesso(Time.valueOf(LocalDateTime.now().toLocalTime()));
    }

    public void atualizaDB() {
        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.nfe.fazenda.gov.br/portal/disponibilidade.aspx").get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<String> downServers = new ArrayList<>();
        Elements table = doc.getElementsByClass("tabelaListagemDados"); //select the first table.
        Elements rows = table.select("tr");

        for (int i = 1; i < rows.size(); i++) { //first row is the col names so skip it.
            Nota n = new Nota();
            Element row = rows.get(i);
            Elements cols = row.select("td");
            setValuesDB(cols,n);
            dao.create(n);
        }
    }

}
