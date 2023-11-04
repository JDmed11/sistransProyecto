package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;

import uniandes.edu.co.proyecto.model.TipoHabitacion;
import uniandes.edu.co.proyecto.repository.TipoHabitacionRepository;

/**
 * COMPLETADA
 */

@Controller
public class TiposHabitacionController {

    @Autowired
    private TipoHabitacionRepository tiposHabitacionRepository;
    
    /**
     * Muestra todos los tipos de habitacion
     * @param model Modelo
     * @return Lista de tipos de habitacion
     */
    @GetMapping("/tiposHabitacion")
    public String getAll(Model model){
        model.addAttribute("tiposHabitacion", tiposHabitacionRepository.getAll());
        return "TiposHabitacion/lista";
    }

    /**
     * Crea un nuevo tipo de habitacion
     * @param model Modelo
     * @return Formulario para crear un nuevo tipo de habitacion
     */
    @GetMapping("/tiposHabitacion/nuevo")
    public String crear(Model model){
        model.addAttribute("tipoHabitacion", new TipoHabitacion());
        return "TiposHabitacion/nuevo";
    }

    /**
     * Guarda el nuevo tipo de habitacion
     * @param tipoHabitacion Tipo de habitacion
     * @return Lista de tipos de habitacion
     */
    @PostMapping("/tiposHabitacion/nuevo/guardar")
    public String guardar(@ModelAttribute TipoHabitacion tipoHabitacion){
        tiposHabitacionRepository.insert(tipoHabitacion.getId(), tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad(), tipoHabitacion.getCosto_noche(), tipoHabitacion.getTiene_jacuzzi(), tipoHabitacion.getTiene_cocina(), tipoHabitacion.getTiene_comedor());
        return "redirect:/tiposHabitacion";
    }

    /**
     * Muestra el formulario para editar un tipo de habitacion
     * @param id Id del tipo de habitacion
     * @param model Modelo
     * @return Formulario para editar un tipo de habitacion
     */
    @GetMapping("/tiposHabitacion/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        TipoHabitacion tipoHabitacion = tiposHabitacionRepository.getById(id);
        if (tipoHabitacion != null) {
            model.addAttribute("tipoHabitacion", tipoHabitacion);
            return "TiposHabitacion/editar";
        }
        else{
            return "redirect:/tiposHabitacion";
        }
    }

    /**
     * Guarda la edicion de un tipo de habitacion
     * @param id Id del tipo de habitacion
     * @param tipoHabitacion Tipo de habitacion
     * @return Lista de tipos de habitacion
     */
    @PostMapping("/tiposHabitacion/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id, @ModelAttribute TipoHabitacion tipoHabitacion){
        tiposHabitacionRepository.update(id, tipoHabitacion.getNombre(), tipoHabitacion.getCapacidad(), tipoHabitacion.getCosto_noche(), tipoHabitacion.getTiene_jacuzzi(), tipoHabitacion.getTiene_cocina(), tipoHabitacion.getTiene_comedor());
        return "redirect:/tiposHabitacion";
    }

    /**
     * Elimina un tipo de habitacion
     * @param id Id del tipo de habitacion
     * @return Lista de tipos de habitacion
     */
    @GetMapping("/tiposHabitacion/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id){
        TipoHabitacion tipoHabitacion = tiposHabitacionRepository.getById(id);
        if (tipoHabitacion != null) {
            tiposHabitacionRepository.delete(id);
            return "redirect:/tiposHabitacion";
        }
        else{
            return "redirect:/tiposHabitacion";
        }
    }
}
