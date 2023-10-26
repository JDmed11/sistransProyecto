package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import uniandes.edu.co.proyecto.repository.UsuarioRepository;

@Controller
public class ClienteController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/cliente")
    public String mostrar(Model model){
        model.addAttribute("clientes", usuarioRepository.getByTipo("cliente"));
        return "clienteview";
    }
}
