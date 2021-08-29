package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.Calendario;
import com.dio.controleAcesso.service.CalendarioService;
//import com.dio.controleAcesso.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Calendario")
public class CalendarioController {
    @Autowired
    CalendarioService calendarioService;
//    @Autowired
//    TipoDataService tipoDataService;

    @PostMapping
    public Calendario createCalendario(@RequestBody Calendario calendario){
        return calendarioService.saveCalendario(calendario);
    }

    @GetMapping("/list")
    public List<Calendario> getCalendarioList(){
        return calendarioService.findAll();
    }

    @GetMapping("{idCalendario}")
    public ResponseEntity<Calendario> getCalendarioById(@PathVariable("idCalendario") Long idCalendario) throws Exception{
        return ResponseEntity.ok(calendarioService.getById(idCalendario).orElseThrow(
                () -> new Exception("Calendario não encontrado.")
        ));
    }

    @PutMapping
    public Calendario updateCalendario(@RequestBody Calendario calendario){
        return calendarioService.updateCalendario(calendario);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteById(@PathVariable("idCalendario") Long idCalendario) throws Exception{
        try{
            calendarioService.deleteCalendario(idCalendario);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idCalendario, HttpStatus.OK);
    }
}
