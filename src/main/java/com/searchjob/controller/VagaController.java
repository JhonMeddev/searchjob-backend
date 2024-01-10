package com.searchjob.controller;

import com.searchjob.model.VagaModel;
import com.searchjob.repository.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/vaga")
public class VagaController {
    @Autowired
    private VagaRepository vagaRepository;
    @GetMapping
    public ResponseEntity<List<VagaModel>> getAll(){
        return ResponseEntity.ok(vagaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VagaModel> findById(@PathVariable long id){
        return vagaRepository.findById(id)
                .map(resp -> ResponseEntity.ok(resp))
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/title/{title}")
    public ResponseEntity<List<VagaModel>> findByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(vagaRepository.findAllByTituloContainingIgnoreCase(titulo));
    }

    @PostMapping
    public ResponseEntity<VagaModel> postVaga(@RequestBody VagaModel vaga){
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaRepository.save(vaga));
    }

    @PutMapping
    public ResponseEntity<VagaModel> putVaga(@RequestBody VagaModel vaga){
        return vagaRepository.findById(vaga.getId())
                .map(vagaUpdate -> {
                    return ResponseEntity.ok().body(vagaRepository.save(vagaUpdate));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteVaga(@PathVariable long id){
        return vagaRepository.findById(id)
                .map(vaga -> {
                    vagaRepository.deleteById(id);
                    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
