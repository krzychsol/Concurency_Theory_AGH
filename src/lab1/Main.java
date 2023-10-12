package lab1;

import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        IncrementThread incrementThread = new IncrementThread(counter);
        DecrementThread decrementThread = new DecrementThread(counter);

        long startTime = System.nanoTime();

        incrementThread.start();
        decrementThread.start();

        incrementThread.join();
        decrementThread.join();

        long elapsedTime = System.nanoTime() - startTime;

        System.out.println("Final Count: " + counter.getCount());
        System.out.println("Elapsed time: " + (double)elapsedTime/1000000 + " seconds");

//        int threadCount = 0;

//        try {
//            while (true) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        try {
//                            Thread.sleep(Long.MAX_VALUE);
//                        } catch (InterruptedException e) {
//                            Thread.currentThread().interrupt();
//                        }
//                    }
//                }).start();
//                threadCount++;
//            }
//        } catch (OutOfMemoryError e) {
//            System.out.println("Maximum thread count reached: " + threadCount);
//        }
    }
}