package com.expediciones.riotour.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.expediciones.riotour.models.ViagemModel;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {
    public List<ViagemModel> findAllByTituloContainingIgnoreCase (@Param ("titulo") String titulo);

    public List<ViagemModel> findAllByOrderByPrecoAsc(); 
    public List<ViagemModel> findAllByOrderByPrecoDesc(); 
 
}
