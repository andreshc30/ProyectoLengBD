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
import LengBD.domain.Lugar;
import LengBD.domain.LugarListadoDTO;
import LengBD.domain.LugarListadoDTO;
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
public class LugarRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall lugarInsertCall;
    private SimpleJdbcCall lugarUpdateCall;
    private SimpleJdbcCall lugarDeleteCall;
    private SimpleJdbcCall lugarReadAllCall;

    @PostConstruct
    public void init() {
        lugarInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LUGAR_INSERT_SP");

        lugarUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LUGAR_UPDATE_SP");

        lugarDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LUGAR_DELETE_SP");

        lugarReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_LUGAR_SP")
                .returningResultSet("p_cursor",
                        BeanPropertyRowMapper.newInstance(LugarListadoDTO.class));;
    }

    public void insertarLugar(Lugar lugar) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", lugar.getNombre());
        params.put("P_ID_DIRECCION", lugar.getIdDireccion());
        params.put("P_ID_ESTADO", lugar.getIdEstado());
        lugarUpdateCall.execute(params);
        lugarInsertCall.execute(params);
    }

    public void actualizarLugar(Lugar lugar) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_LUGAR", lugar.getIdLugar());
        params.put("P_NOMBRE", lugar.getNombre());
        params.put("P_ID_DIRECCION", lugar.getIdDireccion());
        params.put("P_ID_ESTADO", lugar.getIdEstado());
        lugarUpdateCall.execute(params);
    }

    public List<LugarListadoDTO> readAllLugar() {
        Map<String, Object> result = lugarReadAllCall.execute();
        return (List<LugarListadoDTO>) result.get("p_cursor");
    }

    public void deleteLugar(Lugar lugar) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_LUGAR", lugar.getIdLugar());
        lugarDeleteCall.execute(params);
    }

}
