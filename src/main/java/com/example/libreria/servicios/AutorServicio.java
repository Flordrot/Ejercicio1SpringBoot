package com.example.libreria.servicios;

import com.example.libreria.entidades.Autor;
import com.example.libreria.repositorio.AutorRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorServicio {

    @Autowired
    private AutorRepositorio repositorio;

    @Transactional
    public void crearAutor(String nombre) {
        Autor autor = new Autor();

        autor.setAlta(true);
        autor.setNombre(nombre);

        repositorio.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> obtenerAutor() {
        return repositorio.findAll();
    }

    @Transactional
    public void modificarAutor(String id, String nombre) {
        repositorio.modificar(id, nombre);
    }

    /*@Transactional
    public void eliminarAutor(String id) {
        repositorio.deleteById(id); 
    }*/
    
    @Transactional(readOnly = true)
    public Autor buscarPorId(String id) {
        Optional<Autor> autorOptional = repositorio.findById(id);
        return autorOptional.orElse(null);
    }
    
    @Transactional
    public void habilitar(String id) {
        repositorio.habilitar(id);
    }
    
    @Transactional
    public void deshabilitar(String id) {
        repositorio.deshabilitar(id);
    }
}
