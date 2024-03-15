package LombokExercise;

public class Main {
    public static void main(String[] args) {
        Person person = new Person("Lars","Petersen",42);
        System.out.println(person);

        person.setAge(58);
        System.out.println(person.getAge());

        if(person.canEqual(person)){
            System.out.println("True!");
        }

        Person person1 = Person.builder().build();
        System.out.println(person1);

    }
}
