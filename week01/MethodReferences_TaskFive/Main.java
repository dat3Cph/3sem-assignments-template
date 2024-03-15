package MethodReferences_TaskFive;

import FunctionalProgramming_TaskTwo.MyValidatingType;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        int[] num = {
                1,
                5,
                6,
                12,
                50,
                7,
                21,
                30
        };

        MyTransformingType multiply = Main::multiply;
        int[] result = map(num,multiply);
        print(result);

        MyValidatingType isDivisible = Main::isDivisibleBy7;
        int[] filtered = filter(num,isDivisible);
        print(filtered);

    }


    public static int multiply(int a){
        return Math.multiplyExact(a,2);
    }

    public static boolean isDivisibleBy7(int a){
        return a%7 == 0;
    }

    public static int[] map(int[] a, MyTransformingType type){
        int[] result = new int[a.length];
        for(int i = 0; i < a.length; i++){
            result[i] = type.transform(a[i]);
        }
        return result;
    };


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

    public static void print(int[] a){
        for(int i = 0; i < a.length; i++){
            System.out.println(a[i]);
        }
        System.out.println("----------------------------------------");
    }
}
