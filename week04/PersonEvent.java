import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class PersonEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person person;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Event event;

    private LocalDate signupDate;
    private int eventFee;

    public PersonEvent(Person person, Event event, LocalDate signupDate, int eventFee) {
        this.person = person;
        this.event = event;
        this.signupDate = signupDate;
        this.eventFee = eventFee;
    }
}
