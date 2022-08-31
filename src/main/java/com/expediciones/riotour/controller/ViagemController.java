package com.expediciones.riotour.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import com.expediciones.riotour.models.ViagemModel;
import com.expediciones.riotour.repository.ViagemRepository;
import com.expediciones.riotour.upload.FileUploadUtil;


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
    
    @GetMapping("/preco/precomin")
    public ResponseEntity<List<ViagemModel>> getAllByPrecoMin(){
        return ResponseEntity.ok(viagemRepository.findAllByOrderByPrecoAsc());
    }
    
    @GetMapping("/preco/precomax")
    public ResponseEntity<List<ViagemModel>> getAllByPrecoMax(){
        return ResponseEntity.ok(viagemRepository.findAllByOrderByPrecoDesc());
    }
    
    @PostMapping
    public ResponseEntity<ViagemModel> post(@RequestBody @Valid ViagemModel viagem){
        return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagem));
    }
    
    @PostMapping ("/foto/salvar")
    public RedirectView salvarFoto (ViagemModel viagem,
    		@RequestParam("foto") MultipartFile multipartFile) throws IOException{
    		
    			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
    			
    			ViagemModel salvarFoto = viagemRepository.save(viagem);
    			
    			String uploadDir = "Admin-fotos/";
    			
    			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
    			
    			return new RedirectView("/users", true);
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
