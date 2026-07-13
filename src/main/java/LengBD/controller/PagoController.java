package LengBD.controller;

import LengBD.domain.Pago;
import LengBD.domain.PagoListadoDTO;
import LengBD.service.PagoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.FacturacionService;
import LengBD.service.SuscripcionService;
import LengBD.service.CuotaService;
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
@RequestMapping("/pago")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private FacturacionService facturacionService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private CuotaService cuotaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PagoListadoDTO> lista = pagoService.readAllPago();
        model.addAttribute("pagos", lista);
        return "pagos/prueba";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pago", new PagoListadoDTO());
        cargarCombos(model);
        return "pagos/formularioPagos";
    }

    @GetMapping("/editar/{idPago}")
    public String editar(@PathVariable("idPago") Integer id, Model model) {
        model.addAttribute("pago", pagoService.buscarPorId(id));
        cargarCombos(model);
        return "pagos/formularioPagos";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PagoListadoDTO dto, RedirectAttributes ra) {
        try {
            Pago pago = new Pago();
            pago.setIdPago(dto.getIdPago());
            pago.setMonto(dto.getMonto());
            pago.setFechaPago(dto.getFechaPago());
            pago.setIdMetodoPago(dto.getIdMetodoPago());
            pago.setIdFacturacion(dto.getIdFacturacion());
            pago.setIdSuscripcion(dto.getIdSuscripcion());
            pago.setIdCuota(dto.getIdCuota());
            pago.setIdEstado(dto.getIdEstado());

            if (dto.getIdPago() != null) {
                pagoService.actualizarPago(pago);
            } else {
                pagoService.insertarPago(pago);
            }
            ra.addFlashAttribute("todoOk", "Pago guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "pagos/formulario";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idPago") Integer idPago, RedirectAttributes ra) {
        try {
            pagoService.eliminarPago(idPago);
            ra.addFlashAttribute("todoOk", "Pago eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/pagos/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("metodosPago", metodoPagoService.readAllMetodoPago());
        model.addAttribute("facturaciones", facturacionService.readAllFacturacion());
        model.addAttribute("suscripciones", suscripcionService.readAllSuscripcion());
        model.addAttribute("cuotas", cuotaService.readAllCuota());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}