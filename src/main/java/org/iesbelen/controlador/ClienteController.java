package org.iesbelen.controlador;

import org.iesbelen.modelo.Cliente;
import org.iesbelen.service.ClienteService;
import org.iesbelen.service.ComercialService;
import org.iesbelen.service.PedidoService;
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

import lombok.val;

@Controller
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComercialService comercialService;

    @Autowired
    private PedidoService pedidoService;

    private final RedirectView clientesRedirect = new RedirectView("/clientes");

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("listaClientes", clienteService.listAll());
        return "cliente/clientes";
    }

    @GetMapping("{id}")
    public String getCliente(Model model, @PathVariable int id) {
        return clienteService.find(id).map(cliente -> {
            val comerciales = comercialService.getAllComercial(cliente.getId());

            model.addAttribute("comerciales", comerciales);
            model.addAttribute("cPedidos", cantidadDePedidos);
            return "cliente/detalles";
        }).orElse("404Error");
    }

    @GetMapping("editar/{id}")
    public String editarVista(Model model, @PathVariable int id) {
        return clienteService.find(id).map(cliente -> {
            model.addAttribute("cliente", cliente);
            return "cliente/editar";
        }).orElse("404Error");
    }

    @PostMapping("crear")
    public RedirectView crear(@ModelAttribute Cliente cliente) {
        clienteService.create(cliente);
        return clientesRedirect;
    }

    @GetMapping("crear")
    public String crearVista(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/crear";
    }

    @PostMapping("borrar/{id}")
    public RedirectView eliminar(@PathVariable int id) {
        clienteService.delete(id);
        return clientesRedirect;
    }

    @DeleteMapping("editar/{id}")
    public RedirectView editar(@ModelAttribute Cliente cliente, @PathVariable int id) {
        clienteService.update(cliente);
        return clientesRedirect;
    }

}
