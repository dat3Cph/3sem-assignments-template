package GLSExercisePart2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Shipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Package aPackage;

    @ManyToOne
    private Location source;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Location destination;

    @Temporal(TemporalType.DATE)
    private LocalDate shipmentDate;

    public Shipment(Package aPackage, Location source, Location destination, LocalDate shipmentDate) {
        this.aPackage = aPackage;
        this.source = source;
        this.destination = destination;
        this.shipmentDate = shipmentDate;
    }
}
