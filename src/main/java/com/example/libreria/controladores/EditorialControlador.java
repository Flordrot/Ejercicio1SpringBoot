package com.example.libreria.controladores;

import com.example.libreria.entidades.Editorial;
import com.example.libreria.servicios.EditorialServicio;
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
@RequestMapping("/editoriales")
public class EditorialControlador {

    @Autowired
    private EditorialServicio servicio;

    @GetMapping("/todas")
    public ModelAndView mostrarEditorial(){
        ModelAndView mav = new ModelAndView("editoriales-lista");
        
        List <Editorial> editoriales = servicio.obtenerEditorial();
        mav.addObject("editoriales", editoriales);
        
        return mav;
    }
    
    @GetMapping("/crear")
    public ModelAndView crearEditorial(){
        ModelAndView mav = new ModelAndView("editorialformulario");
        mav.addObject("editorial", new Editorial());
        mav.addObject("title","Crear Editorial");
        mav.addObject("action","guardar");
        return mav;
    }

    @PostMapping("/guardar")
    public RedirectView guardarEditorial(@RequestParam String nombre){
           servicio.crearEditorial(nombre);
        return new RedirectView("/editoriales/todas");
    }
    
    /*@PostMapping("/eliminar/{id}") 
    public RedirectView eliminarEditorial(@PathVariable String id) {
        servicio.eliminarEditorial(id);
        return new RedirectView("/editoriales/todas");
    }*/
    
    @GetMapping("/guardar")
    public RedirectView modificarEditorial(@RequestParam String id, @RequestParam String nombre){
           servicio.modificarEditorial(id, nombre);
        return new RedirectView("/editoriales/todas");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarEditorial(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("editorialformulario");
        mav.addObject("editorial", servicio.buscarPorId(id));
        mav.addObject("title", "Editar Editorial");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam("id") String id, @RequestParam("nombre") String nombre) {
        servicio.modificarEditorial(id, nombre);
        return new RedirectView("/editoriales/todas");
    }
    
    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        servicio.habilitar(id);
        return new RedirectView("/editoriales/todas");
    }
    
    @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable String id) {
        servicio.deshabilitar(id);
        return new RedirectView("/editoriales/todas");
    }
}
