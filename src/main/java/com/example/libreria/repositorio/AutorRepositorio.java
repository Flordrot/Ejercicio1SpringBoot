  package com.example.libreria.repositorio;

import com.example.libreria.entidades.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepositorio extends JpaRepository<Autor, String>{
    @Modifying
    @Query("UPDATE Autor a SET a.nombre = :nombre WHERE a.id = :id")
    void modificar(@Param("id") String id, @Param("nombre") String nombre);
    
    @Modifying
    @Query("UPDATE Autor a SET a.alta = true WHERE a.id = :id")
    void habilitar(@Param("id") String id);
    
    @Modifying
    @Query("UPDATE Autor a SET a.alta = false WHERE a.id = :id")
    void deshabilitar(@Param("id") String id);
}
