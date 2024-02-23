import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private LocalDate date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.PERSIST)
    private Set<PersonEvent> persons = new HashSet<>();


    public Event(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }


}
