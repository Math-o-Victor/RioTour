package com.expediciones.riotour.repository;

import com.expediciones.riotour.models.AdminModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminModel, Long> {
}
