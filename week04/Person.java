import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    //Relation one to many:
    @OneToOne
    private PersonDetail personDetail;


    public Person(String name){
        this.name = name;
    }

    // Bi-directional update:

    private void addPersonDetail(PersonDetail personDetail){
        this.personDetail = personDetail;
        if(personDetail != null){
            personDetail.setPerson(this);
        }
    }

}
