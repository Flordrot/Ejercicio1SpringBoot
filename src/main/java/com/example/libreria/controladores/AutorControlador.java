package com.example.libreria.controladores;

import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.entidades.Autor;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/autores")
public class AutorControlador {

    @Autowired
    private AutorServicio servicio;

    @GetMapping("/todos")
    public ModelAndView mostrarAutores(){
        ModelAndView mav = new ModelAndView("autores-lista");

        List<Autor> autores = servicio.obtenerAutor();
        mav.addObject("autores", autores);

        return mav;
    }

    @GetMapping("/crear")
    public ModelAndView crearAutor(){
        ModelAndView mav = new ModelAndView("autorformulario");
        mav.addObject("autor", new Autor());
        mav.addObject("title","Crear Autor");
        mav.addObject("action","guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarAutor(@RequestParam String nombre){
           servicio.crearAutor(nombre);
        return new RedirectView("/autores/todos");
    }
    
    /*@PostMapping("/eliminar/{id}") 
    public RedirectView eliminarAutor(@PathVariable String id) {
        servicio.eliminarAutor(id);
        return new RedirectView("/autores/todos");
    }*/
    
    @GetMapping("/guardar")
    public RedirectView modificarAutor(@RequestParam String id, @RequestParam String nombre){
           servicio.modificarAutor(id, nombre);
        return new RedirectView("/autores/todos");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarAutor(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("autorformulario");
        mav.addObject("autor", servicio.buscarPorId(id));
        mav.addObject("title", "Editar Autor");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam("id") String id, @RequestParam("nombre") String nombre) {
        servicio.modificarAutor(id, nombre);
        return new RedirectView("/autores/todos");
    }
    
    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        servicio.habilitar(id);
        return new RedirectView("/autores/todos");
    }
    
     @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable String id) {
        servicio.deshabilitar(id);
        return new RedirectView("/autores/todos");
    }
}
