package org.iesbelen.controlador;

import java.util.function.Function;

import org.iesbelen.dto.PedidoDto;
import org.iesbelen.mappers.ComercialMapper;
import org.iesbelen.mappers.PedidoMapper;
import org.iesbelen.modelo.Comercial;
import org.iesbelen.modelo.Pedido;
import org.iesbelen.service.ClienteService;
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

import lombok.val;

@Controller
@RequestMapping("comercial")
public class ComercialController {

    @Autowired
    private ComercialService comercialService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ComercialMapper comercialMapper;

    @Autowired
    private PedidoMapper pedidoMapper;

    private final RedirectView comericalRedirect = new RedirectView("/comercial");

    @GetMapping
    public String getAll(Model model) {
        val comerciales = comercialService.getAll();
        model.addAttribute("comerciales", comerciales);
        return "comercial/comerciales";
    }

    @GetMapping("{id}")
    public String getComerial(Model model, @PathVariable int id) {

        final Function<Pedido, PedidoDto> mapToPedidoDto = (pedido) -> {
            val cliente = clienteService.find(pedido.getIdCliente());
            return pedidoMapper.toDto(pedido, cliente.get().getNombre());
        };

        return comercialService.find(id).map(comercial -> {
            val pedidos = comercialService.findAllPedidos(id).stream().map(mapToPedidoDto).toList();
            val totalPedidos = pedidos.size();
            val mediaTotalPedidos = pedidos.stream().mapToDouble(PedidoDto::total).average().orElse(0d);
            val comercialDto = comercialMapper.ToDto(comercial, pedidos, totalPedidos, Math.round(mediaTotalPedidos));
            val totalPedidoMin = pedidos.stream().mapToDouble(PedidoDto::total).min().orElse(0d);
            val totalPedidoMax = pedidos.stream().mapToDouble(PedidoDto::total).max().orElse(0d);

            model.addAttribute("comercial", comercialDto);
            model.addAttribute("max", totalPedidoMax);
            model.addAttribute("min", totalPedidoMin);
            return "comercial/detalles";
        }).orElse("404Error");
    }

    @PostMapping("crear")
    public RedirectView crear(@ModelAttribute Comercial comercial) {
        comercialService.create(comercial);
        return comericalRedirect;
    }

    @GetMapping("crear")
    public String crearVista(Model model) {
        model.addAttribute("comercial", new Comercial());
        return "comercial/crear";
    }

    @PostMapping("borrar/{id}")
    public RedirectView eliminar(@PathVariable int id) {
        comercialService.remove(id);
        return comericalRedirect;
    }

    @PostMapping("editar/{id}")
    public RedirectView editar(@ModelAttribute Comercial comercial) {
        comercialService.update(comercial);
        return comericalRedirect;
    }

    @GetMapping("editar/{id}")
    public String editarVista(Model model, @PathVariable int id) {
        return comercialService.find(id).map(comercial -> {
            model.addAttribute(comercial);
            return "comercial/editar";
        }).orElse("404Error");
    }

}
