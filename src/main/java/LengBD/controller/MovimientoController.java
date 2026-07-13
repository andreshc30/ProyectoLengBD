package LengBD.controller;

import LengBD.domain.Movimiento;
import LengBD.domain.MovimientoListadoDTO;
import LengBD.service.MovimientoService;
import LengBD.service.CategoriaMovimientoService;
import LengBD.service.MetodoPagoService;
import LengBD.service.BandaService;
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
@RequestMapping("/movimiento")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @Autowired
    private CategoriaMovimientoService categoriaMovimientoService;

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private BandaService bandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<MovimientoListadoDTO> lista = movimientoService.readAllMovimiento();
        model.addAttribute("movimientos", lista);
        return "movimiento/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("movimiento", new MovimientoListadoDTO());
        cargarCombos(model);
        return "movimiento/formulario";
    }

    @GetMapping("/editar/{idMovimiento}")
    public String editar(@PathVariable("idMovimiento") Integer id, Model model) {
        model.addAttribute("movimiento", movimientoService.buscarPorId(id));
        cargarCombos(model);
        return "movimiento/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MovimientoListadoDTO dto, RedirectAttributes ra) {
        try {
            Movimiento movimiento = new Movimiento();
            movimiento.setIdMovimiento(dto.getIdMovimiento());
            movimiento.setCantidad(dto.getCantidad());
            movimiento.setFecha(dto.getFecha());
            movimiento.setDetalle(dto.getDetalle());
            movimiento.setIdCategoriaMovimiento(dto.getIdCategoriaMovimiento());
            movimiento.setIdMetodoPago(dto.getIdMetodoPago());
            movimiento.setIdBanda(dto.getIdBanda());
            movimiento.setIdEstado(dto.getIdEstado());

            if (dto.getIdMovimiento() != null) {
                movimientoService.actualizarMovimiento(movimiento);
            } else {
                movimientoService.insertarMovimiento(movimiento);
            }
            ra.addFlashAttribute("todoOk", "Movimiento guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/movimiento/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idMovimiento") Integer idMovimiento, RedirectAttributes ra) {
        try {
            movimientoService.eliminarMovimiento(idMovimiento);
            ra.addFlashAttribute("todoOk", "Movimiento eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/movimiento/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("categoriasMovimiento", categoriaMovimientoService.readAllCategoriaMovimiento());
        model.addAttribute("metodosPago", metodoPagoService.readAllMetodoPago());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}