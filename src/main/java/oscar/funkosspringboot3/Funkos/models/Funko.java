package oscar.funkosspringboot3.Funkos.models;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class Funko {
    private UUID id;
    private String nombre;
    private String tipo;
    private double precio;
    private LocalDateTime crateAt;
    private LocalDateTime updateAt;

    public Funko() {
        this.id = UUID.randomUUID();
        this.crateAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }
}