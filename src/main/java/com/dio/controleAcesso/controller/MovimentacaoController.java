package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.Calendario;
import com.dio.controleAcesso.model.Movimentacao;
import com.dio.controleAcesso.model.Ocorrencia;
import com.dio.controleAcesso.service.CalendarioService;
import com.dio.controleAcesso.service.MovimentacaoService;
import com.dio.controleAcesso.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movimentacao")
public class MovimentacaoController {
    OcorrenciaService ocorrenciaService;
    CalendarioService calendarioService;
    MovimentacaoService movimentacaoService;

    @Autowired
    public MovimentacaoController(OcorrenciaService ocorrenciaService, CalendarioService calendarioService, MovimentacaoService movimentacaoService){
        this.ocorrenciaService = ocorrenciaService;
        this.calendarioService = calendarioService;
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public ResponseEntity<Movimentacao> createMovimentacao(@RequestBody Movimentacao movimentacao){
        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaService.getById(movimentacao.getOcorrencia().getIdOcorrencia());
        if(ocorrenciaOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
        movimentacao.setOcorrencia(ocorrenciaOptional.get());

        Optional<Calendario> calendarioOptional = calendarioService.getById(movimentacao.getCalendario().getIdCalendario());
        if(calendarioOptional.isEmpty()){
            return ResponseEntity.unprocessableEntity().build();
        }
//        Calendario calendario2 = calendarioOptional.get();
        movimentacao.setCalendario(calendarioOptional.get());

        Movimentacao movimentacaoSalva = movimentacaoService.saveMovimentacao(movimentacao);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(movimentacaoSalva.getIdMovimento()).toUri();
        return ResponseEntity.created(location).body(movimentacaoSalva);
    }

    @GetMapping("/list")
    public List<Movimentacao> getMovimentacaoList(){
        return movimentacaoService.findAll();
    }

    @GetMapping("{idMovimentacao}")
    public ResponseEntity<Movimentacao> getMovimentacaoById(@PathVariable("idMovimentacao") Movimentacao.MovimentacaoId idMovimentacao) throws Exception{
        return ResponseEntity.ok(movimentacaoService.getById(idMovimentacao).orElseThrow(
                () -> new Exception("Movimentação não encontrada")
        ));
    }

    @PutMapping
//    public ResponseEntity<Movimentacao> updateMovimentacao(@RequestBody Movimentacao movimentacao){
//        Movimentacao.MovimentacaoId movimentacaoId = new Movimentacao.MovimentacaoId(movimentacao.getIdMovimento().getIdMovimento(),
//                movimentacao.getIdMovimento().getIdUsuario());
//        Optional<Movimentacao> movimentacaoOptional = movimentacaoService.getById(movimentacaoId);
//        if(movimentacaoOptional.isEmpty()){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        Optional<Ocorrencia> ocorrenciaOptional = ocorrenciaService.getById(movimentacao.getOcorrencia().getIdOcorrencia());
//        if(ocorrenciaOptional.isEmpty()){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//        Optional<Calendario> calendarioOptional = calendarioService.getById(movimentacao.getCalendario().getIdCalendario());
//        if(calendarioOptional.isEmpty()){
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        movimentacao.setOcorrencia(ocorrenciaOptional.get());
//        movimentacao.setCalendario(calendarioOptional.get());
//        movimentacaoService.saveMovimentacao(movimentacao);
//        return ResponseEntity.noContent().build();
//
//    }
    public Movimentacao updateMovimentacao(@RequestBody Movimentacao movimentacao){
        return movimentacaoService.updateMovimentacao(movimentacao);
    }

    @DeleteMapping("{idMovimentacao}")
    public ResponseEntity<Movimentacao.MovimentacaoId> deleteById(@PathVariable("idMovimentacao") Movimentacao.MovimentacaoId idMovimentacao) throws Exception{
        try {
            movimentacaoService.deleteMovimentacao(idMovimentacao);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idMovimentacao, HttpStatus.OK);
    }

}
