package com.viasoft.viasoftapi.bd;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotaDAO {
    public void create(Nota n) {
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO banco (autorizador,autorizacao,retornoAutorizacao,inutilizacao,consultaProtocolo,statusServico,tempoMedio,consultaCadastro,recepcaoEvento,dataAcesso,horaAcesso)VALUES(?,?,?,?,?,?,?,?,?,?,?)");

            stmt.setString(1, n.getAutorizador());
            stmt.setInt(2, n.getAutorizacao());
            stmt.setInt(3, n.getRetornoAutorizacao());
            stmt.setInt(4, n.getInutilizacao());
            stmt.setInt(5, n.getConsultaProtocolo());
            stmt.setInt(6, n.getStatusServico());
            stmt.setString(7, n.getTempoMedio());
            stmt.setInt(8, n.getConsultaCadastro());
            stmt.setInt(9, n.getRecepcaoEvento());
            stmt.setDate(10, n.getDataAcesso());
            stmt.setTime(11, n.getHoraAcesso());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Nota> statusAtual() {
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Nota> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT  * FROM banco WHERE (horaAcesso >= CURRENT_TIMESTAMP - INTERVAL 5 MINUTE) " +
                    "AND (dataAcesso = (SELECT max(dataAcesso) from banco))");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setAutorizador(rs.getString("autorizador"));
                nota.setAutorizacao(rs.getInt("autorizacao"));
                nota.setRetornoAutorizacao(rs.getInt("retornoAutorizacao"));
                nota.setInutilizacao(rs.getInt("inutilizacao"));
                nota.setConsultaProtocolo(rs.getInt("consultaProtocolo"));
                nota.setStatusServico(rs.getInt("statusServico"));
                nota.setTempoMedio(rs.getString("tempoMedio"));
                nota.setConsultaCadastro(rs.getInt("consultaCadastro"));
                nota.setRecepcaoEvento(rs.getInt("recepcaoEvento"));
                nota.setDataAcesso(rs.getDate("dataAcesso"));
                nota.setHoraAcesso(rs.getTime("horaAcesso"));
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }

    public String statusServicoPorEstado(String servico, String estado) {
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        String resposta = new String();

        try {
            stmt = con.prepareStatement("SELECT  * FROM banco WHERE (horaAcesso >= CURRENT_TIMESTAMP - INTERVAL 5 MINUTE) " +
                    "AND (dataAcesso = (SELECT max(dataAcesso) from banco)) " +
                    "AND (autorizador = ?)");
            stmt.setString(1, estado);

            rs = stmt.executeQuery();
            if (rs != null && rs.next()) {
                switch (servico) {
                    case "autorizacao":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("autorizacao"));
                        break;
                    case "retornoAutorizacao":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("retornoAutorizacao"));
                        break;
                    case "inutilizacao":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("inutilizacao"));
                        break;
                    case "consultaProtocolo":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("consultaProtocolo"));
                        break;
                    case "statusServico":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("statusServico"));
                        break;
                    case "tempoMedio":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                rs.getString("tempoMedio");
                        break;
                    case "consultaCadastro":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("consultaCadastro"));
                        break;
                    case "recepcaoEvento":
                        resposta = "Autorizador: " + estado + ", servico: " + servico + ", status: " +
                                String.valueOf(rs.getInt("recepcaoEvento"));
                        break;
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resposta;
    }

    public List<Nota> statusPorData(Date date) {
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Nota> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM banco WHERE dataAcesso = ? ");
            stmt.setDate(1, date);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setAutorizador(rs.getString("autorizador"));
                nota.setAutorizacao(rs.getInt("autorizacao"));
                nota.setRetornoAutorizacao(rs.getInt("retornoAutorizacao"));
                nota.setInutilizacao(rs.getInt("inutilizacao"));
                nota.setConsultaProtocolo(rs.getInt("consultaProtocolo"));
                nota.setStatusServico(rs.getInt("statusServico"));
                nota.setTempoMedio(rs.getString("tempoMedio"));
                nota.setConsultaCadastro(rs.getInt("consultaCadastro"));
                nota.setRecepcaoEvento(rs.getInt("recepcaoEvento"));
                nota.setDataAcesso(rs.getDate("dataAcesso"));
                nota.setHoraAcesso(rs.getTime("horaAcesso"));
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }

    public List<Nota> tabelaCompleta() {
        Connection con = ConnectionBD.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Nota> notas = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM banco");
            rs = stmt.executeQuery();

            while (rs.next()) {
                Nota nota = new Nota();
                nota.setAutorizador(rs.getString("autorizador"));
                nota.setAutorizacao(rs.getInt("autorizacao"));
                nota.setRetornoAutorizacao(rs.getInt("retornoAutorizacao"));
                nota.setInutilizacao(rs.getInt("inutilizacao"));
                nota.setConsultaProtocolo(rs.getInt("consultaProtocolo"));
                nota.setStatusServico(rs.getInt("statusServico"));
                nota.setTempoMedio(rs.getString("tempoMedio"));
                nota.setConsultaCadastro(rs.getInt("consultaCadastro"));
                nota.setRecepcaoEvento(rs.getInt("recepcaoEvento"));
                nota.setDataAcesso(rs.getDate("dataAcesso"));
                nota.setHoraAcesso(rs.getTime("horaAcesso"));
                notas.add(nota);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return notas;
    }

    public String servicosIndisponivel(List<Nota> n) {
        List<EstadoServico> disponibilidade = new ArrayList<>();

        disponibilidade = setEstados();

        for (int i = 0; i < disponibilidade.size(); i++) {
            for (int j = 0; j < n.size(); j++) {
                if (disponibilidade.get(i).equals(n.get(j).getAutorizador())) {
                    if (n.get(j).getAutorizacao() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getRetornoAutorizacao() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getInutilizacao() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getConsultaProtocolo() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getStatusServico() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getConsultaCadastro() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                    if (n.get(j).getRecepcaoEvento() > 1) {
                        disponibilidade.get(i).incrementaIndisponibilidade();
                    }
                }
            }
        }
        return maiorValor(disponibilidade);
    }

    public String maiorValor(List<EstadoServico> list) {
        EstadoServico maior = new EstadoServico(list.get(0).getEstado(), list.get(0).getQtdServicoIndisponivel());
        for (int i = 1; i < list.size(); i++) {
            if (maior.getQtdServicoIndisponivel() < list.get(i).getQtdServicoIndisponivel()) {
                maior.setEstado(list.get(i).getEstado());
                maior.setQtdServicoIndisponivel(list.get(i).getQtdServicoIndisponivel());
            }
        }
        return "Autorizador: " + maior.getEstado() + " com " + maior.getQtdServicoIndisponivel() + " Serviços Indiponíveis";
    }

    public List<EstadoServico> setEstados() {
        List<EstadoServico> disp = new ArrayList<>();
        disp.add(new EstadoServico("AM", 0));
        disp.add(new EstadoServico("BA", 0));
        disp.add(new EstadoServico("CE", 0));
        disp.add(new EstadoServico("GO", 0));
        disp.add(new EstadoServico("MG", 0));
        disp.add(new EstadoServico("MS", 0));
        disp.add(new EstadoServico("MT", 0));
        disp.add(new EstadoServico("PE", 0));
        disp.add(new EstadoServico("PR", 0));
        disp.add(new EstadoServico("RS", 0));
        disp.add(new EstadoServico("SP", 0));
        disp.add(new EstadoServico("SVAN", 0));
        disp.add(new EstadoServico("SVRS", 0));
        disp.add(new EstadoServico("SVC-AN", 0));
        disp.add(new EstadoServico("SVC-RS", 0));

        return disp;
    }


}
