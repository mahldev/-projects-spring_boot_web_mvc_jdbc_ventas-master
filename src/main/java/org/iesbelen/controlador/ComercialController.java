package org.iesbelen.controlador;

import org.iesbelen.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("comerciales")
public class ComercialController {

    private final ComercialService comercialService;

    @Autowired
    public ComercialController(final ComercialService comercialService) {
        this.comercialService = comercialService;
    }

    @GetMapping
    public String getAll(final Model model) {
        final var comerciantes = comercialService.getAll();
        model.addAttribute("comerciales", comerciantes);

        return "comerciales";
    }
}
