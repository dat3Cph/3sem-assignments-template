package ThePointExercise;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="point")
@NoArgsConstructor
@Getter
@Setter
public class Point {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name ="id")
    private int id;

    @Column(name="x")
    private int x;

    @Column(name="y")
    private int y;


    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

}
