package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.*;
import com.dio.controleAcesso.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    UsuarioService usuarioService;
    CategoriaUsuarioService categoriaUsuarioService;
    EmpresaService empresaService;
    NivelAcessoService nivelAcessoService;
    JornadaService jornadaService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService, CategoriaUsuarioService categoriaUsuarioService, EmpresaService empresaService, NivelAcessoService nivelAcessoService, JornadaService jornadaService){
        this.usuarioService = usuarioService;
        this.categoriaUsuarioService = categoriaUsuarioService;
        this.empresaService = empresaService;
        this.nivelAcessoService = nivelAcessoService;
        this.jornadaService = jornadaService;
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario) {
        Optional<CategoriaUsuario> categoriaUsuarioOptional = categoriaUsuarioService.getById(usuario.getCategoriaUsuario().getIdCategoriaUsuario());
        if (categoriaUsuarioOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setCategoriaUsuario(categoriaUsuarioOptional.get());

        Optional<Empresa> empresaOptional = empresaService.getById(usuario.getEmpresa().getIdEmpresa());
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setEmpresa(empresaOptional.get());

        Optional<NivelAcesso> nivelAcessoOptional = nivelAcessoService.getById(usuario.getNivelAcesso().getIdNivelAcesso());
        if (nivelAcessoOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setNivelAcesso(nivelAcessoOptional.get());

        Optional<JornadaTrabalho> jornadaTrabalhoOptional = jornadaService.getById(usuario.getJornadaTrabalho().getIdJornadaTrabalho());
        if (jornadaTrabalhoOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setJornadaTrabalho(jornadaTrabalhoOptional.get());

        Usuario usuarioSalvo = usuarioService.saveUsuario(usuario);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(usuarioSalvo.getIdUsuario()).toUri();

        return ResponseEntity.created(location).body(usuarioSalvo);
    }

    @GetMapping("/list")
    public List<Usuario> getUsuarioList(){
        return usuarioService.findAll();
    }

    @GetMapping("{idUsuario}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable("idUsuario") Long idUsuario) throws Exception{
        return ResponseEntity.ok(usuarioService.getById(idUsuario).orElseThrow(
                () -> new Exception("Usuario não encontrado")
        ));
    }

    @PutMapping
    public ResponseEntity<Usuario> updateUsuario(@RequestBody Usuario usuario) {
        Optional<Usuario> usuarioOptional = usuarioService.getById(usuario.getIdUsuario());
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        Optional<CategoriaUsuario> categoriaUsuarioOptional = categoriaUsuarioService.getById(usuario.getCategoriaUsuario().getIdCategoriaUsuario());
        if (categoriaUsuarioOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setCategoriaUsuario(categoriaUsuarioOptional.get());

        Optional<Empresa> empresaOptional = empresaService.getById(usuario.getEmpresa().getIdEmpresa());
        if (empresaOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setEmpresa(empresaOptional.get());

        Optional<NivelAcesso> nivelAcessoOptional = nivelAcessoService.getById(usuario.getNivelAcesso().getIdNivelAcesso());
        if (nivelAcessoOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setNivelAcesso(nivelAcessoOptional.get());

        Optional<JornadaTrabalho> jornadaTrabalhoOptional = jornadaService.getById(usuario.getJornadaTrabalho().getIdJornadaTrabalho());
        if (jornadaTrabalhoOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }
        usuario.setJornadaTrabalho(jornadaTrabalhoOptional.get());

        Usuario usuarioSalvo = usuarioService.saveUsuario(usuario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Usuario> deleteUsuario(@PathVariable Long id) {
        Optional<Usuario> usuarioOptional = usuarioService.getById(id);
        if (usuarioOptional.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        usuarioService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }



}
