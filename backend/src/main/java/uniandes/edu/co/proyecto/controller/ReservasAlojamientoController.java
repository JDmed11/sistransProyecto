package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import uniandes.edu.co.proyecto.model.ReservaAlojamiento;
import uniandes.edu.co.proyecto.repository.HabitacionRepository;
import uniandes.edu.co.proyecto.repository.PlanConsumoRepository;
import uniandes.edu.co.proyecto.repository.ReservaAlojamientoRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class ReservasAlojamientoController {
    @Autowired
    private ReservaAlojamientoRepository reservaAlojamientoRepository;

    @Autowired
    private HabitacionRepository habitacionRepository;
    @Autowired
    private PlanConsumoRepository planConsumoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    @GetMapping("/reservasAlojamiento")
    public String getAll(Model model) {
        model.addAttribute("reservasAlojamiento", reservaAlojamientoRepository.getAll());
        return "ReservasAlojamiento/lista";
    }

    @GetMapping("/reservasAlojamiento/nuevo")
    public String crear(Model model){ //,@PathVariable("fecha_entrada") Date fecha_entrada, @PathVariable("fecha_salida") Date fecha_salida, @PathVariable("tipo_habitacion") int tipo_habitacion){
        model.addAttribute("reservaAlojamiento", new ReservaAlojamiento());
        model.addAttribute("habitaciones", habitacionRepository.getAll());
        model.addAttribute("planesConsumo", planConsumoRepository.getAll());
        model.addAttribute("clientes", usuarioRepository.getByTipo("cliente"));
        return "ReservasAlojamiento/nuevo";
    }

    @PostMapping("/reservasAlojamiento/nuevo/guardar")
    public String guardar(@ModelAttribute ReservaAlojamiento reservaAlojamiento) {
        reservaAlojamientoRepository.insert(reservaAlojamiento.getId(), reservaAlojamiento.getFecha_entrada(), reservaAlojamiento.getFecha_salida(), reservaAlojamiento.getAcompaniantes(), reservaAlojamiento.getEstado().toString(), reservaAlojamiento.getHabitacion().getNumero_habitacion(), reservaAlojamiento.getPlan_consumo().getId(), reservaAlojamiento.getCliente().getId());
        return "redirect:/reservasAlojamiento";
    }

    @GetMapping("/reservasAlojamiento/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        ReservaAlojamiento reservaAlojamiento = reservaAlojamientoRepository.getById(id);
        if (reservaAlojamiento != null) {
            model.addAttribute("reserva", reservaAlojamiento);
            model.addAttribute("habitaciones", habitacionRepository.getAll());
            model.addAttribute("planesConsumo", planConsumoRepository.getAll());
            model.addAttribute("clientes", usuarioRepository.getByTipo("cliente"));
            return "ReservasAlojamiento/editar";
        }
        return "ReservasAlojamiento/editar";
    }

    @PostMapping("/reservasAlojamiento/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id, @ModelAttribute ReservaAlojamiento reservaAlojamiento) {
        reservaAlojamientoRepository.update(id, reservaAlojamiento.getFecha_entrada(), reservaAlojamiento.getFecha_salida(), reservaAlojamiento.getAcompaniantes(), reservaAlojamiento.getEstado().toString(), reservaAlojamiento.getHabitacion().getNumero_habitacion(), reservaAlojamiento.getPlan_consumo().getId(), reservaAlojamiento.getSaldo());
        return "redirect:/reservasAlojamiento";
    }
}
