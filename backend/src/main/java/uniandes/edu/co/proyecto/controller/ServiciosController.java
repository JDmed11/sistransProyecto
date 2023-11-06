package uniandes.edu.co.proyecto.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.repository.ServicioRepository;

@Controller
public class ServiciosController {
    
    @Autowired
    private ServicioRepository servicioRepository;

    @GetMapping("/servicios")
    public String getFilteredServices(Model model,
                                      @RequestParam(required = false) Double precioMin,
                                      @RequestParam(required = false) Double precioMax,
                                      @RequestParam(required = false) String fechaInicio,
                                      @RequestParam(required = false) String fechaFin,
                                      @RequestParam(required = false) Long empleadoId,
                                      @RequestParam(required = false) Long tipoServicioId,
                                      @RequestParam(required = false) Long categoriaServicioId) {
        LocalDate inicio = fechaInicio != null ? LocalDate.parse(fechaInicio) : null;
        LocalDate fin = fechaFin != null ? LocalDate.parse(fechaFin) : null;
        // Llamar al método del repo
        List<Servicio> servicios = servicioRepository.findWithFilters(
                precioMin, precioMax, inicio, fin, empleadoId, tipoServicioId, categoriaServicioId);
        
        // Agregar servicios filtrados al modelo
        model.addAttribute("servicios", servicios);
        
        return "Servicios/lista";
    }
}