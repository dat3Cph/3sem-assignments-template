package FunctionalProgramming_TaskTwo;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        //Initialization of lambdas:
        MyTransformingType multiplyInt = (a) -> Math.multiplyExact(a,2);
        MyValidatingType isDivisibleWith7 = (a) -> a % 7 == 0;

        //Integers to use as parameters:
        int[] nums = {1,4,5,10,23,20,28,7,63};

        //The result of the map method:
        int[] mappedResult = map(nums, multiplyInt);

        //The result of the filter method:
        int[] filteredResult = filter(nums,isDivisibleWith7);

        //Printing the results to the console:
        print(mappedResult);
        print(filteredResult);
    }

    //Method that takes an array of integers & a TransformingType as parameters. Returns a new array of integers where the data has been transformed using the type:
    public static int[] map(int[] a, MyTransformingType type){
        int[] result = new int[a.length];
        for(int i = 0; i < a.length; i++){
            result[i] = type.transform(a[i]);
        }
        return result;
    };

    //Method that takes an array of integers & a ValidatingType as parameters. Returns a new array of integers where the data consists of the filtered out data
    //from the original array, based on the validatingType
    public static int[] filter(int[] a, MyValidatingType type){
        int[] result = new int[a.length];
        int count = 0;
        for(int i = 0; i < a.length; i++){
            if(type.validate(a[i])){
                result[count] = a[i];
                count++;
            }
        }
        return Arrays.copyOf(result,count);
    };

    //Method for printing.
    public static void print(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
        System.out.println("----------------------------------------");
    }
}
