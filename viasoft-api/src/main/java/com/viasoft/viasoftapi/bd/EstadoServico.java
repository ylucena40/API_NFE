package com.viasoft.viasoftapi.bd;

public class EstadoServico {
    private String estado;
    private int qtdServicoIndisponivel;

    public EstadoServico(String estado, int qtdServicoIndisponivel) {
        this.estado = estado;
        this.qtdServicoIndisponivel = qtdServicoIndisponivel;
    }

    public void incrementaIndisponibilidade(){
        this.qtdServicoIndisponivel++;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setQtdServicoIndisponivel(int qtdServicoIndisponivel) {
        this.qtdServicoIndisponivel = qtdServicoIndisponivel;
    }

    public int getQtdServicoIndisponivel(){
        return this.qtdServicoIndisponivel;
    }
}
