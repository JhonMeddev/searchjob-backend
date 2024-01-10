package com.searchjob.controller;

import com.searchjob.model.PerfilModel;
import com.searchjob.model.utils.AreasEnum;
import com.searchjob.repository.PerfilRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/perfil")
public class PerfilController {

    @Autowired
    private PerfilRepository perfilRepository;

    @GetMapping
    public ResponseEntity<List<PerfilModel>> getAll(){
        return ResponseEntity.ok(perfilRepository.findAll());
    }

    @GetMapping("/name/{perfilArea}")
    public ResponseEntity<List<PerfilModel>> getByAreaName(@PathVariable String perfilArea){
        return ResponseEntity.ok(perfilRepository.findAllByArea(AreasEnum.valueOf(perfilArea)));
    }

    @PostMapping
    public ResponseEntity<PerfilModel> postPerfil(@RequestBody PerfilModel perfil){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(perfilRepository.save(perfil));
    }

    @PutMapping
    public ResponseEntity<PerfilModel> putPerfil(@RequestBody PerfilModel perfil){
        return perfilRepository.findById(perfil.getId())
                .map(perfilUpdate ->{
                    return ResponseEntity.ok().body(perfilRepository.save(perfilUpdate));
                }).orElse(ResponseEntity.notFound().build());
    }

}
