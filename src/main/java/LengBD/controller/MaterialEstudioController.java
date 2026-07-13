package LengBD.controller;

import LengBD.domain.MaterialEstudio;
import LengBD.domain.MaterialEstudioListadoDTO;
import LengBD.service.MaterialEstudioService;
import LengBD.service.SeccionService;
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
@RequestMapping("/materialEstudio")
public class MaterialEstudioController {

    @Autowired
    private MaterialEstudioService materialEstudioService;

    @Autowired
    private SeccionService seccionService;

    @Autowired
    private EstadoService estadoService;

    @GetMapping("/listado")
    public String listado(Model model) {
        List<MaterialEstudioListadoDTO> lista = materialEstudioService.readAllMaterialEstudio();
        model.addAttribute("materialesEstudio", lista);
        return "materialEstudio/listado";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("materialEstudio", new MaterialEstudioListadoDTO());
        cargarCombos(model);
        return "materialEstudio/formulario";
    }

    @GetMapping("/editar/{idMaterial}")
    public String editar(@PathVariable("idMaterial") Integer id, Model model) {
        model.addAttribute("materialEstudio", materialEstudioService.buscarPorId(id));
        cargarCombos(model);
        return "materialEstudio/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute MaterialEstudioListadoDTO dto, RedirectAttributes ra) {
        try {
            MaterialEstudio materialEstudio = new MaterialEstudio();
            materialEstudio.setIdMaterial(dto.getIdMaterial());
            materialEstudio.setNombre(dto.getNombre());
            materialEstudio.setFecha(dto.getFecha());
            materialEstudio.setRutaMaterialEstudio(dto.getRutaMaterialEstudio());
            materialEstudio.setIdSeccion(dto.getIdSeccion());
            materialEstudio.setIdEstado(dto.getIdEstado());

            if (dto.getIdMaterial() != null) {
                materialEstudioService.actualizarMaterialEstudio(materialEstudio);
            } else {
                materialEstudioService.insertarMaterialEstudio(materialEstudio);
            }
            ra.addFlashAttribute("todoOk", "Material de estudio guardado correctamente");
        } catch (Exception ex) {
            ex.printStackTrace();
            ra.addFlashAttribute("error", "Error al guardar: " + ex.getMessage());
        }
        return "redirect:/materialEstudio/listado";
    }

    @PostMapping("/eliminar")
    public String eliminar(@RequestParam("idMaterial") Integer idMaterial, RedirectAttributes ra) {
        try {
            materialEstudioService.eliminarMaterialEstudio(idMaterial);
            ra.addFlashAttribute("todoOk", "Material de estudio eliminado correctamente");
        } catch (Exception e) {
            ra.addFlashAttribute("error", "Error al eliminar");
        }
        return "redirect:/materialEstudio/listado";
    }

    private void cargarCombos(Model model) {
        model.addAttribute("secciones", seccionService.readAllSeccion());
        model.addAttribute("estados", estadoService.readAllEstado());
    }
}