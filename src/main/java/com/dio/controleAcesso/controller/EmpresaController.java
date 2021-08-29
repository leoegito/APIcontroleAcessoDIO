package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.Empresa;
import com.dio.controleAcesso.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @PostMapping
    public Empresa createEmpresa(@RequestBody Empresa empresa){
        return empresaService.saveEmpresa(empresa);
    }

    @GetMapping("/list")
    public List<Empresa> getEmpresaList(){
        return empresaService.findAll();
    }

    @GetMapping("{idEmpresa}")
    public ResponseEntity<Empresa> getEmpresaById(@PathVariable("idEmpresa") Long idEmpresa) throws Exception{
        return ResponseEntity.ok(empresaService.getById(idEmpresa).orElseThrow(
                () -> new Exception("Empresa não encontrada.")
        ));
    }

    @PutMapping
    public Empresa updateEmpresa(@RequestBody Empresa empresa){
        return empresaService.updateEmpresa(empresa);
    }

    @DeleteMapping("{idEmpresa}")
    public ResponseEntity<Long> deleteById(@PathVariable("idEmpresa") Long idEmpresa) throws Exception{
        try{
            empresaService.deleteEmpresa(idEmpresa);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idEmpresa, HttpStatus.OK);
    }

}
