package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.repositorio.AutorRepositorio;
import com.example.libreria.repositorio.EditorialRepositorio;
import com.example.libreria.repositorio.LibroRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroServicio {

    @Autowired
    private LibroRepositorio repositorio;
    @Autowired
    private AutorRepositorio autorRepositorio;
    @Autowired
    private EditorialRepositorio editorialRepositorio;

    public void crearLibro(Long isbn, String titulo, Integer anio, Integer ejemplares, String idAutor, String idEditorial) {
        Libro libro = new Libro();
        
        libro.setAlta(true);
        libro.setIsbn(isbn);
        libro.setTitulo(titulo);
        libro.setAutor(autorRepositorio.findById(idAutor).orElse(null));
        libro.setAnio(anio);
        libro.setEditorial(editorialRepositorio.findById(idEditorial).orElse(null));
        libro.setEjemplares(ejemplares);
        libro.setEjemplaresPrestados(0);
        libro.setEjemplaresRestantes(ejemplares);

        repositorio.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> obtenerLibros() {
        return repositorio.findAll();
    }

    @Transactional
    public void modificarLibro(String id, Long isbn, String titulo, Integer anio, Integer ejemplares, Integer ejemplaresPrestados, Integer ejemplaresRestantes, Boolean alta, Autor autor, Editorial editorial) {
        repositorio.modificar(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);
    }

    @Transactional(readOnly = true)
    public Libro buscarPorId(String id) {
        Optional<Libro> usuarioOptional = repositorio.findById(id);
        return usuarioOptional.orElse(null);
    }

    /*@Transactional
    public void eliminarLibro(String id) {
        repositorio.deleteById(id);
    }*/

    @Transactional
    public void habilitar(String id) {
        repositorio.habilitar(id);
    }
    
    @Transactional
    public void deshabilitar(String id) {
        repositorio.deshabilitar(id);
    }
}
