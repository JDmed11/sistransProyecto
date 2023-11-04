package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.model.Usuario;
import uniandes.edu.co.proyecto.repository.TiposUsuarioRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

/**
 * Clase que representa el controlador de la vista de usuarios
 * COMPLETA
 */
@Controller
public class UsuariosController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TiposUsuarioRepository tipoUsuarioRepository;

    /**
     * Método que retorna la lista de usuarios
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @GetMapping("/usuarios")
    public String getAll(Model model){
        model.addAttribute("usuarios", usuarioRepository.getAll());
        return "Usuarios/lista";
    }

    /**
     * Método que retorna la vista para crear un usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @GetMapping("/usuarios/nuevo")
    public String crear(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("tiposUsuario", tipoUsuarioRepository.getAll());
        return "Usuarios/nuevo";
    }

    /**
     * Método que retorna la vista para crear un usuario
     * Si el usuario es cliente, se inserta en la tabla de clientes
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @PostMapping("/usuarios/nuevo/guardar")
    public String guardar(@ModelAttribute Usuario usuario){
        usuarioRepository.insert(usuario.getId(), usuario.getTipoDocumento(), usuario.getNumeroDocumento(), usuario.getNombre(), usuario.getCorreo(), usuario.getTipoUsuario().getId());

            return "redirect:/usuarios";
        
    }

    /**
     * Método que retorna la vista para editar un usuario
     * @param tipoDocumento Tipo de documento del usuario
     * @param numeroDocumento Número de documento del usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        model.addAttribute("usuario", usuarioRepository.getById(id));
        if (usuarioRepository.getById(id) != null) {
            model.addAttribute("tiposUsuario", tipoUsuarioRepository.getAll());
            return "Usuarios/editar";
        }
        else{
            return "redirect:/usuarios";
        }
    }

    /**
     * Método que actualiza un usuario
     * Si el usuario es cliente y se cambia el tipo de usuario, se elimina de la tabla de clientes
     * Si el usuario no es cliente y se cambia el tipo de usuario, se inserta en la tabla de clientes
     * @param tipoDocumento Tipo de documento del usuario
     * @param numeroDocumento Número de documento del usuario
     * @param usuario Usuario a actualizar
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @PostMapping("/usuarios/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id ,@ModelAttribute Usuario usuario){
        usuarioRepository.update(id, usuario.getTipoDocumento(), usuario.getNumeroDocumento() , usuario.getNombre(), usuario.getCorreo(), usuario.getTipoUsuario().getId());
        return "redirect:/usuarios";
    }

    /**
     * Método que elimina un usuario
     * Si el usuario es cliente, se elimina de la tabla de clientes
     * Si el usuario no es cliente, no se elimina de la tabla de clientes
     * @param tipoDocumento Tipo de documento del usuario
     * @param numeroDocumento Número de documento del usuario
     * @param model Modelo utilizado por Thymeleaf
     * @return String Nombre de la vista
     */
    @GetMapping("/usuarios/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, Model model){
        if (usuarioRepository.getById(id) != null) {
            usuarioRepository.delete(id);
            return "redirect:/usuarios";
        }
        else{
            return "redirect:/usuarios";
        }

    }
}
