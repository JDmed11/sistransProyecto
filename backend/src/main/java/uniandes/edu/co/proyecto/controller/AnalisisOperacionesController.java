package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import uniandes.edu.co.proyecto.repository.ReservaAlojamientoRepository;

@Controller
public class AnalisisOperacionesController {
    
    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @GetMapping("/analisisOperaciones")
    public String analizarOperaciones(Model model) {
        Date diaMayorOcupacion = reservaAlojamientoRepository.findFechaMayorOcupacion();
        Date diaMayorIngreso = reservaAlojamientoRepository.findFechaMayoresIngresos();
        Date diaMenorDemanda = reservaAlojamientoRepository.findFechaMenorDemanda();

        model.addAttribute("diaMayorOcupacion", diaMayorOcupacion);
        model.addAttribute("diaMayorIngreso", diaMayorIngreso);
        model.addAttribute("diaMenorDemanda", diaMenorDemanda);

        return "AnalisisOperaciones/vista";
    }
}
