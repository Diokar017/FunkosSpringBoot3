package oscar.funkosspringboot3.Funkos.mappers;

import org.springframework.stereotype.Component;
import oscar.funkosspringboot3.Funkos.dto.FunkoDto;
import oscar.funkosspringboot3.Funkos.models.Funko;

@Component
public class FunkoMapper {
    public Funko toFunko(FunkoDto dto) {
        Funko funko = new Funko();
        funko.setNombre(dto.getNombre());
        funko.setTipo(dto.getTipo());
        funko.setPrecio(dto.getPrecio());
        return funko;
    }
}
