package FunctionalInterfaces_TaskThree;

public class Employee {
    private String name;
    private int age;
    public Employee(String name){
        this.name = name;
    }

    public Employee(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge(){return age;}
}
