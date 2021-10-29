/*
El repositorio que persiste a esta entidad
(LibroRepositorio) debe contener los métodos necesarios para guardar/actualizar libros
en la base de datos, realizar consultas o dar de baja según corresponda.
*/
package com.example.libreria.repositorio;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro, String>{
   @Query("SELECT l FROM Libro l WHERE l.autor.id = :id")
   public List<Libro> buscarLibroPorAutor(@Param("id") String id);
   
   /*@Query("SELECT l FROM Libro l WHERE l.editorial.id = :id")
   public List<Libro> buscarLibroPorEditorial(@Param("id") String id);

   @Query("SELECT l FROM Libro l WHERE l.isbn = :isbn")
   public List<Libro> buscarLibroPorIsbn(@Param("isbn") Long isbn);
   
   @Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:titulo%")
   public List<Libro> buscarLibroPorTitulo(@Param("titulo") String titulo);
   */
   
    @Modifying
    @Query("UPDATE Libro l SET l.isbn = :isbn, l.titulo = :titulo, l.anio = :anio, l.ejemplares = :ejemplares, l.ejemplaresPrestados = :ejemplaresPrestados, l.ejemplaresRestantes = :ejemplaresRestantes, l.alta = :alta, l.autor = :autor, l.editorial = :editorial WHERE l.id = :id")
    void modificar(@Param("id") String id, @Param("isbn") Long isbn, @Param("titulo") String titulo, @Param("anio") Integer anio, @Param("ejemplares") Integer ejemplares, @Param("ejemplaresPrestados") Integer ejemplaresPrestados, @Param("ejemplaresRestantes") Integer ejemplaresRestantes, @Param("alta")Boolean alta, @Param("autor") Autor autor, @Param("editorial") Editorial editorial);
    
    @Modifying
    @Query("UPDATE Libro l SET l.alta = true WHERE l.id = :id")
    void habilitar(@Param("id") String id);
    
    @Modifying
    @Query("UPDATE Libro l SET l.alta = false WHERE l.id = :id")
    void deshabilitar(@Param("id") String id);
}
