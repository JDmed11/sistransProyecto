package uniandes.edu.co.proyecto.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import uniandes.edu.co.proyecto.model.ReservaAlojamiento;
import uniandes.edu.co.proyecto.model.Usuario;
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
    public String crear(Model model){
        model.addAttribute("reservaAlojamiento", new ReservaAlojamiento());
        model.addAttribute("habitaciones", habitacionRepository.getAll());
        model.addAttribute("planesConsumo", planConsumoRepository.getAll());

        return "ReservasAlojamiento/nuevo";
    }

    @PostMapping("/reservasAlojamiento/nuevo/guardar")
    public String guardar(@ModelAttribute ReservaAlojamiento reservaAlojamiento, @RequestParam String cliente) {
        Usuario cliente1 = usuarioRepository.getByNumeroDocumento(cliente);
        if (cliente != null) {
            reservaAlojamiento.setCliente(cliente1);
        }
        else{
            return "errorRA";
        }
        
        Date fecha_entrada = reservaAlojamiento.getFecha_entrada();
        Date fecha_salida = reservaAlojamiento.getFecha_salida();
        Long habitacion = reservaAlojamiento.getHabitacion().getNumero_habitacion();
        List<ReservaAlojamiento> reservas = reservaAlojamientoRepository.getReservasHabitacion(fecha_entrada, fecha_salida, habitacion);

        if (reservas.size() > 0 || fecha_entrada.after(fecha_salida) || cliente1 == null) {
            return "errorRA";
        }else{
            reservaAlojamientoRepository.insert(reservaAlojamiento.getId(), reservaAlojamiento.getFecha_entrada(), reservaAlojamiento.getFecha_salida(), reservaAlojamiento.getAcompaniantes(), reservaAlojamiento.getEstado().toString(), reservaAlojamiento.getHabitacion().getNumero_habitacion(), reservaAlojamiento.getPlan_consumo().getId(), reservaAlojamiento.getCliente().getId());
        }
        
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

    @GetMapping("/reservasAlojamiento/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        reservaAlojamientoRepository.delete(id);
        return "redirect:/reservasAlojamiento";
    }
}
