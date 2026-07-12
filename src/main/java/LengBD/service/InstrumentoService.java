
package LengBD.service;

import LengBD.domain.Instrumento;
import LengBD.repository.InstrumentoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstrumentoService {

    @Autowired
    private InstrumentoRepository instrumentoRepository;

    public void insertarInstrumento(Instrumento instrumento) {
        instrumentoRepository.insertarInstrumento(instrumento);
    }

    public void updateInstrumento(Instrumento instrumento) {
        instrumentoRepository.updateInstrumento(instrumento);
    }

    public List<Instrumento> readAllInstrumento() {
        return instrumentoRepository.readAllInstrumento();
    }

    public void eliminarInstrumento(Integer idInstrumento) {
        instrumentoRepository.eliminarInstrumento(idInstrumento);
    }

    public Instrumento buscarPorId(Integer id) {
        return readAllInstrumento().stream()
                .filter(inst -> inst.getIdInstrumento().equals(id))
                .findFirst()
                .orElse(null);
    }
}