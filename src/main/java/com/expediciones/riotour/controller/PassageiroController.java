package com.expediciones.riotour.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expediciones.riotour.models.PassageiroModel;
import com.expediciones.riotour.repository.PassageiroRepository;

@RequestMapping("/passageiro")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PassageiroController {
	
	@Autowired
    public PassageiroRepository passageiroRepository;

    @GetMapping
    public ResponseEntity<List<PassageiroModel>> getAll(){
        return ResponseEntity.ok(passageiroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PassageiroModel> getById(@PathVariable Long id){
        return passageiroRepository.findById(id)
                .map(resposta -> ResponseEntity.ok(resposta))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<PassageiroModel>> getByTitulo(@PathVariable String nome){
        return ResponseEntity.ok(passageiroRepository.findAllByNomeContainingIgnoreCase(nome));

    }

    @PostMapping
    public ResponseEntity<PassageiroModel> post(@RequestBody @Valid PassageiroModel passageiro){
        return ResponseEntity.status(HttpStatus.CREATED).body(passageiroRepository.save(passageiro));
    }

    @PutMapping
    public ResponseEntity<PassageiroModel> put(@RequestBody @Valid PassageiroModel passageiro){
        return passageiroRepository.findById(passageiro.getId()).map(resp -> {
            return ResponseEntity.ok().body(passageiroRepository.save(passageiro));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity <?> delete(@PathVariable Long id){
        return passageiroRepository.findById(id).map(resp -> {
            passageiroRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
