package GLSExercise;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

}

enum DeliveryStatus{
    PENDING,
    IN_TRANSIT,
    DELIVERED
}