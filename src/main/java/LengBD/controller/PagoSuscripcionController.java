package LengBD.controller;

import LengBD.domain.PagoSuscripcion;
import LengBD.domain.PagoSuscripcionListadoDTO;
import LengBD.service.PagoSuscripcionService;
import LengBD.service.MetodoPagoService;
import LengBD.service.PlanesService;
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
@RequestMapping("/pagoSuscripcion")
public class PagoSuscripcionController {

    @Autowired
    private PagoSuscripcionService pagoSuscripcionService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private PlanesService planesService;

    @Autowired
    private SuscripcionService suscripcionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PagoSuscripcionListadoDTO> lista = pagoSuscripcionService.readAllPagoSuscripcion();
        model.addAttribute("pagosSuscripcion", lista);
        return "pagoSuscripcion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("pagoSuscripcion", new PagoSuscripcionListadoDTO());
        cargarCombos(model);
        return "pagoSuscripcion/formulario";
    }

    @GetMapping("/editar/{idPagoSuscripcion}")
    public String editar(@PathVariable("idPagoSuscripcion") Integer id, Model model) {
        model.addAttribute("pagoSuscripcion", pagoSuscripcionService.buscarPorId(id));
        cargarCombos(model);
        return "pagoSuscripcion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PagoSuscripcionListadoDTO dto, RedirectAttributes ra) {
        try {
            PagoSuscripcion pagoSuscripcion = new PagoSuscripcion();
            pagoSuscripcion.setIdPagoSuscripcion(dto.getIdPagoSuscripcion());
            pagoSuscripcion.setNombre(dto.getNombre());
            pagoSuscripcion.setDescripcion(dto.getDescripcion());
            pagoSuscripcion.setMonto(dto.getMonto());
            pagoSuscripcion.setFechaPago(dto.getFechaPago());
            pagoSuscripcion.setIdMetodoPago(dto.getIdMetodoPago());
            pagoSuscripcion.setIdTipoPlan(dto.getIdTipoPlan());
            pagoSuscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            pagoSuscripcion.setIdEstado(dto.getIdEstado());

            if (dto.getIdPagoSuscripcion() != null) {
                pagoSuscripcionService.actualizarPagoSuscripcion(pagoSuscripcion);
            } else {
                pagoSuscripcionService.insertarPagoSuscripcion(pagoSuscripcion);
            }
            ra.addFlashAttribute("todoOk", "Pago de suscripción guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/pagoSuscripcion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idPagoSuscripcion") Integer idPagoSuscripcion, RedirectAttributes ra) {
        try {
            pagoSuscripcionService.eliminarPagoSuscripcion(idPagoSuscripcion);
            ra.addFlashAttribute("todoOk", "Pago de suscripción eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/pagoSuscripcion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("metodosPago", metodoPagoService.readAllMetodoPago());
        model.addAttribute("planes", planesService.readAllPlanes());
        model.addAttribute("suscripciones", suscripcionService.readAllSuscripcion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}