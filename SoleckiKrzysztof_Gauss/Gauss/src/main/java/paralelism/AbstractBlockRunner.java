package paralelism;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import production.IProduction;

public abstract class AbstractBlockRunner implements BlockRunner {

    private final List<IProduction> list = new LinkedList<>();

    //starts all threads
    @Override
    public void startAll() {
        for (IProduction production:list) {
            runOne(production);
        }
        wakeAll();
        for (IProduction production:list) {
            try {
                production.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(AbstractBlockRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        list.clear();
    }

    //adds a thread to poll
    @Override
    public void addThread(IProduction _pThread) {
        list.add(_pThread);
    }

    //starts one thread
    abstract void runOne(IProduction _pOne);

    //wakes all threads
    abstract void wakeAll();
}

