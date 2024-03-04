package JavalinExercise.dtos;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DogDTO {
    private int id;
    private String name;
    private String breed;
    private String gender;
    private int age;
}
