package LengBD.controller;

import LengBD.domain.Facturacion;
import LengBD.domain.FacturacionListadoDTO;
import LengBD.domain.Suscripcion;
import LengBD.domain.SuscripcionListadoDTO;
import LengBD.domain.UsuarioLoginDTO;
import LengBD.repository.UsuarioRepository;
import LengBD.service.BandaService;
import LengBD.service.SuscripcionService;
import LengBD.service.EstadoService;
import LengBD.service.FacturacionService;
import LengBD.service.MetodoPagoService;
import LengBD.service.SuscripcionService;
import LengBD.service.PlanesService;
import LengBD.service.SuscripcionService;
import LengBD.service.UsuarioService;
import java.time.LocalDate;
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
import org.springframework.security.core.Authentication;

@Controller
@RequestMapping("/suscripcion")
public class SuscripcionCrudController {

    @Autowired
    private EstadoService estadoService;
    
    
    
    @Autowired
    private FacturacionService facturacionService;
    
    
    @Autowired
    private SuscripcionService suscripcionService;
    
    @Autowired
    private BandaService bandaService;
    
    @Autowired
    private PlanesService planesService;
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<SuscripcionListadoDTO> lista = suscripcionService.readAllSuscripcion();
        model.addAttribute("suscripciones", lista);
        model.addAttribute("nuevaSuscripcion", new SuscripcionListadoDTO());
        cargarCombos(model);
        return "suscripcion/listado";
    }
 
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("suscripcion", new SuscripcionListadoDTO());
        cargarCombos(model);
        return "suscripcion/formulario";
    }

    @GetMapping("/editar/{idSuscripcion}")
    public String editar(@PathVariable("idSuscripcion") Integer id, Model model) {
        model.addAttribute("suscripcion", suscripcionService.buscarPorId(id));
        cargarCombos(model);
        return "suscripcion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute SuscripcionListadoDTO dto, RedirectAttributes ra) {
        try {
            Suscripcion suscripcion = new Suscripcion();
            suscripcion.setIdSuscripcion(dto.getIdSuscripcion());
            suscripcion.setNombre(dto.getNombre());
            suscripcion.setFechaInicio(dto.getFechaInicio());
            suscripcion.setFechaFinal(dto.getFechaFinal());
            suscripcion.setAutoRenovar(dto.getAutoRenovar());
            suscripcion.setIdTipoPlan(dto.getIdTipoPlan());
            suscripcion.setIdBanda(dto.getIdBanda());
            suscripcion.setIdEstado(dto.getIdEstado());

            if (dto.getIdSuscripcion()!= null) {
                suscripcionService.actualizarSuscripcion(suscripcion);
            } else {
                suscripcionService.insertarSuscripcion(suscripcion);
            }
            ra.addFlashAttribute("todoOk", "Suscripcion guardada correctamente");
        } catch (Exception ex) {
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/suscripcion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idSuscripcion") Integer idSuscripcion, RedirectAttributes ra) {
        try {
            suscripcionService.eliminarSuscripcion(idSuscripcion);
            ra.addFlashAttribute("todoOk", "Suscripcion eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/suscripcion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("planes", planesService.readAllPlanes());
        model.addAttribute("bandas", bandaService.readAllBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
    
    
    @GetMapping("/formulario")
    public String pasarelaPago(@RequestParam(value = "plan", required = false, defaultValue = "Básico") String plan, Model model) {
        
        System.out.println("Cargando pasarela de pago para el plan: " + plan);
        model.addAttribute("plan", plan);
        
        return "suscripcion/pasarela"; 
    }
    
    
    @PostMapping("/pagar")
    public String pagar(@RequestParam("plan") String plan,
                        @RequestParam("metodo") String metodo,
                        Authentication auth,
                        RedirectAttributes ra) {
        try {
            UsuarioLoginDTO usuario = usuarioRepository.buscarPorCorreo(auth.getName());
            Integer idBanda = usuario.getIdBanda() != null ? usuario.getIdBanda() : 1;
            Integer idPlan = planesService.buscarIdPorNombre(plan);
            Integer idMetodo = "SINPE".equalsIgnoreCase(metodo) ? 4 : 2;

            double subtotal = 10000.00;
            double impuestos = subtotal * 0.13;
            double total = subtotal + impuestos;

            Long idFactura = planesService.procesarPago(idBanda, idPlan, idMetodo,
                    subtotal, impuestos, total, "Plan " + plan);

            System.out.println(">>> idFactura antes de redirect: " + idFactura);

            ra.addFlashAttribute("idFactura", idFactura);
            ra.addFlashAttribute("todoOk", "¡Pago exitoso! Factura generada.");
            return "redirect:/suscripcion/verFactura";

        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al procesar el pago: " + ex.getMessage());
            return "redirect:/suscripcion/listado";
        }
    }
     @GetMapping("/verFactura")
    public String verFactura(Model model) {
        System.out.println("===== ENTRANDO A VER FACTURA =====");
        System.out.println(">>> tiene idFactura? " + model.containsAttribute("idFactura"));
        System.out.println(">>> tiene facturaDirecta? " + model.containsAttribute("facturaDirecta"));
        System.out.println(">>> keys en model: " + model.asMap().keySet());

        if (model.containsAttribute("facturaDirecta")) {
            model.addAttribute("factura", model.getAttribute("facturaDirecta"));
            return "suscripcion/verFactura";
        }

        if (model.containsAttribute("idFactura")) {
            Long idFactura = (Long) model.getAttribute("idFactura");
            if (idFactura != null) {
                FacturacionListadoDTO factura = facturacionService.buscarPorId(idFactura);
                if (factura != null) {
                    model.addAttribute("factura", factura);
                    return "suscripcion/verFactura";
                }
            }
        }

        System.out.println("REDIRECCIÓN: No se pudo cargar la factura.");
        return "redirect:/planes/listado";
    }
}
