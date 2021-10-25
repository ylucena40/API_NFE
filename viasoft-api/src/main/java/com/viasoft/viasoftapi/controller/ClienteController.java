package com.viasoft.viasoftapi.controller;


import com.viasoft.viasoftapi.bd.Nota;
import com.viasoft.viasoftapi.bd.NotaDAO;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@RestController
@RequestMapping("/notas")
public class ClienteController {

    NotaDAO n = new NotaDAO();

    @GetMapping("/statusAtual")
    public List<Nota> statusAtual(){
        return n.statusAtual();
   }

    @GetMapping("/servicoEstado")
    public String servicoEstado(@RequestParam (value="servico") String servico,
                                       @RequestParam (value="estado") String estado){
        return n.statusServicoPorEstado(servico,estado);
    }

    @GetMapping("/statusData")
    public List<Nota> statusData(@RequestParam (value="data") String data){
        return n.statusPorData(Date.valueOf(data));
    }

    @GetMapping("/servicoIndisponivelEstado")
    public String servicoIndisponivelEstado(){
        return n.servicosIndisponivel(n.tabelaCompleta());
   }
}
