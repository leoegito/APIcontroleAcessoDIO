package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.BancoHoras;
import com.dio.controleAcesso.service.BancoHorasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bancohoras")
public class BancoHorasController {
    @Autowired
    BancoHorasService bancoHorasService;

    @PostMapping
    public BancoHoras createBancoHoras(@RequestBody BancoHoras bancoHoras){
        return bancoHorasService.saveBancoHoras(bancoHoras);
    }

    @GetMapping("/list")
    public List<BancoHoras> getBancoHorasList(){
        return bancoHorasService.findAll();
    }

    @GetMapping("/{idBancoHoras}")
    public ResponseEntity<BancoHoras> getBancoHorasById(@PathVariable("idBancoHoras") BancoHoras.BancoHorasId idBancoHoras) throws Exception{
        return ResponseEntity.ok(bancoHorasService.getById(idBancoHoras).orElseThrow(
                () -> new Exception("Banco de Horas não encontrado.")
        ));
    }

    @PutMapping
    public BancoHoras updateBancoHoras(@RequestBody BancoHoras bancoHoras){
        return bancoHorasService.updateBancoHoras(bancoHoras);
    }

    @DeleteMapping("{idBancoHoras}")
    public ResponseEntity<BancoHoras.BancoHorasId> deleteById(@PathVariable("idBancoHoras")BancoHoras.BancoHorasId idBancoHoras) throws Exception{
        try{
            bancoHorasService.deleteBancoHoras(idBancoHoras);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idBancoHoras, HttpStatus.OK);
    }





}
