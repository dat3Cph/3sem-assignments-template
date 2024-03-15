package ThreadsExcercise;

public class Task implements Runnable{

    private static final Object lock = new Object();
    private static char currentChar = 'A';
    @Override
    public void run() {
        synchronized (lock){
            System.out.println(currentChar+""+currentChar+""+currentChar);
            currentChar++;
        }
    }
}
