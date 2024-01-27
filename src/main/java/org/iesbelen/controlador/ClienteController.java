package org.iesbelen.controlador;

import org.iesbelen.modelo.Cliente;
import org.iesbelen.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    private final RedirectView clientesRedirect = new RedirectView("/clientes");

    @GetMapping
    public String listar(final Model model) {
        model.addAttribute("listaClientes", clienteService.listAll());
        return "cliente/clientes";
    }

    @GetMapping("{id}")
    public String getCliente(final Model model, @PathVariable final int id) {
        return clienteService.find(id).map(cliente -> {
            model.addAttribute("cliente", cliente);
            return "cliente/detalles";
        }).orElse("404Error");
    }

    @GetMapping("editar/{id}")
    public String editarVista(final Model model, @PathVariable final int id) {
        return clienteService.find(id).map(cliente -> {
            model.addAttribute("cliente", cliente);
            return "cliente/editar";
        }).orElse("404Error");
    }

    @PostMapping("crear")
    public RedirectView crear(@ModelAttribute final Cliente cliente) {
        clienteService.create(cliente);
        return clientesRedirect;
    }

    @GetMapping("crear")
    public String crearVista(final Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/crear";
    }

    @PostMapping("borrar/{id}")
    public RedirectView eliminar(@PathVariable final int id) {
        clienteService.delete(id);
        return clientesRedirect;
    }

    @DeleteMapping("editar/{id}")
    public RedirectView editar(@ModelAttribute final Cliente cliente, @PathVariable final int id) {
        clienteService.update(cliente);
        return clientesRedirect;
    }


}
