package ThreadsExcercise;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static char index = 'A';
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(4);
        for(int i = 0; i < 25; i++){
            executor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(index+index+index);
                    index++;
                }
            });
        }



    }
}
