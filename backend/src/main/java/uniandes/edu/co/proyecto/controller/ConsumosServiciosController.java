package uniandes.edu.co.proyecto.controller;

import java.util.Collection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uniandes.edu.co.proyecto.model.ConsumoServicio;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository;
import uniandes.edu.co.proyecto.repository.ReservaAlojamientoRepository;
import uniandes.edu.co.proyecto.repository.ServicioRepository;
import uniandes.edu.co.proyecto.repository.UsuarioRepository;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository.BuenosClientes;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository.ConsumoServicioCliente;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository.consumosHabitacion;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository.ocupacionHabitacion;
import uniandes.edu.co.proyecto.repository.ConsumoServicioRepository.serviciosMasSolicitados;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



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
    
    /**
     * Obtiene todos los consumos de servicios
     * @param model
     * @return
     */
    @GetMapping("/consumosServicios")
    public String getAll(Model model){
        model.addAttribute("consumosServicios", consumosServiciosRepository.getAll());
        return "ConsumosServicios/lista"; 
    }

    /**
     * crea un nuevo consumo de servicio
     * @param model
     * @return
     */
    @GetMapping("/consumosServicios/nuevo")
    public String nuevo(Model model){
        model.addAttribute("consumoServicio", new ConsumoServicio());
        model.addAttribute("reservasAlojamiento", reservaAlojamientoRepository.getAll());
        model.addAttribute("servicios", servicioRepository.getAll());
        model.addAttribute("usuarios", usuarioRepository.getAll());

        return "ConsumosServicios/nuevo";
    }

    /**
     * Guarda un nuevo consumo de servicio
     * @param consumoServicio
     * @return
     */
    @PostMapping("/consumosServicios/nuevo/guardar")
    public String guardar(@ModelAttribute ConsumoServicio consumoServicio){
        consumoServicio.getReserva_alojamiento().setSaldo(consumoServicio.getReserva_alojamiento().getSaldo() + consumoServicio.getCosto());
        consumosServiciosRepository.insert(consumoServicio.getId(), consumoServicio.getEstado().name(), consumoServicio.getFecha_inicio(), consumoServicio.getFecha_fin(), consumoServicio.getCosto(), consumoServicio.getReserva_alojamiento().getId(), consumoServicio.getServicio().getId(), consumoServicio.getEmisor().getId());
        return "redirect:/consumosServicios";
    }
    
    /**
     * Edita un consumo de servicio
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/consumosServicios/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model){
        ConsumoServicio consumoServicio = consumosServiciosRepository.getById(id);
        if(consumoServicio != null){
            model.addAttribute("consumoServicio", consumoServicio);
            model.addAttribute("reservasAlojamiento", reservaAlojamientoRepository.getAll());
            model.addAttribute("servicios", servicioRepository.getAll());
            model.addAttribute("usuarios", usuarioRepository.getAll());
            return "ConsumosServicios/editar";
        }
        else{
            return "redirect:/consumosServicios";
        }
    }

    /**
     * Guarda la edición de un consumo de servicio
     * @param id
     * @param consumoServicio
     * @return
     */
    @PostMapping("/consumosServicios/editar/{id}/guardar")
    public String guardarEdicion(@PathVariable("id") Long id, @ModelAttribute ConsumoServicio consumoServicio){
        consumosServiciosRepository.update(id, consumoServicio.getEstado().name(), consumoServicio.getFecha_inicio(), consumoServicio.getFecha_fin(), consumoServicio.getCosto(), consumoServicio.getReserva_alojamiento().getId(), consumoServicio.getServicio().getId(), consumoServicio.getEmisor().getId());
        return "redirect:/consumosServicios";
    }

    /**
     * Elimina un consumo de servicio
     * @param id
     * @return
     */
    @GetMapping("/consumosServicios/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id){
        consumosServiciosRepository.deleteById(id);
        return "redirect:/consumosServicios";
    }

    //CONSULTAS AVANZADAS

    /**
     * Obtiene el recaudo de los servicios en un rango de fechas, despliega el formulario para ingresar las fechas
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/recaudo/fecha")
    public String getRecaudosServiciosFecha(Model model, String fecha_inicio, String fecha_fin){

    model.addAttribute("fecha_inicio", fecha_inicio);
    model.addAttribute("fecha_fin", fecha_fin);

    return "ConsumosServicios/recaudoFecha";
    }


    /**
     * Obtiene el recaudo de los servicios en un rango de fechas, despliega el resultado de la consulta
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/recaudo")
    public String getRecaudosServicios(Model model, @RequestParam String fecha_inicio, @RequestParam String fecha_fin){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha_inicio, formatter);
        LocalDate date2 = LocalDate.parse(fecha_fin, formatter);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Convierte la fecha a la representación deseada
        String formattedDate = date.format(outputFormatter);
        String formattedDate2 = date2.format(outputFormatter);
        

        
        Collection<consumosHabitacion> recaudos = consumosServiciosRepository.getRecaudoServicios(formattedDate, formattedDate2);
        model.addAttribute("recaudos", recaudos);
        model.addAttribute("fecha_inicio", formattedDate);
        model.addAttribute("fecha_fin", formattedDate2);
    

        if(recaudos.iterator().hasNext()){
            model.addAttribute("recaudo", recaudos.iterator().next().getRecaudo());
            model.addAttribute("habitacion", recaudos.iterator().next().getHabitacion());
        }
        else
        {
            model.addAttribute("recaudo", "No hay recaudos");
            model.addAttribute("habitacion", "No hay recaudos");
        }
        System.out.println(recaudos);

        return "ConsumosServicios/recaudos";
    }

    /**
     * Obtiene el ranking de los servicios mas solicitados en un rango de fechas, despliega el formulario para ingresar las fechas
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/ranking/fecha")
    public String getRankingServiciosFecha(Model model, String fecha_inicio, String fecha_fin){
            model.addAttribute("fecha_inicio", fecha_inicio);
    model.addAttribute("fecha_fin", fecha_fin);

    return "ConsumosServicios/rankingFecha";
    }

    /**
     * Obtiene el ranking de los servicios mas solicitados en un rango de fechas, despliega el resultado de la consulta
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/ranking")
    public String getRankingServicios(Model model, @RequestParam String fecha_inicio, @RequestParam String fecha_fin){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha_inicio, formatter);
        LocalDate date2 = LocalDate.parse(fecha_fin, formatter);
        System.out.println(date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Convierte la fecha a la representación deseada
        String formattedDate = date.format(outputFormatter);
        String formattedDate2 = date2.format(outputFormatter);

        Collection<serviciosMasSolicitados> ranking = consumosServiciosRepository.getServiciosMasSolicitados(formattedDate, formattedDate2);
        model.addAttribute("fecha_inicio", formattedDate);
        model.addAttribute("fecha_fin", formattedDate2);
        model.addAttribute("ranking", ranking);
        if (ranking.iterator().hasNext()){
            model.addAttribute("nombre", ranking.iterator().next().getNombre());
            model.addAttribute("cantidad", ranking.iterator().next().getCantidad());
        }
        else
        {
            model.addAttribute("nombre", "No hay servicios");
            model.addAttribute("cantidad", "No hay servicios");
        }
        
        System.out.println(ranking);

        return "ConsumosServicios/ranking";
    }

    /**
     * Obtiene la ocupacion de cada habitacion en un rango de fechas, despliega el formulario para ingresar las fechas
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/ocupacion/fecha")
    public String getOcupacionHabitacionFecha(Model model, String fecha_inicio, String fecha_fin){
        model.addAttribute("fecha_inicio", fecha_inicio);
        model.addAttribute("fecha_fin", fecha_fin);
        return "ConsumosServicios/ocupacionFecha";
    }

    /**
     * Obtiene la ocupacion de cada habitacion en un rango de fechas, despliega el resultado de la consulta
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/ocupacion")
    //debera ir en reservas de alojamiento
    public String getOcupacionHabitacion(Model model, @RequestParam String fecha_inicio, @RequestParam String fecha_fin){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha_inicio, formatter);
        LocalDate date2 = LocalDate.parse(fecha_fin, formatter);
        System.out.println(date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Convierte la fecha a la representación deseada
        String formattedDate = date.format(outputFormatter);
        String formattedDate2 = date2.format(outputFormatter);

        Collection<ocupacionHabitacion> ocupacion = consumosServiciosRepository.getOcupacionHabitacion(formattedDate, formattedDate2);
        model.addAttribute("ocupacion", ocupacion);
        model.addAttribute("fecha_inicio", formattedDate);
        model.addAttribute("fecha_fin", formattedDate2);

        if(ocupacion.iterator().hasNext()){
            model.addAttribute("dias", ocupacion.iterator().next().getDias());
            model.addAttribute("habitacion", ocupacion.iterator().next().getHabitacion());
            model.addAttribute("porcentaje", ocupacion.iterator().next().getPorcentaje());
        }
        else
        {
            model.addAttribute("dias", "No hay ocupación");
            model.addAttribute("habitacion", "No hay ocupación");
            model.addAttribute("porcentaje", "No hay ocupación");
        }
        System.out.println(ocupacion);

        return "ConsumosServicios/ocupacion";
    }

    /**
     * Obtiene el gasto de un usuario en un rango de fechas, despliega el formulario para ingresar las fechas
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @param documento
     * @return
     */
    @GetMapping("/consumosServicios/gastoUsuario/fecha")
    //deberia ir en reservas de alojamiento
    public String getGastoUsuarioFecha(Model model, String fecha_inicio, String fecha_fin, String documento){
        model.addAttribute("fecha_inicio", fecha_inicio);
        model.addAttribute("fecha_fin", fecha_fin);
        model.addAttribute("documento", documento);
        return "ConsumosServicios/gastoUsuarioFecha";
    }

    /**
     * Obtiene el gasto de un usuario en un rango de fechas, despliega el resultado de la consulta
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @param documento
     * @return
     */
    @GetMapping("/consumosServicios/gastoUsuario")
    public String getGastoUsuario(Model model, @RequestParam String fecha_inicio, @RequestParam String fecha_fin, @RequestParam String documento){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha_inicio, formatter);
        LocalDate date2 = LocalDate.parse(fecha_fin, formatter);
        System.out.println(date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Convierte la fecha a la representación deseada
        String formattedDate = date.format(outputFormatter);
        String formattedDate2 = date2.format(outputFormatter);

        Collection<ConsumoServicioCliente> gasto = consumosServiciosRepository.getConsumoServicio(formattedDate, formattedDate2, documento);
        model.addAttribute("gasto", gasto);
        model.addAttribute("fecha_inicio", formattedDate);
        model.addAttribute("fecha_fin", formattedDate2);
        model.addAttribute("documento", documento);

        if(gasto.iterator().hasNext()){
            model.addAttribute("nombre", gasto.iterator().next().getNombre());
            model.addAttribute("documento", gasto.iterator().next().getDocumento());
            model.addAttribute("total", gasto.iterator().next().getTotal());
        }
        else
        {
            model.addAttribute("nombre", "No hay gastos");
            model.addAttribute("documento", "No hay gastos");
            model.addAttribute("total", "No hay gastos");
        }
        System.out.println(gasto);

        return "ConsumosServicios/gastoUsuario";
    }

    /**
     * Obtiene los buenos clientes en un rango de fechas, despliega el formulario para ingresar las fechas
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/buenosClientes/fecha")
    public String getBuenosClientesFecha(Model model, String fecha_inicio, String fecha_fin){
        model.addAttribute("fecha_inicio", fecha_inicio);
        model.addAttribute("fecha_fin", fecha_fin);
        return "ConsumosServicios/buenClienteFecha";
    }

    /**
     * Obtiene los buenos clientes en un rango de fechas, despliega el resultado de la consulta
     * @param model
     * @param fecha_inicio
     * @param fecha_fin
     * @return
     */
    @GetMapping("/consumosServicios/buenosClientes")
    public String getBuenosClientes(Model model, @RequestParam String fecha_inicio, @RequestParam String fecha_fin){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(fecha_inicio, formatter);
        LocalDate date2 = LocalDate.parse(fecha_fin, formatter);
        System.out.println(date);
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yy");

        // Convierte la fecha a la representación deseada
        String formattedDate = date.format(outputFormatter);
        String formattedDate2 = date2.format(outputFormatter);

        Collection<BuenosClientes> buenosClientes = consumosServiciosRepository.getBuenosClientes(formattedDate, formattedDate2);
        model.addAttribute("clientes", buenosClientes);
        model.addAttribute("fecha_inicio", formattedDate);
        model.addAttribute("fecha_fin", formattedDate2);

        if(buenosClientes.iterator().hasNext()){
            model.addAttribute("nombre", buenosClientes.iterator().next().getNombre());
            model.addAttribute("documento", buenosClientes.iterator().next().getDocumento());
            model.addAttribute("dias", buenosClientes.iterator().next().getDias());
            model.addAttribute("gasto", buenosClientes.iterator().next().getGasto());
        }
        else
        {
            model.addAttribute("nombre", "No hay buenos clientes");
            model.addAttribute("documento", "No hay buenos clientes");
            model.addAttribute("dias", "No hay buenos clientes");
            model.addAttribute("gasto", "No hay buenos clientes");
        }
        System.out.println(buenosClientes);

        return "ConsumosServicios/buenosClientes";
    }
}
