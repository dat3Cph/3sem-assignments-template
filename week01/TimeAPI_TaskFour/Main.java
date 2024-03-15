package TimeAPI_TaskFour;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {

        //////////////////////////////////////// 1. Calculate age of employees ////////////////////////////////////////

        Random random = new Random();

        String[] names = {
                "John",
                "Jane",
                "Jack",
                "Joe",
                "Jill"
        };

        LocalDate[] dates = {
                LocalDate.of(1998,8,10),
                LocalDate.of(1978,4,11),
                LocalDate.of(1946,5,4),
                LocalDate.of(2002,8,2),
                LocalDate.of(2003,2,8),
                LocalDate.of(2003,1,2),
                LocalDate.of(1999,1,28),
                LocalDate.of(1996,2,10),
                LocalDate.of(1997,2,15),
                LocalDate.of(1995,1,31),
                LocalDate.of(1994,1,22),
        };

        Supplier<Employee> employeeSupplier = () -> new Employee(names[random.nextInt(5)],dates[random.nextInt(10)]);
        List<Employee> employees = employ(10,employeeSupplier);
        List<Employee> employeesWithAges = calculateAge(employees);
        employeesWithAges.forEach(System.out::println);

        //////////////////////////////////////// 2. Calculate average age of employees ////////////////////////////////////////

        int employeeAverageAge = averageAges(employeesWithAges);
        System.out.println(employeeAverageAge);


        //////////////////////////////////////// 3. Filter and display employees by birthdays in a specific month ////////////////////////////////////////

        Predicate<Employee> filterByAugust = (a) -> a.getBirthdate().getMonth().equals(Month.AUGUST);
        List<Employee> bornInAugust = birthdayInAugust(employeesWithAges,filterByAugust);
        bornInAugust.forEach(System.out::println);


        //////////////////////////////////////// 4. Group employees by birth month and display the count of employees in each group ////////////////////////////////////////



        System.out.println("------------------------------------------------------------------------------");

        Map<Month, List<Employee>> employeesBirthByMonths = groupedByBirthdaysMap(employeesWithAges);

        for (Map.Entry<Month,List<Employee>> entry: employeesBirthByMonths.entrySet()) {
            System.out.println("Month: "+entry.getKey()+ ": Amount: "+ entry.getValue().size());
            for (Employee e: entry.getValue()) {
                System.out.println(e);
            }

        }


        System.out.println("------------------------------------------------------------------------------");

        //////////////////////////////////////// 5. List all employees who have a birthday in the current month ////////////////////////////////////////

        Predicate<Employee> filterByCurrentMonth = (a) -> a.getBirthdate().getMonth().equals(LocalDate.now().getMonth());
        List<Employee> employeesWithBirthdayThisMonth = birthdayInCurrentMonth(employeesWithAges,filterByCurrentMonth);

        System.out.println("How many people that have their birthday in the current month:");
        employeesWithBirthdayThisMonth.forEach(System.out::println);

    }


    public static List<Employee> employ(int amount, Supplier<Employee> employer){
        List<Employee> result = new ArrayList<>();
        for(int i = 0; i < amount; i++){
            result.add(employer.get());
        }
        return result;
    }

    public static List<Employee> calculateAge(List<Employee> list){
        LocalDate now = LocalDate.now();
        List<Employee> result = new ArrayList<>();
        for (Employee e: list) {
            Period period = Period.between(e.getBirthdate(),now);
            e.setAge(period.getYears());
            result.add(e);
        }
        return result;
    }

    public static int averageAges(List<Employee> list){
        int totalAge = 0;
        for (Employee e: list) {
            totalAge += e.getAge();
        }
        return Math.floorDiv(totalAge,list.size());
    }

    public static List<Employee> birthdayInAugust(List<Employee> list, Predicate<Employee> predicate){
        List<Employee> result = new ArrayList<>();
        for(Employee e: list){
            if(predicate.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    public static Map<Month,List<Employee>> groupedByBirthdaysMap(List<Employee> list){
        Map<Month, List<Employee>> result = new HashMap<>();
        for (int i = 1; i < 13; i++){
            List<Employee> thisMonth = new ArrayList<>();
            for (Employee e: list) {
                if(e.getBirthdate().getMonth().getValue() == i){
                    thisMonth.add(e);
                }
            }
            result.put(Month.of(i),thisMonth);
        }
        return result;
    }

    public static List<Employee> birthdayInCurrentMonth(List<Employee> list, Predicate<Employee> predicate){
        List<Employee> result = new ArrayList<>();
        for (Employee e: list) {
            if(predicate.test(e)){
                result.add(e);
            }
        }
        return result;
    }

}
