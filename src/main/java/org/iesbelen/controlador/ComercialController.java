package org.iesbelen.controlador;

import org.iesbelen.modelo.Comercial;
import org.iesbelen.service.ComercialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("comercial")
public class ComercialController {

    @Autowired
    private ComercialService comercialService;

    private RedirectView comericalRedirect = new RedirectView("/comercial");

    @GetMapping
    public String getAll(final Model model) {
        final var comerciales = comercialService.getAll();
        model.addAttribute("comerciales", comerciales);
        return "comercial/comerciales";
    }

    @GetMapping("{id}")
    public String getComerial(final Model model, @PathVariable final int id) {
        return comercialService.find(id)
                .map(comercial -> {
                    model.addAttribute("comercial", comercial);
                    return "comercial/detalles";
                })
                .orElse("404Error");
    }

    @PostMapping("crear")
    public RedirectView crear(@ModelAttribute final Comercial comercial) {
        comercialService.create(comercial);
        return comericalRedirect;
    }

    @GetMapping("crear")
    public String crearVista(final Model model) {
        model.addAttribute("comercial", new Comercial());
        return "comercial/crear";
    }

    @PostMapping("borrar/{id}")
    public RedirectView eliminar(@PathVariable final int id) {
        comercialService.remove(id);
        return comericalRedirect;
    }

    @PostMapping("editar/{id}")
    public RedirectView editar(@ModelAttribute final Comercial comercial) {
        comercialService.update(comercial);
        return comericalRedirect;
    }

    @GetMapping("editar/{id}")
    public String editarVista(final Model model, @PathVariable final int id) {
        return comercialService.find(id).map(comercial -> {
            model.addAttribute(comercial);
            return "comercial/editar";
        }).orElse("404Error");
    }

}
