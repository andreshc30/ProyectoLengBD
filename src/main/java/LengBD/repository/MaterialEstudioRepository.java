/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LengBD.repository;

import LengBD.domain.AsistenciaPresentacion;
import LengBD.domain.Canton;
import LengBD.domain.Caracteristicas;
import LengBD.domain.Facturacion;
import LengBD.domain.Instrumento;
import LengBD.domain.MaterialEstudio;
import LengBD.domain.MetodoPago;
import LengBD.domain.PagoSuscripcion;
import LengBD.domain.Suscripcion;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialEstudioRepository extends JpaRepository<MaterialEstudio, Integer>{
        
    @Procedure(procedureName = "FIDE_MATERIAL_ESTUDIO_INSERT_SP")
    void insertarMaterialEstudio(
        @Param("P_ID_MATERIAL_ESTUDIO") Integer idMaterialEstudio,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA") Date fecha,
        @Param("P_RUTA_MATERIAL_ESTUDIO") String rutaMaterialEstudio,
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_ID_ESTADO") Integer idEstado
        
    );
    
    @Procedure(procedureName = "FIDE_MATERIAL_ESTUDIO_UPDATE_SP")
    void updateMaterialEstudio(
        @Param("P_ID_MATERIAL_ESTUDIO") Integer idMaterialEstudio,
        @Param("P_NOMBRE") String nombre,
        @Param("P_FECHA") Date fecha,
        @Param("P_RUTA_MATERIAL_ESTUDIO") String rutaMaterialEstudio,
        @Param("P_ID_SECCION") Integer idSeccion,
        @Param("P_ID_ESTADO") Integer idEstado
    );
    
    @Procedure(procedureName = "FIDE_MATERIAL_ESTUDIO_DELETE_LOGICO_SP")
    void eliminarMaterialEstudio(
        @Param("P_ID_MATERIAL_ESTUDIO") Integer idMaterialEstudio
    );
    
}
