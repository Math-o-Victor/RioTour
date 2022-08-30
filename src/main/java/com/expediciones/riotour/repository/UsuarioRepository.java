package com.expediciones.riotour.repository;

import com.expediciones.riotour.models.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioModel,Long> {
}
