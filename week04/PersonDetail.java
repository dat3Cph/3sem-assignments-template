import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonDetail {
    @Id
    private int id;
    private String address;
    private int zip;
    private String city;
    private int age;


    // Relation 1:1
    @OneToOne
    @MapsId
    private Person person;

}
