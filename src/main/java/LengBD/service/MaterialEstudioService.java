/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.service;

import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.MaterialEstudio;
import LengBD.domain.MaterialEstudioListadoDTO;
import LengBD.repository.MaterialEstudioRepository;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;


@Service
public class MaterialEstudioService {
    @Autowired
    private MaterialEstudioRepository materialEstudioRepository;

    public void insertarMaterialEstudio(MaterialEstudio materialEstudio) {
        materialEstudioRepository.insertarMaterialEstudio(materialEstudio);
    }

    public void actualizarMaterialEstudio(MaterialEstudio materialEstudio) {
        materialEstudioRepository.actualizarMaterialEstudio(materialEstudio);
    }
    
    public List<MaterialEstudioListadoDTO> readAllMaterialEstudio() {
        return materialEstudioRepository.readAllMaterialEstudio();
    }

    public void eliminarMaterialEstudio(Integer idMaterial) {
        MaterialEstudio materialEstudio = new MaterialEstudio();
        materialEstudio.setIdMaterial(idMaterial);
        materialEstudioRepository.deleteMaterialEstudio(materialEstudio);
    }
    
    public MaterialEstudioListadoDTO buscarPorId(Integer id) {
        return readAllMaterialEstudio().stream()
                .filter(mateEst -> mateEst.getIdMaterial().equals(id))
                .findFirst()
                .orElse(null);
    }
    
}
