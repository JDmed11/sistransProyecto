package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ServicioRepository;

@Controller
public class ServiciosController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String getAll(Model model){
        model.addAttribute("servicios", servicioRepository.getAll());
        return "Servicios/lista";
    }
}
