package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import uniandes.edu.co.proyecto.model.ConsumoServicio;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository;
import uniandes.edu.co.proyecto.repository.ReservaAlojamientoRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ConsumosServiciosController {

    @Autowired
    private ConsumoServicioRepository consumosServiciosRepository;

    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @Autowired
    private ServicioRepository servicioRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @GetMapping("/consumosServicios")
    public String getAll(Model model){
        model.addAttribute("consumosServicios", consumosServiciosRepository.getAll());
        return "ConsumosServicios/lista";
    }

    @GetMapping("/consumosServicios/nuevo")
    public String nuevo(Model model){
        model.addAttribute("consumoServicio", new ConsumoServicio());
        model.addAttribute("reservasAlojamiento", reservaAlojamientoRepository.getAll());
        model.addAttribute("servicios", servicioRepository.getAll());
        model.addAttribute("usuarios", usuarioRepository.getAll());

        return "ConsumosServicios/nuevo";
    }

    @PostMapping("/consumosServicios/nuevo/guardar")
    public String guardar(@ModelAttribute ConsumoServicio consumoServicio){
        consumosServiciosRepository.insert(consumoServicio.getId(), consumoServicio.getEstado().name(), consumoServicio.getFecha_inicio(), consumoServicio.getFecha_fin(), consumoServicio.getCosto(), consumoServicio.getReserva_alojamiento().getId(), consumoServicio.getServicio().getId(), consumoServicio.getEmisor().getId());
        return "redirect:/consumosServicios";
    }
    
    
}
