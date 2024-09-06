package MultiThreading.NewMultiThreading.Synchronization;

public class Counter {
    private int count=0;

    public void increment(){
        synchronized (this){
            count++;
        }
        //using synchronized means we are using the intrinsic lock. It is automatic locks.
        //Other is Explicit locks which can be used using lock class from java.util.concurrent.locks

    }

    public int getCount(){
        return count;
    }

}
