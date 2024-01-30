package TimeAPI_TaskFour;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
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
        };

        Supplier<Employee> employeeSupplier = () -> new Employee(names[random.nextInt(5)],dates[random.nextInt(5)]);
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

        Employee[][] employeesByBirthdays = groupedByBirthdays(employeesWithAges);


        System.out.println("------------------------------------------------------------------------------");

        for(Employee[] x : employeesByBirthdays){
            int count = 0;
            if(x != null){
                for(Employee e: x){
                    if(e != null && e.getBirthdate() != null){
                        count++;
                        System.out.println(e + " "+ e.getBirthdate().getMonth() +" "+ count);

                    }
                }
                if(count > 0){
                    System.out.println();
                    System.out.println("Amount of employees who have birthdays in the month above: "+count);
                    System.out.println();
                }
            }

        }

        System.out.println("------------------------------------------------------------------------------");

        //////////////////////////////////////// 5. List all employees who have a birthday in the current month ////////////////////////////////////////

        Predicate<Employee> filterByCurrentMonth = (a) -> a.getBirthdate().getMonth().equals(LocalDate.now().getMonth());
        List<Employee> employeesWithBirthdayThisMonth = birthdayInCurrentMonth(employeesWithAges,filterByCurrentMonth);
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

    public static Employee[][] groupedByBirthdays(List<Employee> list){
        Employee[][] employees = new Employee[13][list.size()];
        int janCount = 0;
        int febCount = 0;
        int marchCount = 0;
        int aprilCount = 0;
        int mayCount = 0;
        int juneCount = 0;
        int julyCount = 0;
        int augCount = 0;
        int septCount = 0;
        int octCount = 0;
        int novCount = 0;
        int decCount = 0;
        for (Employee e: list) {
            switch(e.getBirthdate().getMonth()){
                case JANUARY:
                    employees[1][janCount] = e;
                    janCount++;
                    break;
                case FEBRUARY:
                    employees[2][febCount] = e;
                    febCount++;
                    break;
                case MARCH:
                    employees[3][marchCount] = e;
                    marchCount++;
                    break;
                case APRIL:
                    employees[4][aprilCount] = e;
                    aprilCount++;
                    break;
                case MAY:
                    employees[5][mayCount] = e;
                    mayCount++;
                    break;
                case JUNE:
                    employees[6][juneCount] = e;
                    juneCount++;
                    break;
                case JULY:
                    employees[7][julyCount] = e;
                    julyCount++;
                    break;
                case AUGUST:
                    employees[8][augCount] = e;
                    augCount++;
                    break;
                case SEPTEMBER:
                    employees[9][septCount] = e;
                    septCount++;
                    break;
                case OCTOBER:
                    employees[10][octCount] = e;
                    octCount++;
                    break;
                case NOVEMBER:
                    employees[11][novCount] = e;
                    novCount++;
                    break;
                case DECEMBER:
                    employees[12][decCount] = e;
                    decCount++;
                    break;
            }
        }
        return employees;
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
