/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.AsignacionListadoDTO;
import LengBD.domain.MaterialEstudio;
import LengBD.domain.MaterialEstudioListadoDTO;
import LengBD.domain.MaterialEstudioListadoDTO;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

@Repository
public class MaterialEstudioRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall materialEstudioInsertCall;
    private SimpleJdbcCall materialEstudioUpdateCall;
    private SimpleJdbcCall materialEstudioDeleteCall;
    private SimpleJdbcCall materialEstudioReadAllCall;

    @PostConstruct
    public void init() {
        materialEstudioInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MATERIAL_ESTUDIO_INSERT_SP");

        materialEstudioUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MATERIAL_ESTUDIO_UPDATE_SP");

        materialEstudioDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_MATERIAL_ESTUDIO_DELETE_LOGICO_SP");

        materialEstudioReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_MATERIAL_ESTUDIO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(MaterialEstudioListadoDTO.class));;
    }

    public void insertarMaterialEstudio(MaterialEstudio materialEstudio) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", materialEstudio.getNombre());
        params.put("P_FECHA", materialEstudio.getFecha());
        params.put("P_RUTA_MATERIAL_ESTUDIO", materialEstudio.getRutaMaterialEstudio());
        params.put("P_ID_SECCION", materialEstudio.getIdSeccion());
        params.put("P_ID_ESTADO", materialEstudio.getIdEstado());
        materialEstudioInsertCall.execute(params);
    }

    public void actualizarMaterialEstudio(MaterialEstudio materialEstudio) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MATERIAL", materialEstudio.getIdMaterial());
        params.put("P_NOMBRE", materialEstudio.getNombre());
        params.put("P_FECHA", materialEstudio.getFecha());
        params.put("P_RUTA_MATERIAL_ESTUDIO", materialEstudio.getRutaMaterialEstudio());
        params.put("P_ID_SECCION", materialEstudio.getIdSeccion());
        params.put("P_ID_ESTADO", materialEstudio.getIdEstado());
        materialEstudioUpdateCall.execute(params);
    }

    public List<MaterialEstudioListadoDTO> readAllMaterialEstudio() {
        Map<String, Object> result = materialEstudioReadAllCall.execute();
        return (List<MaterialEstudioListadoDTO>) result.get("p_cursor");
    }

    public void deleteMaterialEstudio(MaterialEstudio materialEstudio) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_MATERIAL", materialEstudio.getIdMaterial());
        materialEstudioDeleteCall.execute(params);
    }

}
