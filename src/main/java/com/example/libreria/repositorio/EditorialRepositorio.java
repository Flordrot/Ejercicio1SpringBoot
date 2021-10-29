package com.example.libreria.repositorio;

import com.example.libreria.entidades.Editorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EditorialRepositorio extends JpaRepository<Editorial, String>{
    @Modifying
    @Query("UPDATE Editorial e SET e.nombre = :nombre WHERE e.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre);
    
    @Modifying
    @Query("UPDATE Editorial e SET e.alta = true WHERE e.id = :id")
    void habilitar(@Param("id") String id);
    
    @Modifying
    @Query("UPDATE Editorial e SET e.alta = false WHERE e.id = :id")
    void deshabilitar(@Param("id") String id);
}
