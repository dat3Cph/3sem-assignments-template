package GLSExercise;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NamedQueries({
    @NamedQuery(name="Package.getByTrackingNr",query = "select p from Package p where p.trackingNumber = :value"),
    @NamedQuery(name="Package.updateDeliveryStatus", query = "update Package p set p.deliveryStatus = :value where p.id = :value2"),
    @NamedQuery(name="Package.deleteById", query = "delete Package p where p.id = :value")
})
@Table(name="packages")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;

    @Column(name ="tracking_number")
    private String trackingNumber;

    @Column(name ="sender")
    private String sender;

    @Column(name ="receiver")
    private String receiver;

    @Column(name ="delivery_status")
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @Temporal(TemporalType.DATE)
    private LocalDate createdAt;

    @Temporal(TemporalType.DATE)
    private LocalDate updatedAt;


    public Package(String trackingNumber, String sender, String receiver, DeliveryStatus deliveryStatus) {
        this.trackingNumber = trackingNumber;
        this.sender = sender;
        this.receiver = receiver;
        this.deliveryStatus = deliveryStatus;
    }

    @PrePersist
    private void setInitialDates(){
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    private void updateDates(){
        this.updatedAt = LocalDate.now();
    }

}

