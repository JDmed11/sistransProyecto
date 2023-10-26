package uniandes.edu.co.proyecto.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecepcionistaController {
    
    @GetMapping("/recepcionista")
    public String recepcionista(){
        return "recepcionistaview";
    }
}
