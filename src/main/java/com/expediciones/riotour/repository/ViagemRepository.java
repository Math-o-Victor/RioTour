package com.expediciones.riotour.repository;

import com.expediciones.riotour.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
    public List<ViagemModel> findAllByTituloContainingIgnoreCase (@Param ("titulo") String titulo);
}
