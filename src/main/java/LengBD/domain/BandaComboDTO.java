package LengBD.domain;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
@Data
public class BandaComboDTO {
    private Integer idBanda;
    private String nombre;
    private String logoUrl;
    private Integer idEstado;
    private String estado;
}