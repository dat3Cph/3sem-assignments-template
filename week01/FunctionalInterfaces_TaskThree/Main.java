package FunctionalInterfaces_TaskThree;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

         ///////////////////////////////////////  1. PREDICATE  ///////////////////////////////////////////////

        //Initialization of Predicate lambda, returns a boolean:
        Predicate<Integer> divisible = (a) -> Math.floorMod(a,7) == 0;

        //Initializing the arraylist of numbers and adding numbers to it:
        List<Integer> nums = new ArrayList<>();
        nums.add(7);
        nums.add(14);
        nums.add(50);
        nums.add(80);
        nums.add(21);
        nums.add(77);
        nums.add(120);

        //Method call of filter:
        List<Integer> result = filter(nums,divisible);
        result.forEach(System.out::println);


        ////////////////////////////////////////  2. SUPPLIER  ///////////////////////////////////////////////

        Random random = new Random();

        String[] names = new String[] {
                "John",
                "Jane",
                "Jack",
                "Joe",
                "Jill"
        };

        Supplier<Employee> employeeSupplier = () -> new Employee(names[random.nextInt(5)], random.nextInt(90));

        List<Employee> employees = employ(10,employeeSupplier);
        employees.forEach(System.out::println);


        ////////////////////////////////////////  3. CONSUMER  ///////////////////////////////////////////////

        Consumer<Employee> employeeConsumer = (n) -> System.out.println(n);
        print(employees,employeeConsumer);



        ////////////////////////////////////////  4. FUNCTION  ///////////////////////////////////////////////

        Function<Employee, String> employeeToString = (a) -> a.getName();
        List<String> employeeNames = employeeToStringMethod(employees,employeeToString);
        employeeNames.forEach(System.out::println);


        ////////////////////////////////////////  5. PREDICATE  ///////////////////////////////////////////////
        Predicate<Employee> IsOverEighteen = (a) -> a.getAge() > 18;
        List<Employee> adultEmployees = filterAge(employees,IsOverEighteen);
        for (Employee e: adultEmployees) {
            System.out.println(e.getName()+ " "+e.getAge());
        }

    }


    //Method that takes a list of integers & a predicate type Integer and returns a new list of integers filled with data filtered from the input list following the
    //predicates rules.
    public static List<Integer> filter(List<Integer> list, Predicate<Integer> predicate){
        List<Integer> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(predicate.test(list.get(i))){
                result.add(list.get(i));
            }
        }
        return result;
    }

    //Method that takes a list of employees & and a predicate type employee and returns a new list of employees filled with data filtered from the input list following
    // the predicates rules.
    public static List<Employee> filterAge(List<Employee> list, Predicate<Employee> predicate){
        List<Employee> result = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            if(predicate.test(list.get(i))){
                result.add(list.get(i));
            }
        }
        return result;
    }

    //Method that takes an integer & a supplier type employee and returns a list of employees with random names and ages using the employer.
    public static List<Employee> employ(int amount, Supplier<Employee> employer){
        List<Employee> result = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            result.add(employer.get());
        }
        return result;
    }

    //Method that takes a list of employees & a consumer type employee, that prints the objects address on the memory.
    public static void print(List<Employee> list, Consumer<Employee> consumer){
        for (Employee e: list) {
            consumer.accept(e);
        }
    }

    //Method that takes a list of employees & a function type employee & String and returns a List of Strings consisting of employee names.
    public static List<String> employeeToStringMethod(List<Employee> list, Function<Employee, String> function){
        List<String> result = new ArrayList<>();
        for (Employee e: list) {
            result.add(function.apply(e));
        }
        return result;
    }


}
