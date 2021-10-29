package com.example.libreria.controladores;

import com.example.libreria.entidades.Autor;
import com.example.libreria.entidades.Editorial;
import com.example.libreria.entidades.Libro;
import com.example.libreria.servicios.AutorServicio;
import com.example.libreria.servicios.EditorialServicio;
import com.example.libreria.servicios.LibroServicio;
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
@RequestMapping("/libros")
public class LibroControlador {
    
    @Autowired
    private LibroServicio servicio;
    @Autowired
    private AutorServicio autorServicio;
    @Autowired
    private EditorialServicio editorialServicio;

    @GetMapping("/todos")
    public ModelAndView mostrarLibros(){
        ModelAndView mav = new ModelAndView("libro-lista");
        
        List<Libro> libro = servicio.obtenerLibros();
        mav.addObject("libro", libro);
        
        return mav;
    }
    
    @GetMapping("/crear")
    public ModelAndView crearLibro(){
        ModelAndView mav = new ModelAndView("libroformulario");
        mav.addObject("libro", new Libro());
        mav.addObject("title", "Crear Libro");
        mav.addObject("autores", autorServicio.obtenerAutor());
        mav.addObject("editoriales", editorialServicio.obtenerEditorial()); 
        mav.addObject("action","guardar");
        return mav;
    }
    
        @PostMapping("/guardar")
    public RedirectView guardarLibro(@RequestParam Long isbn,@RequestParam String titulo, @RequestParam Integer anio,@RequestParam Integer ejemplares, @RequestParam("autor") String idAutor,@RequestParam("editorial") String idEditorial){
           servicio.crearLibro(isbn, titulo, anio, ejemplares, idAutor, idEditorial);
        return new RedirectView("/libros/todos");
    }
    
    /*@PostMapping("/eliminar/{id}") 
    public RedirectView eliminarLibro(@PathVariable String id) {
        servicio.eliminarLibro(id);
        return new RedirectView("/libros/todos");
    }*/
    
    @GetMapping("/guardar")
    public RedirectView modificarEditorial(@RequestParam String id, @RequestParam Long isbn, @RequestParam String titulo,@RequestParam Integer anio, @RequestParam Integer ejemplares, @RequestParam Integer ejemplaresPrestados, @RequestParam Integer ejemplaresRestantes, @RequestParam Boolean alta, @RequestParam Autor autor, @RequestParam Editorial editorial){
           servicio.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);
        return new RedirectView("/ibros/todos");
    }
    
    @GetMapping("/editar/{id}")
    public ModelAndView editarLibro(@PathVariable String id) {
        ModelAndView mav = new ModelAndView("libroformulario");
        mav.addObject("libros", servicio.buscarPorId(id));
        mav.addObject("title", "Editar Libro");
        mav.addObject("action", "modificar");
        return mav;
    }
    
    @PostMapping("/modificar")
    public RedirectView modificar(@RequestParam("id") String id, @RequestParam("isbn") Long isbn,@RequestParam("titulo") String titulo,@RequestParam("anio") Integer anio,@RequestParam("ejemplares") Integer ejemplares,@RequestParam("ejemplaresPrestados") Integer ejemplaresPrestados,@RequestParam("ejemplaresRestantes") Integer ejemplaresRestantes,@RequestParam("alta") Boolean alta,@RequestParam("autor") Autor autor,@RequestParam("editorial") Editorial editorial) {
        servicio.modificarLibro(id, isbn, titulo, anio, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta, autor, editorial);
        return new RedirectView("/libros/todos");
    }
    @PostMapping("/habilitar/{id}")
    public RedirectView habilitar(@PathVariable String id) {
        servicio.habilitar(id);
        return new RedirectView("/libros/todos");
    }
    
     @PostMapping("/deshabilitar/{id}")
    public RedirectView deshabilitar(@PathVariable String id) {
        servicio.deshabilitar(id);
        return new RedirectView("/libros/todos");
    }
} 
