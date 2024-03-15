package TimeAPI_TaskFour;

import java.time.LocalDate;

public class Employee {

    private String name;
    private int age;
    private LocalDate birthdate;
    public Employee(String name) {
        this.name = name;
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name, LocalDate birthdate){
        this.name = name;
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public LocalDate getBirthdate(){return birthdate;}

    public void setBirthdate(LocalDate birthdate){this.birthdate = birthdate;}

    public void setAge(int age){this.age = age;}

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthdate=" + birthdate +
                '}';
    }
}
