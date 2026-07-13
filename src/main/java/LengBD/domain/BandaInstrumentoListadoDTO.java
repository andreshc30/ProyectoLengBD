/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.domain;

import java.io.Serializable;
import lombok.Data;

@Data
public class BandaInstrumentoListadoDTO implements Serializable {
    private static final long serialVersionUID = 1l;
    
    private Integer idBanda;
    private String nombreBanda;
    
    private String nombreInstrumento;
    private Integer idInstrumento;
    
    private String estado; 
    private Integer idEstado; 
    

}