package com.dio.controleAcesso.controller;

import com.dio.controleAcesso.model.TipoData;
//import com.dio.controleAcesso.service.CalendarioService;
import com.dio.controleAcesso.service.TipoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipodata")
public class TipoDataController {

    @Autowired
    TipoDataService tipoDataService;
//    @Autowired
//    CalendarioService calendarioService;//necessário?

    @PostMapping
    public ResponseEntity<TipoData> createTipoData(@RequestBody TipoData tipoData){

        TipoData tipoDataSalvo = tipoDataService.saveTipoData(tipoData);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(tipoDataSalvo.getIdTipoData()).toUri();

        return ResponseEntity.created(location).body(tipoDataSalvo);
    }

    @GetMapping
    public ResponseEntity<List<TipoData>> getTipoDataList(){
        return ResponseEntity.ok(tipoDataService.getTipoData());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoData> getTipoDataById(@PathVariable Long id) {
        Optional<TipoData> optionalTipoData = tipoDataService.getTipoDataById(id);
        if (optionalTipoData.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        return ResponseEntity.ok(optionalTipoData.get());
    }

    @PutMapping
    public ResponseEntity<TipoData> updateTipoData(@RequestBody TipoData tipoData){
        Optional<TipoData> optionalTipoData = tipoDataService.getTipoDataById(tipoData.getIdTipoData());
        if (optionalTipoData.isEmpty()) {
            return ResponseEntity.unprocessableEntity().build();
        }

        tipoData.setIdTipoData(optionalTipoData.get().getIdTipoData());
        tipoDataService.updateTipoData(tipoData);

        return ResponseEntity.noContent().build();

    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<TipoData> deleteCalendario(@PathVariable Long id){
//        Optional<TipoData> optionalTipoData = tipoDataService.getTipoDataById(id);
//        if (optionalTipoData.isEmpty()) {
//            return ResponseEntity.unprocessableEntity().build();
//        }
//
//        tipoDataService.deleteTipoData(optionalTipoData.get().getIdTipoData());
//        return ResponseEntity.noContent().build();
//    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TipoData> deleteTipoData(@PathVariable Long id){
    Optional<TipoData> optionalTipoData = tipoDataService.getTipoDataById(id);
    if (optionalTipoData.isEmpty()) {
        return ResponseEntity.unprocessableEntity().build();
    }

    tipoDataService.deleteTipoData(optionalTipoData.get().getIdTipoData());
    return ResponseEntity.noContent().build();
}


}
