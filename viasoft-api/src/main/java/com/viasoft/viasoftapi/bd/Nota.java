package com.viasoft.viasoftapi.bd;

import java.sql.Time;
import java.sql.Date;

public class Nota {
    private String autorizador;
    private int autorizacao;
    private int retornoAutorizacao;
    private int inutilizacao;
    private int consultaProtocolo;
    private int statusServico;
    private String tempoMedio;
    private int consultaCadastro;
    private int recepcaoEvento;
    private Date dataAcesso;
    private Time horaAcesso;


    public int verificaStatus(String status) {
        if (status.contains("bola_verde")) {
            return 1;
        }
        if (status.contains("bola_amarela")) {
            return 2;
        }
        if (status.contains("bola_vermelho")) {
            return 3;
        }
        return 0;

    }

    public int getRetornoAutorizacao() {
        return retornoAutorizacao;
    }

    public void setRetornoAutorizacao(int retornoAutorizacao) {
        this.retornoAutorizacao = retornoAutorizacao;
    }

    public int getInutilizacao() {
        return inutilizacao;
    }

    public void setInutilizacao(int inutilizacao) {
        this.inutilizacao = inutilizacao;
    }

    public int getConsultaProtocolo() {
        return consultaProtocolo;
    }

    public void setConsultaProtocolo(int consultaProtocolo) {
        this.consultaProtocolo = consultaProtocolo;
    }

    public int getStatusServico() {
        return statusServico;
    }

    public void setStatusServico(int statusServico) {
        this.statusServico = statusServico;
    }

    public String getTempoMedio() {
        return tempoMedio;
    }

    public void setTempoMedio(String tempoMedio) {
        this.tempoMedio = tempoMedio;
    }

    public int getConsultaCadastro() {
        return consultaCadastro;
    }

    public void setConsultaCadastro(int consultaCadastro) {
        this.consultaCadastro = consultaCadastro;
    }

    public int getRecepcaoEvento() {
        return recepcaoEvento;
    }

    public void setRecepcaoEvento(int recepcaoEvento) {
        this.recepcaoEvento = recepcaoEvento;
    }

    public Date getDataAcesso() {
        return dataAcesso;
    }

    public void setDataAcesso(Date dataAcesso) {
        this.dataAcesso = dataAcesso;
    }

    public Time getHoraAcesso() {
        return horaAcesso;
    }

    public void setHoraAcesso(Time horaAcesso) {
        this.horaAcesso = horaAcesso;
    }

    public String getAutorizador() {
        return autorizador;
    }

    public void setAutorizador(String autorizador) {
        this.autorizador = autorizador;
    }

    public int getAutorizacao() {
        return autorizacao;
    }

    public void setAutorizacao(int autorizacao) {
        this.autorizacao = autorizacao;
    }
}
