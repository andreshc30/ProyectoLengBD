package LengBD.controller;

import LengBD.domain.Pago;
import LengBD.domain.PagoListadoDTO;
import LengBD.service.EstadoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.PagoService;
import LengBD.service.SuscripcionService;
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
@RequestMapping("/pago")
public class PagoCrudController {

    @Autowired
    private PagoService pagoService;
    
    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PagoListadoDTO> lista = pagoService.readAllPago();
        model.addAttribute("pagos", lista);
        model.addAttribute("nuevoPago", new PagoListadoDTO());
        cargarCombos(model);
        return "pago/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pago", new PagoListadoDTO());
        cargarCombos(model);
        return "pago/formulario";
    }

    @GetMapping("/editar/{idPago}")
    public String editar(@PathVariable("idPago") Integer id, Model model) {
        model.addAttribute("pago", pagoService.buscarPorId(id));
        cargarCombos(model);
        return "pago/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PagoListadoDTO dto, RedirectAttributes ra) {
        try {
            Pago pago = new Pago();
            pago.setIdPago(dto.getIdPago());
            pago.setIdMetodoPago(dto.getIdMetodoPago());
            pago.setIdFacturacion(dto.getIdFacturacion());
            pago.setIdSuscripcion(dto.getIdSuscripcion());
            pago.setIdCuota(dto.getIdCuota());
            pago.setFechaPago(dto.getFechaPago());
            pago.setMonto(dto.getMonto());
            pago.setIdEstado(dto.getIdEstado());

            if (dto.getIdPago() != null) {
                pagoService.actualizarPago(pago);
            } else {
                pagoService.insertarPago(pago);
            }
            ra.addFlashAttribute("todoOk", "Pago guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/pago/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idPago") Integer idPago, RedirectAttributes ra) {
        try {
            pagoService.eliminarPago(idPago);
            ra.addFlashAttribute("todoOk", "Pago eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/pago/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("metodosPagos", metodoPagoService.readAllMetodoPago());
        model.addAttribute("suscripciones", suscripcionService.readAllSuscripcion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}