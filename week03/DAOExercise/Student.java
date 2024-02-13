package DAOExercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="newStudents")
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name ="name")
    private String name;

    public Student(String name){
        this.name = name;
    }

    @PrePersist
    void prePersist(){
        if(this.name.isBlank() || this.name.isEmpty()){
            this.name = "John Doe";
        }
    }

    @PreUpdate
    void preUpdate(){
        if(this.name.isBlank() || this.name.isEmpty()){
            this.name = "John Doe";
        }
    }


}
