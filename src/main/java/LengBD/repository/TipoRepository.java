/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package LengBD.repository;

/**
 *
 * @author peper
 */
import LengBD.domain.Tipo;
import LengBD.domain.TipoListadoDTO;
import LengBD.domain.TipoListadoDTO;
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
public class TipoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall tipoInsertCall;
    private SimpleJdbcCall tipoUpdateCall;
    private SimpleJdbcCall tipoDeleteCall;
    private SimpleJdbcCall tipoReadAllCall;

    @PostConstruct
    public void init() {
        tipoInsertCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TIPO_INSERT_SP");

        tipoUpdateCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TIPO_UPDATE_SP");

        tipoDeleteCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_TIPO_DELETE_LOGICO_SP");

        tipoReadAllCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("FIDE_PROYECTO_LENGUAJES_PCK")
                .withProcedureName("FIDE_LISTAR_TIPO_SP")
                .returningResultSet("p_cursor",
                BeanPropertyRowMapper.newInstance(TipoListadoDTO.class));;
    }

    public void insertarTipo(Tipo tipo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_NOMBRE", tipo.getNombre());
        params.put("P_DESCRIPCION", tipo.getDescripcion());
        params.put("P_ID_ESTADO", tipo.getIdEstado());
        tipoInsertCall.execute(params);
    }

    public void actualizarTipo(Tipo tipo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TIPO", tipo.getIdTipo());
        params.put("P_NOMBRE", tipo.getNombre());
        params.put("P_DESCRIPCION", tipo.getDescripcion());
        params.put("P_ID_ESTADO", tipo.getIdEstado());
        tipoUpdateCall.execute(params);
    }

    public 
        List<TipoListadoDTO> readAllTipo() {
        Map<String, Object> result = tipoReadAllCall.execute();
        return (List<TipoListadoDTO>) result.get("p_cursor");
    }

    public void deleteTipo(Tipo tipo) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_TIPO", tipo.getIdTipo());
        tipoDeleteCall.execute(params);
    }

}
