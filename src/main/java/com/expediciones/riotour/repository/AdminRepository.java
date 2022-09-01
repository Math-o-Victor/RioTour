package com.expediciones.riotour.repository;

import com.expediciones.riotour.models.AdminModel;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminModel, Long> {
	
	public Optional<AdminModel> findByEmail(String email);
	
}
