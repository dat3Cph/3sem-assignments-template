package Concurrency_TaskNine;

public class Task  {


    public void run() {
        try{
            System.out.println(Thread.currentThread().getName());
            Thread.sleep(1000);
            throw new InterruptedException("Error");
        }catch(InterruptedException e){
            e.printStackTrace();
        }
    }
}
