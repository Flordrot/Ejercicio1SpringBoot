package com.example.libreria.servicios;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.repositorio.EditorialRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialServicio {

    @Autowired
    private EditorialRepositorio repositorio;

    public void crearEditorial(String nombre) {
        Editorial editorial = new Editorial();

        editorial.setAlta(true);
        editorial.setNombre(nombre);

        repositorio.save(editorial);
    }
    
    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditorial() {
        return repositorio.findAll();
    }
    
    @Transactional
    public void modificarEditorial(String id, String nombre) { 
        repositorio.modificar(id, nombre);
    }

    /*@Transactional
    public void eliminarEditorial(String id) {
        repositorio.deleteById(id); 
    }*/
    
  
    @Transactional(readOnly = true)
    public Editorial buscarPorId(String id) {
        Optional<Editorial> editorialOptional = repositorio.findById(id);
        return editorialOptional.orElse(null);
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
