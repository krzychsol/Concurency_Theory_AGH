package production;


import paralelism.MyLock;

public interface IProduction {

    public void join() throws InterruptedException;

    public void start();

    public void apply();

    public void injectRefs(MyLock _lock);

//    public P getObj();
}
