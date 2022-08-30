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

import com.expediciones.riotour.models.AdminModel;
import com.expediciones.riotour.repository.AdminRepository;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@GetMapping
	private ResponseEntity<List<AdminModel>> getAll(){
		return ResponseEntity.ok(adminRepository.findAll());
	}
	
	@PostMapping
    public ResponseEntity<AdminModel> post(@RequestBody @Valid AdminModel admin){
        return ResponseEntity.status(HttpStatus.CREATED).body(adminRepository.save(admin));
    }
	
	@PutMapping
    public ResponseEntity<AdminModel> put(@RequestBody @Valid AdminModel admin){
        return adminRepository.findById(admin.getId()).map(resp -> {
            return ResponseEntity.ok().body(adminRepository.save(admin));
        }).orElse(ResponseEntity.notFound().build());
    }
		
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		adminRepository.deleteById(id);
	}

}
