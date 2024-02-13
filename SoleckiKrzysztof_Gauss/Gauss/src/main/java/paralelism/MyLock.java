package paralelism;


public class MyLock {

    private boolean blocked = false;

    public synchronized void lock() {
        blocked = true;
    }

    public synchronized void unlock() {
        blocked = false;
        notifyAll();
    }
}
