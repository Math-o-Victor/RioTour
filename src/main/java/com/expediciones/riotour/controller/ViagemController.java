package com.expediciones.riotour.controller;

import com.expediciones.riotour.models.ViagemModel;
import com.expediciones.riotour.repository.ViagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/viagem")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ViagemController {

    @Autowired
    public ViagemRepository viagemRepository;

    @GetMapping
    public ResponseEntity<List<ViagemModel>> getAll(){
        return ResponseEntity.ok(viagemRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ViagemModel> getById(@PathVariable Long id){
        return viagemRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<ViagemModel>> getByTitulo(@PathVariable String titulo){
        return ResponseEntity.ok(viagemRepository.findAllByTituloContainingIgnoreCase(titulo));

    }


    @PostMapping
    public ResponseEntity<ViagemModel> post(@RequestBody @Valid ViagemModel viagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
    }

    @PutMapping
    public ResponseEntity<ViagemModel> put(@RequestBody @Valid ViagemModel viagem){
        return viagemRepository.findById(viagem.getId()).map(resp -> {
            return ResponseEntity.ok().body(viagemRepository.save(viagem));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> delete(@PathVariable Long id){
        return viagemRepository.findById(id).map(resp -> {
            viagemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElse(ResponseEntity.notFound().build());
    }



}
