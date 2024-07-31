package MultiThreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorServiceRunner {
    public static void main(String[] args) {
       // ExecutorService service= Executors.newSingleThreadExecutor();
        /*Here newSingleThreadExecutor means when 1 thread is completed, then only the next thread will start
        * but as we run the code, thread 3 starts in between because it is not a part of executor service
        * but as soon as task 1 completes, only then the tast 2 will start as both of them are the part of
        * executor service*/
        ExecutorService service = Executors.newFixedThreadPool(4);
        /*With the help of newFixedThreadPool we can run threads concurrently (2 in above example))*/
        service.execute(new Thread1(1));
        service.execute(new Thread1(2));
        service.execute(new Thread1(3));
        service.execute(new Thread1(4));

        service.shutdown();
    }
}
