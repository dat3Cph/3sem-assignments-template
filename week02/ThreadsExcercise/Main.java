package ThreadsExcercise;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        ////////////////////////////////////////// EXERCISE 1: ///////////////////////////////////////////////////

        for(int i = 0; i<26; i++){
            executor.submit(new Task());
        }


        executor.shutdown();

        System.out.println("-------------------------------------------------------------------------------------");

        ////////////////////////////////////////// EXERCISE 2: ///////////////////////////////////////////////////

        ExecutorService executor1 = Executors.newCachedThreadPool();



        for(int i = 0; i<11; i++){
            executor1.execute(Counter::increment);
        }

        executor1.shutdown();
        try{
            boolean isDone = executor1.awaitTermination(15, TimeUnit.SECONDS);
        }catch(InterruptedException e){
            e.printStackTrace();
        }


        System.out.println("Count should be 11, but is:"+ Counter.getCount());



        //////////////////////////////////////////////// EXERCISE 3 ////////////////////////////////////////////////////////////////////

        //CHECK AddingNumbers class:
        //The problem is, that multiple threads try to add into a list on the same index at the same time. Fixed by making the List a
        //synchronised list.

        ////////////////////////////////////////////// EXERCISE 4 ////////////////////////////////////////////////////////////////////
        ExecutorService executor2 = Executors.newCachedThreadPool();

        for(int i = 0; i < 1000000; i++){
            executor2.submit(new Task());
        }



    }
    private static class Counter{
        private static int count = 0;

        public static synchronized void increment(){
            count++;
        }

        public static int getCount(){
            return count;
        }
    }
}


