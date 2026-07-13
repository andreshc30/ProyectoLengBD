package LengBD.controller;

import LengBD.domain.MetodoPago;
import LengBD.domain.MetodoPagoListadoDTO;
import LengBD.service.MetodoPagoService;
import LengBD.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/metodoPago")
public class MetodoPagoController {

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<MetodoPagoListadoDTO> lista = metodoPagoService.readAllMetodoPago();
        model.addAttribute("metodosPago", lista);
        return "metodoPago/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("metodoPago", new MetodoPagoListadoDTO());
        cargarCombos(model);
        return "metodoPago/formulario";
    }

    @GetMapping("/editar/{idMetodoPago}")
    public String editar(@PathVariable("idMetodoPago") Integer id, Model model) {
        model.addAttribute("metodoPago", metodoPagoService.buscarPorId(id));
        cargarCombos(model);
        return "metodoPago/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MetodoPagoListadoDTO dto, RedirectAttributes ra) {
        try {
            MetodoPago metodoPago = new MetodoPago();
            metodoPago.setIdMetodoPago(dto.getIdMetodoPago());
            metodoPago.setNombre(dto.getNombre());
            metodoPago.setIdEstado(dto.getIdEstado());

            if (dto.getIdMetodoPago() != null) {
                metodoPagoService.actualizarMetodoPago(metodoPago);
            } else {
                metodoPagoService.insertarMetodoPago(metodoPago);
            }
            ra.addFlashAttribute("todoOk", "Método de pago guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/metodoPago/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idMetodoPago") Integer idMetodoPago, RedirectAttributes ra) {
        try {
            metodoPagoService.eliminarMetodoPago(idMetodoPago);
            ra.addFlashAttribute("todoOk", "Método de pago eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/metodoPago/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}