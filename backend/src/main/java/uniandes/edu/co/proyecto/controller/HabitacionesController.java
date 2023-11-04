package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.model.Habitacion;
import uniandes.edu.co.proyecto.repository.HabitacionRepository;
import uniandes.edu.co.proyecto.repository.TipoHabitacionRepository;

@Controller
public class HabitacionesController {
    
    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private TipoHabitacionRepository tipoHabitacionRepository;

    /**
     * Metodo que retorna la lista de habitaciones
     * @param model Modelo utilizado para pasar la lista de habitaciones a la vista
     * @return La vista de la lista de habitaciones
     */
    @GetMapping("/habitaciones")
    public String getAll(Model model){
        model.addAttribute("habitaciones", habitacionRepository.getAll());
        return "Habitaciones/lista";
    }

    /**
     * Metodo que retorna la vista de crear una habitacion
     * @param model Modelo utilizado para pasar la lista de tipos de habitacion a la vista
     * @return La vista de crear una habitacion
     */
    @GetMapping("/habitaciones/nuevo")
    public String crear(Model model){
        model.addAttribute("habitacion", new Habitacion());
        model.addAttribute("tiposHabitacion", tipoHabitacionRepository.getAll());
        return "Habitaciones/nuevo";
    }

    /**
     * Metodo que guarda una habitacion
     * @param habitacion Habitacion a guardar
     * @return La vista de la lista de habitaciones
     */
    @PostMapping("/habitaciones/nuevo/guardar")
    public String guardar(@ModelAttribute Habitacion habitacion){
        habitacionRepository.insert(habitacion.getNumero_habitacion(), habitacion.getTipo_habitacion().getId());
        return "redirect:/habitaciones";
    }

    /**
     * Metodo que retorna la vista de editar una habitacion
     * @param numero_habitacion Numero de la habitacion a editar
     * @param model Modelo utilizado para pasar la lista de tipos de habitacion a la vista
     * @return La vista de editar una habitacion
     */
    @GetMapping("/habitaciones/editar/{numero_habitacion}")
    public String editar(@PathVariable("numero_habitacion") Long numero_habitacion, Model model){
        model.addAttribute("habitacion", habitacionRepository.getById(numero_habitacion));
        if (habitacionRepository.getById(numero_habitacion) != null){
            model.addAttribute("tiposHabitacion", tipoHabitacionRepository.getAll());
            return "Habitaciones/editar";
        } else {
            return "redirect:/habitaciones";
        }
    }

    /**
     * Metodo que actualiza una habitacion
     * @param numero_habitacion Numero de la habitacion a actualizar
     * @param habitacion Habitacion a actualizar
     * @return La vista de la lista de habitaciones
     */
    @PostMapping("/habitaciones/editar/{numero_habitacion}/guardar")
    public String actualizar(@PathVariable("numero_habitacion") Long numero_habitacion, @ModelAttribute Habitacion habitacion){
        habitacionRepository.update(numero_habitacion, habitacion.getTipo_habitacion().getId());
        return "redirect:/habitaciones";
    }

    /**
     * Metodo que elimina una habitacion
     * @param numero_habitacion Numero de la habitacion a eliminar
     * @return La vista de la lista de habitaciones
     */
    @GetMapping("/habitaciones/eliminar/{numero_habitacion}")
    public String eliminar(@PathVariable("numero_habitacion") Long numero_habitacion){
        habitacionRepository.delete(numero_habitacion);
        return "redirect:/habitaciones";
    }


}
