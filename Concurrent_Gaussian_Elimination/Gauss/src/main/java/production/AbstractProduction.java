package production;

import paralelism.MyLock;

public abstract class AbstractProduction implements IProduction {

    private MyLock lock;
    protected final Thread thread = new Thread(this::apply);

    public AbstractProduction() {
    }


    //run the thread
    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void join() throws InterruptedException {
        thread.join();
    }

    @Override
    public void injectRefs(MyLock _lock) {
        this.lock = _lock;
    }
}
