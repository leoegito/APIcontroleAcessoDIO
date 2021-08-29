package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.Ocorrencia;
import com.dio.controleAcesso.service.MovimentacaoService;
import com.dio.controleAcesso.service.OcorrenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ocorrencia")
public class OcorrenciaController {
    @Autowired
    OcorrenciaService ocorrenciaService;
    MovimentacaoService movimentacaoService;

    public OcorrenciaController(OcorrenciaService ocorrenciaService, MovimentacaoService movimentacaoService){
        this.ocorrenciaService = ocorrenciaService;
        this.movimentacaoService = movimentacaoService;
    }

    @PostMapping
    public Ocorrencia createOcorrencia(@RequestBody Ocorrencia ocorrencia){
        return ocorrenciaService.saveOcorrencia(ocorrencia);
    }

    @GetMapping("/list")
    public List<Ocorrencia> getOcorrenciaList(){
        return ocorrenciaService.findAll();
    }

    @GetMapping("{idOcorrencia}")
    public ResponseEntity<Ocorrencia> getOcorrenciaById(@PathVariable("idOcorrencia") Long idOcorrencia) throws Exception{
        return ResponseEntity.ok(ocorrenciaService.getById(idOcorrencia).orElseThrow(
                () -> new Exception("Ocorrencia não encontrada")
        ));
    }

    @PutMapping
    public Ocorrencia updateOcorrencia(@RequestBody Ocorrencia ocorrencia){
        return ocorrenciaService.updateOcorrencia(ocorrencia);
    }

    @DeleteMapping("{idOcorrencia}")
    public ResponseEntity<Long> deleteById(@PathVariable("idOcorrencia") Long idOcorrencia) throws Exception{
        try{
            ocorrenciaService.deleteOcorrencia(idOcorrencia);
        }catch (Exception e){
            return new ResponseEntity<>(idOcorrencia, HttpStatus.OK);
        }
        return new ResponseEntity<>(idOcorrencia, HttpStatus.OK);
    }
}
