package SchoolExercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@ToString
@Getter
@Setter
@NamedQueries({
        @NamedQuery(name="Student.findAllByFirstName", query = "select s from SchoolExercise.Student s where s.firstName = :value"),
        @NamedQuery(name="Student.findAllByLastName", query = "select s from SchoolExercise.Student s where s.lastName = :value"),
        @NamedQuery(name="Student.findTotalNumberOfStudentsBySemester", query = "select count(s) from SchoolExercise.Student s where s.currentSemester.name = :value"),
        @NamedQuery(name="Student.findTotalNumberOfStudentsByTeacher", query = "select count(s) from SchoolExercise.Student s join SchoolExercise.Teacher t on s.currentSemester.id = t.id where t.id = :value")
})

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;
    private String lastName;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Semester currentSemester;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addSemester(Semester semester){
        if(semester != null){
            this.currentSemester = semester;
        }
    }
}
