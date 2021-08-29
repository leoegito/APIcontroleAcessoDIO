package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.CategoriaUsuario;
import com.dio.controleAcesso.service.CategoriaUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoriausuario")
public class CategoriaUsuarioController {
    @Autowired
    CategoriaUsuarioService categoriaUsuarioService;

    @PostMapping
    public CategoriaUsuario createCategoria(@RequestBody CategoriaUsuario categoriaUsuario){
        return categoriaUsuarioService.saveCategoria(categoriaUsuario);
    }

    @GetMapping("/list")
    public List<CategoriaUsuario> getCategoriaList(){
        return categoriaUsuarioService.findAll();
    }

    @GetMapping("{idCategoria}")
    public ResponseEntity<CategoriaUsuario> getCategoriaById(@PathVariable("idCategoria") Long idCategoria) throws Exception{
        return ResponseEntity.ok(categoriaUsuarioService.getById(idCategoria).orElseThrow(
                () -> new Exception("Categoria de usuário não encontrada")
        ));
    }

    @PutMapping
    public CategoriaUsuario updateCategoria(@RequestBody CategoriaUsuario categoriaUsuario){
        return categoriaUsuarioService.updateCategoria(categoriaUsuario);
    }

    @DeleteMapping("{idCategoria}")
    public ResponseEntity<Long> deleteById(@PathVariable("idCategoria") Long idCategoria) throws Exception{
        try{
            categoriaUsuarioService.deleteCategoria(idCategoria);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(idCategoria, HttpStatus.OK);
    }

}
