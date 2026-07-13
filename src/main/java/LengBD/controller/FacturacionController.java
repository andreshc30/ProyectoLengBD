package LengBD.controller;

import LengBD.domain.Facturacion;
import LengBD.domain.FacturacionListadoDTO;
import LengBD.service.FacturacionService;
import LengBD.service.MetodoPagoService;
import LengBD.service.CuotaService;
import LengBD.service.SuscripcionService;
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
@RequestMapping("/facturacion")
public class FacturacionController {

    @Autowired
    private FacturacionService facturacionService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private CuotaService cuotaService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<FacturacionListadoDTO> lista = facturacionService.readAllFacturacion();
        model.addAttribute("facturaciones", lista);
        return "facturacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("facturacion", new FacturacionListadoDTO());
        cargarCombos(model);
        return "facturacion/formulario";
    }

    @GetMapping("/editar/{idFactura}")
    public String editar(@PathVariable("idFactura") Integer id, Model model) {
        model.addAttribute("facturacion", facturacionService.buscarPorId(id));
        cargarCombos(model);
        return "facturacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute FacturacionListadoDTO dto, RedirectAttributes ra) {
        try {
            Facturacion facturacion = new Facturacion();
            facturacion.setIdFactura(dto.getIdFactura());
            facturacion.setFechaEmision(dto.getFechaEmision());
            facturacion.setDetalle(dto.getDetalle());
            facturacion.setSubtotal(dto.getSubtotal());
            facturacion.setImpuestos(dto.getImpuestos());
            facturacion.setTotal(dto.getTotal());
            facturacion.setIdMetodoPago(dto.getIdMetodoPago());
            facturacion.setIdCuota(dto.getIdCuota());
            facturacion.setIdSuscripcion(dto.getIdSuscripcion());
            facturacion.setIdEstado(dto.getIdEstado());

            if (dto.getIdFactura() != null) {
                facturacionService.actualizarFacturacion(facturacion);
            } else {
                facturacionService.insertarFacturacion(facturacion);
            }
            ra.addFlashAttribute("todoOk", "Facturación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/facturacion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idFactura") Integer idFactura, RedirectAttributes ra) {
        try {
            facturacionService.eliminarFacturacion(idFactura);
            ra.addFlashAttribute("todoOk", "Facturación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/facturacion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("metodosPago", metodoPagoService.readAllMetodoPago());
        model.addAttribute("cuotas", cuotaService.readAllCuota());
        model.addAttribute("suscripciones", suscripcionService.readAllSuscripcion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}