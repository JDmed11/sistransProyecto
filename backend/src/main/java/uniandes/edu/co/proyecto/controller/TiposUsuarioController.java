package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.model.TipoUsuario;
import uniandes.edu.co.proyecto.repository.TiposUsuarioRepository;

/**
 * Clase que representa el controlador de tipos de usuario
 * COMPLETA
 */
@Controller
public class TiposUsuarioController {

    /**
     * Repositorio de tipos de usuario
     */
    @Autowired
    private TiposUsuarioRepository tiposUsuarioRepository;

    /**
     * Retorna todos los tipos de usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return Vista de lista de tipos de usuario
     */
    @GetMapping("/tiposUsuario")
    public String getAll(Model model){
        model.addAttribute("tiposUsuario", tiposUsuarioRepository.getAll());
        return "TiposUsuario/lista";
    }

    /**
     * Retorna un tipo de usuario dado su id
     * @param id Id del tipo de usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return Vista de detalle de tipo de usuario
     */
    @GetMapping("/tiposUsuario/nuevo")
    public String crear(Model model){
        model.addAttribute("tiposUsuario", new TipoUsuario());
        return "TiposUsuario/nuevo";
    }

    /**
     * Inserta un tipo de usuario en la base de datos
     * @param tiposUsuario Tipo de usuario a insertar
     * @return Redirección a la lista de tipos de usuario
     */
    @PostMapping("/tiposUsuario/nuevo/guardar")
    public String guardar(@ModelAttribute TipoUsuario tiposUsuario){
        tiposUsuarioRepository.insert(tiposUsuario.getId(), tiposUsuario.getNombre());
        return "redirect:/tiposUsuario";
    }

    /**
     * Retorna un tipo de usuario dado su id
     * @param id Id del tipo de usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return Vista de detalle de tipo de usuario
     */
    @GetMapping("/tiposUsuario/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        TipoUsuario tiposUsuario = tiposUsuarioRepository.getById(id);
        if (tiposUsuario != null) {
            model.addAttribute("tipoUsuario", tiposUsuario);
            return "TiposUsuario/editar";
        }
        else{
            return "redirect:/tiposUsuario";
        }
    }

    /**
     * Actualiza un tipo de usuario en la base de datos
     * @param id Id del tipo de usuario
     * @param tipoUsuario Tipo de usuario a actualizar
     * @return Redirección a la lista de tipos de usuario
     */
    @PostMapping("/tiposUsuario/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id, @ModelAttribute TipoUsuario tipoUsuario){
        tiposUsuarioRepository.update(id, tipoUsuario.getNombre());
        return "redirect:/tiposUsuario";
    }

    /**
     * Elimina un tipo de usuario de la base de datos
     * @param id Id del tipo de usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return Redirección a la lista de tipos de usuario
     */
    @GetMapping("/tiposUsuario/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, Model model){
        TipoUsuario tiposUsuario = tiposUsuarioRepository.getById(id);
        if (tiposUsuario != null) {
            tiposUsuarioRepository.delete(id);
            return "redirect:/tiposUsuario";
        }
        else{
            return "redirect:/tiposUsuario";
        }
    }

}
