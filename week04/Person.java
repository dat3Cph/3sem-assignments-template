import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@NamedQueries({
        @NamedQuery(name = "Person.getTotalPaid", query = "select sum(f.amount) from Fee f where f.person.id = :value"),
        @NamedQuery(name= "Person.getAllNotesByPerson", query = "select n from Note n where n.person.id = :value"),
        @NamedQuery(name = "Person.getNotesWithNamesAndAges", query = "select new NoteWithNameAndAgeDTO(n.note, p.name, p2.age) from Note n join Person p on p.id = n.person.id join PersonDetail p2 on p.id = p2.person.id")
})

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

    //Relation 1:1:
    @OneToOne(mappedBy = "person", cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private PersonDetail personDetail;

    //Relation 1:m
    @OneToMany(mappedBy = "person",cascade = CascadeType.ALL)
    private Set<Fee> fees = new HashSet<>();

    //Relation 1:m
    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Note> notes = new HashSet<>();

    public Person(String name){
        this.name = name;
    }



    // Bi-directional update:

    public void addPersonDetail(PersonDetail personDetail){
        this.personDetail = personDetail;
        if(personDetail != null){
            personDetail.setPerson(this);
        }
    }

    // Bi-directional update:
    public void addFee(Fee fee){
        this.fees.add(fee);
        if(fee != null){
            fee.setPerson(this);
        }
    }

    // Bi-directional update:
    public void addNote(Note note){
        this.notes.add(note);
        if(note != null){
            note.setPerson(this);
        }
    }


}
