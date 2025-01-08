package com.sa.appexamelaboratorio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sa.appexamelaboratorio.model.Usuario;
import com.sa.appexamelaboratorio.service.ExameService;
import com.sa.appexamelaboratorio.service.LabService;
import com.sa.appexamelaboratorio.service.PacienteService;
import com.sa.appexamelaboratorio.service.UsuarioService;

@Controller
public class MainController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LabService labService;

    @Autowired
    private ExameService exameService;

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/main")
    public String exibirHomePage(Model model) {
        System.out.println("Usuário autenticado: " + org.springframework.security.core.context.SecurityContextHolder
                .getContext().getAuthentication().getName());

        Optional<Usuario> usuario = usuarioService
                .buscarPorEmail(org.springframework.security.core.context.SecurityContextHolder
                        .getContext().getAuthentication().getName());

        model.addAttribute("totalExames", exameService.contarExames());
        model.addAttribute("totalPacientes", pacienteService.contarPacientes());
        model.addAttribute("totalExamesAgendados", exameService.contarExamesStatus("AGENDADO"));
        model.addAttribute("totalLaboratorios", labService.contarLaboratorios());
        model.addAttribute("usuario", usuario.get());
        return "main";
    }
}
