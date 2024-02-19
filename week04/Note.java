import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String note;
    private LocalDate created;
    private String createdBy;

    @ManyToOne
    private Person person;

    public Note(String note, LocalDate created, String createdBy) {
        this.note = note;
        this.created = created;
        this.createdBy = createdBy;
    }



}
