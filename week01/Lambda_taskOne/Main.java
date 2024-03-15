package Lambda_taskOne;

public class Main {
    public static void main(String[] args) {


        //Initialization of lambdas:
        ArithmeticOperation addition = (a,b) -> Math.addExact(a,b);
        ArithmeticOperation subtraction = (a,b) -> Math.subtractExact(a,b);
        ArithmeticOperation multiplication = (a,b) -> Math.multiplyExact(a,b);
        ArithmeticOperation division = (a,b) -> Math.floorDiv(a,b);
        ArithmeticOperation modulus = (a,b) -> Math.floorMod(a,b);
        ArithmeticOperation power = (a,b) -> (int) Math.pow(a,b);


        //Keeping the lambdas in a list to go through them all in a loop.
        ArithmeticOperation[] operations = new ArithmeticOperation[] {
                addition,
                subtraction,
                multiplication,
                division,
                modulus,
                power
        };

        //Numbers to run calculations on:
        int[] nums = {1,4,5,10,23,20};
        int[] nums2 = {5,16,24,300,56,10};

        //For each ArithmeticOperation in operations, call the operate method with the values 2 and 4. This will go through each of the different operations.
        for (ArithmeticOperation o: operations) {
            System.out.println(operate(2, 4, o));
        }

        System.out.println("---------------------------------");

        //For each ArithmeticOperation in operations, call the operate method with the values from the two arrays of integers. This will go through every pair of
        //integers in every operation.
        for (ArithmeticOperation o: operations){
            int[] results = operate(nums,nums2,o);
            for(int i = 0; i < results.length; i++){
                System.out.println(results[i]);
            }
            System.out.println("---------------------------------");
        }

    }

    public static int operate(int a, int b, ArithmeticOperation operation){
        return operation.perform(a,b);
    }

    public static int[] operate(int[] a, int[] b, ArithmeticOperation operation){
        int[] result = new int[a.length];
        for (int i = 0; i < a.length; i++){
            result[i] = operation.perform(a[i],b[i]);
        }
        return result;
    }

}
