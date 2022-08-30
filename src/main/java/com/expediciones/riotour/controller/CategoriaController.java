package com.expediciones.riotour.controller;

import com.expediciones.riotour.models.CategoriaModel;
import com.expediciones.riotour.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/categoria")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {


        @Autowired
        public CategoriaRepository categoriaRepository;

        @GetMapping
        public ResponseEntity<List<CategoriaModel>> getAll(){
            return ResponseEntity.ok(categoriaRepository.findAll());
        }

        @GetMapping("/{id}")
        public ResponseEntity<CategoriaModel> getById(@PathVariable Long id){
            return categoriaRepository.findById(id)
                    .map(resposta -> ResponseEntity.ok(resposta))
                    .orElse(ResponseEntity.notFound().build());
        }

        @GetMapping("/titulo/{titulo}")
        public ResponseEntity<List<CategoriaModel>> getByTitulo(@PathVariable String descricao){
            return ResponseEntity.ok(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));

        }


        @PostMapping
        public ResponseEntity<CategoriaModel> post(@RequestBody @Valid CategoriaModel categoria){
            return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoria));
        }

        @PutMapping
        public ResponseEntity<CategoriaModel> put(@RequestBody @Valid CategoriaModel categoria){
            return categoriaRepository.findById(categoria.getId()).map(resp -> {
                return ResponseEntity.ok().body(categoriaRepository.save(categoria));
            }).orElse(ResponseEntity.notFound().build());
        }

        @DeleteMapping("/{id}")
        public ResponseEntity <?> delete(@PathVariable Long id){
            return categoriaRepository.findById(id).map(resp -> {
                categoriaRepository.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }).orElse(ResponseEntity.notFound().build());
        }



    }
