package lab1;

public class DecrementThread extends Thread{
    private Counter counter;

    public DecrementThread(Counter counter){
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i=0;i<10000;i++){
            counter.decrement();
        }
    }
}
