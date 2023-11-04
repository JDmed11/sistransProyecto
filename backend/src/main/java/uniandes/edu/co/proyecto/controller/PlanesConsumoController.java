package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.model.PlanConsumo;
import uniandes.edu.co.proyecto.repository.PlanConsumoRepository;

@Controller
public class PlanesConsumoController {
    
    @Autowired
    private PlanConsumoRepository planConsumoRepository;

    @GetMapping("/planesConsumo")
    public String planesConsumo(Model model) {
        model.addAttribute("planesConsumo", planConsumoRepository.getAll());
        return "PlanesConsumo/lista";
    }

    @GetMapping("/planesConsumo/nuevo")
    public String crear(Model model) {
        model.addAttribute("planConsumo", new PlanConsumo());
        return "PlanesConsumo/nuevo";
    }

    @PostMapping("/planesConsumo/nuevo/guardar")
    public String guardar(@ModelAttribute PlanConsumo planConsumo) {
        planConsumoRepository.insert(planConsumo.getId(), planConsumo.getNombre(), planConsumo.getDescripcion());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        PlanConsumo planConsumo = planConsumoRepository.getById(id);
        if (planConsumo != null) {
            model.addAttribute("planConsumo", planConsumo);
            return "PlanesConsumo/editar";
        }
        return "PlanesConsumo/editar";
    }

    @PostMapping("/planesConsumo/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id, @ModelAttribute PlanConsumo planConsumo) {
        planConsumoRepository.update(id, planConsumo.getNombre(), planConsumo.getDescripcion());
        return "redirect:/planesConsumo";
    }

    @GetMapping("/planesConsumo/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        planConsumoRepository.delete(id);
        return "redirect:/planesConsumo";
    }
}
