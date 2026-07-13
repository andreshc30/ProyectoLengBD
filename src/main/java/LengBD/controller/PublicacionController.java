package LengBD.controller;

import LengBD.domain.Publicacion;
import LengBD.domain.PublicacionListadoDTO;
import LengBD.service.PublicacionService;
import LengBD.service.RedSocialBandaService;
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
@RequestMapping("/publicacion")
public class PublicacionController {

    @Autowired
    private PublicacionService publicacionService;

    @Autowired
    private RedSocialBandaService redSocialBandaService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<PublicacionListadoDTO> lista = publicacionService.readAllPublicacion();
        model.addAttribute("publicaciones", lista);
        return "publicacion/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("publicacion", new PublicacionListadoDTO());
        cargarCombos(model);
        return "publicacion/formulario";
    }

    @GetMapping("/editar/{idPublicacion}")
    public String editar(@PathVariable("idPublicacion") Integer id, Model model) {
        model.addAttribute("publicacion", publicacionService.buscarPorId(id));
        cargarCombos(model);
        return "publicacion/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute PublicacionListadoDTO dto, RedirectAttributes ra) {
        try {
            Publicacion publicacion = new Publicacion();
            publicacion.setIdPublicacion(dto.getIdPublicacion());
            publicacion.setFecha(dto.getFecha());
            publicacion.setTitulo(dto.getTitulo());
            publicacion.setDetalle(dto.getDetalle());
            publicacion.setIdRedSocial(dto.getIdRedSocial());
            publicacion.setIdEstado(dto.getIdEstado());

            if (dto.getIdPublicacion() != null) {
                publicacionService.actualizarPublicacion(publicacion);
            } else {
                publicacionService.insertarPublicacion(publicacion);
            }
            ra.addFlashAttribute("todoOk", "Publicación guardada correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/publicacion/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idPublicacion") Integer idPublicacion, RedirectAttributes ra) {
        try {
            publicacionService.eliminarPublicacion(idPublicacion);
            ra.addFlashAttribute("todoOk", "Publicación eliminada correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/publicacion/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("redesSociales", redSocialBandaService.readAllRedSocialBanda());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}